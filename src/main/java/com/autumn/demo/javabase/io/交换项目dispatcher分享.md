# 交换项目dispatcher分享

标签: 交换 netty 设计模式

---

[TOC]
### 1. dispatcher
#### 1. 概要序列图

![交换概要序列图][1]
#### 2. 思路
```
使用netty框架.
dispatcher是服务端, engine和module是客户端. 客户端可以有多个, 目前engine只有一个, module有多个.
使用moduleConfig.json配置客户端服务.
enging处理器,module处理器,超时处理机制, 断线重连机制
```
#### 3. 代码
##### 1. Bootstrap
```
netty服务端创建: engine和module.
ChannelInitializer的创建: 1. 使用Google Protobuf编解码;2. 心跳handler; 3. 自定义handler.
```

##### 2. 自定义handler的选择
策略模式:

| 类名  | 说 明 |
|-------|------|    
|FactoryBean<T>| 策略, 定义实现策略所必需的接口. 使用getObject()接口|
|EngineHandlerFactoryBean| 具体的策略: 实现FactoryBean,通过getObject()得到EngineHandler实例=PacketDispatcher |
|MessageHandlerFactoryBean| 具体的策略: 实现FactoryBean, 通过getObject()得到MessageHandler实例=PacketDispatcher + ModuleService|
|EngineHandler | 继承 ChannelHandlerAdapter|
|MessageHandler| 继承 ChannelHandlerAdapter|
|||
|MessageTimeoutTaskFactoryBean|具体的策略: 实现FactoryBean,通过getObject()得到MessageTimeoutTask实例|
|MessageTimeoutTask|自定义类, 实现TimerTask接口. 重写run()|

Mediator模式:

| 类名 | 说明 |
|---|---|
|PacketDispatcher|Mediator: 定义和实现与Colleague角色进行通信和做出决定的接口, Hander的集合和对handler的操作|
|EngineHandler|Colleague : 定义和实现与Mediator角色进行通信的接口, 域中包含PacketDispatcher|
|MessageHandler|Colleague : 定义和实现与Mediator角色进行通信的接口, 域中包含PacketDispatcher|

 策略模式实例
 
```
//1. context: Bootstrap-策略接口的使用, 
ChannelInitializer tcpChannelInitializer(FactoryBean<? extends ChannelHandlerAdapter> factory) {      
    ...省略
    .addLast(decoder)
    .addLast(encoder)
    .addLast(factory.getObject());
}

// 2. ConcreteStrategy角色的实例1-EngineHandler
public class EngineHandlerFactoryBean implements FactoryBean<EngineHandler> {
    @Autowired
    private PacketDispatcher packetDispatcher;

    @Override
    public EngineHandler getObject() throws Exception {
        EngineHandler engineHandler = new EngineHandler();
        engineHandler.setPacketDispatcher(packetDispatcher); // 仲裁者模式
        return engineHandler;
    }
}
// 2. ConcreteStrategy角色的实例2-MessageHandler
public class MessageHandlerFactoryBean implements FactoryBean<MessageHandler> {
    @Autowired
    private PacketDispatcher packetDispatcher;
    @Autowired
    private ModuleService moduleService;

    @Override
    public MessageHandler getObject() throws Exception {
        MessageHandler handler = new MessageHandler();
        handler.setPacketDispatcher(packetDispatcher);  // 仲裁者模式
        handler.setModuleService(moduleService);
        return handler;
    }
}

// 3. context: Bootstrap-保存了ConcreteStrategy角色的实例.
startTcpServer(moduleServiceIp, moduleServicePort,
                "moduleService",
                tcpChannelInitializer(messageHandlerFactoryBean));
    
startTcpServer(engineServiceIp, engineServicePort,
                "engineService",
                tcpChannelInitializer(engineHandlerFactoryBean));
                
```

##### 3. handler
EngineHandler
```
EngineHandler extends ChannelHandlerAdapter {
// 方法1: 注册engine客户端 或者 netty报文发送到module模块
    channelRead() {
    if (未注册) {
    engineId = nettyMessage.getModuleCode();
    packetDispatcher.addEngineServer(engineId, this);
    isRegistered = true; // 注册标识
    } else {
    packetDispatcher.messageOut(nettyMessage); // 报文发送到module模块
    }
    }
    
// 方法2: netty报文发送到engine, 由ctx决定
    send() {
      ChannelFuture cf = ctx.writeAndFlush(nettyMessage);  // 报文发送到engine
    }
}
```
 
MessageHandler
 
```
MessageHandler extends ChannelHandlerAdapter {
// 方法1: 注册module客户端 或者 将报文发送到engine模块
    channelRead(..) {
    // 获取moduleCode, 为空则关闭channel.
    // proxy-business的moduleCode默认为0000. 
    if (!"0000".equals(moduleCode) && !started) {
    // module未注册
    if (!moduleCheck(ctx, moduleCode)) {
        throw new RuntimeException("module not found " + moduleCode);
        }
        // 第一次的数据是注册数据， 长度 + 模块编码
        packetDispatcher.addModule(moduleCode, this);
        started = true;
     } else {
     packetDispatcher.messageIn(buf, this); // 报文发送到engine模块.
     }
    }
    
// 方法2: netty发送报文到module模块. 由ctx决定
send() {
    if (ctx.channel().isOpen() && ctx.channel().isActive()) {
    ChannelFuture cf = ctx.writeAndFlush(nettyMessage); // 报文发送到module模块
    } else {
    //与module客户端断线
    throw new ModuleDisconnectedException("Connection disconnected: " + moduleCode);
    }
}

// 方法3: 校验moduleCode
boolean moduleCheck(ctx, moduleCode) {
// 调用ModuleService的方法校验
moduleService.moduleCHeck(moduleCode, ip);
checkOk = true;
}
}
```

ModuleService
```
ModuleService{
// 方法1: 读取moduleConfig.json, 放入List<Map>集合中. 在系统启动时初始化
initModuleConfig() {
    1. 根据文件地址或通过类加载器读取moduleConfig.json文件, inputStream
    2.使用jackson的ObjectMapper.readValue(inputStream, List.class)将输入流转为List<Map> moduleConfig.
} 

// 方法2: 校验moduleCode是否存在.
moduleCheck(moduleCode, ip) {
    // 遍历moduleConfig, 对比moduleCode与map.get("moduleCode")值是否相同.
    Map module = null;
        for(Map map : moduleConfig) {
            if(map.get("moduleCode").equals(moduleCode)) {
                module = map;
                break;
            }
        }
        // null代表没有在moduleConfig.json里面配置
        if (module == null) {
            throw new RuntimeException("Module not found: " + moduleCode);
        }
}
}
```

PacketDispatcher
```
// 方法1: 向外部模块转发报文(包括结束后返回报文给proxy-business)
messageOut(nettyMessage) {
    String iid = nettyMessage.getProcessInstanceId();
    if (nettyMessage.getEngineEvent() == EngineEvent.END
                || nettyMessage.getEngineEvent() == EngineEvent.ABORT) {
    //结束消息通知给发送方，并在map中移除
    MessageHandler handler = removeInstanceAndgetHandler(iid);
    // 发送响应报文给proxy-business
    if (null != handler) {handler.send(nettyMessage);}
    } else {
  MessageHandler handler =moduleHandlerMap.get(nettyMessage.getModuleCode());
            if (handler != null) {
            //时间监控
            messageTimer.startCountTime(nettyMessage);
            // 发送报文到module
            handler.send(nettyMessage);
            messageTimer.start(nettyMessage);
            } else {
            // 超时或断线机制
            messageService.sendTimeoutMessage(nettyMessage);
            }
        }
}
// 方法2: 向engine模块发送报文/proxy-business注册
private void doMessageIn(NettyMessage.Netty message,MessageHandler handler) {
        String instanceId = message.getProcessInstanceId();
        //处理客户端请求
        if (instanceId == null || instanceId.trim().isEmpty()) {
            instanceId = UUID.randomUUID().toString().replaceAll("-", "");// 流水号
            // proxy-business的IP地址
            remoteInstanceMap.put(instanceId, handler.getRemoteAddress()); 
            // proxy-business分配的MessageHandler
            instanceHandlerMap.put(instanceId, handler);
        }else {
            LogUtil.log(message, LogUtil.StepType.ReceivedFromModule);
        }
        String engineId = selectEngineServer(message);
        message = message.toBuilder().setEngineId(engineId).
                build();
        LogUtil.log(message, LogUtil.StepType.PreSendToEngine);
        engineHandlerMap.get(engineId).send(message);
    }

// 方法3: 添加Engine客户端. 可以有多个Engine
public void addEngineServer(String engineId, EngineHandler engineHandler) {
        if (StringUtils.hasText(engineId)) { // 只要engineId不是null, ""或者" ". 都会返回true
            // engineId和对应的EngineHandler
            engineHandlerMap.put(engineId, engineHandler);
            // enginge集合
            engineIds.add(engineId);
        } else {
            throw new RuntimeException("EngineId can't be null or empty.");
        }
    }
    
// 方法4: 添加module客户端. 可以有多个module
public void addModule(String moduleCode, MessageHandler moduleHandler) {
        // 只要moduleCode不是null, ""或者" ". 都会返回true
        if (StringUtils.hasText(moduleCode)) {
        // moduleCode和对应的ModuleHandler
            moduleHandlerMap.put(moduleCode, moduleHandler);
        }
        else {
            throw new RuntimeException("moduleCode can't be null or empty.");
        }
    }
 
// 方法5: 删除instanceEngineMap,instanceHandlerMap和remoteInstanceMap
private synchronized MessageHandler removeInstanceAndgetHandler(String instanceId) {
        // 流程正常或异常结束通知
        String prev = instanceEngineMap.remove(instanceId);// 删除enginie
        if (prev == null) {
            LOG.warn("Instance Engine Map Entry not exists: {}.", instanceId);
        }
        MessageHandler prevHandler=instanceHandlerMap.remove(instanceId); // 删除代理handler
        if(prevHandler==null){
            LOG.warn("Instance Handler Map Entry not exists: {}.instanceHandlerMap size{}",instanceId,instanceHandlerMap.size());
        }
        remoteInstanceMap.remove(instanceId);  // 删除代理IP地址  
        return prevHandler;
    }  
  
 // 方法: 选取合适的engine server：
        private synchronized String selectEngineServer(NettyMessage.Netty message) {
        String engineId = null;
        if (message.getEngineEvent() != EngineEvent.START) {
        //对于已经在跑的流程， 直接用原来的server跑；
            engineId = instanceEngineMap.get(message.getProcessInstanceId());
        }else {
            //对于新的流程，采用round-robin方式，选取server。
            int index = engineIndex.getAndIncrement();
            engineId = engineIds.get(index % engineIds.size());
            instanceEngineMap.put(message.getProcessInstanceId(), engineId);
            // 监控每秒钟访问engine的次数
            monitor.incrementRunningProcess();
        }
        return engineId;
    }    
```
ServerMonitor
```
public class ServerMonitor {
    private AtomicLong all = new AtomicLong(0);
    private AtomicLong current = new AtomicLong(0);

    Map<Long, Integer> countPerSecond = new HashMap<>();
    // 从1开始, 每秒钟增长的数量
    public void incrementRunningProcess() {
        current.incrementAndGet();
        all.incrementAndGet();

        synchronized (this) {
            long key = System.currentTimeMillis() / 1000;
            Integer count = countPerSecond.get(key);
            countPerSecond.clear();
            if (count == null) {
                countPerSecond.put(key, 1);
            }
            else {
                countPerSecond.put(key, ++ count);
            }
        }
    }
```

超时机制
netty中的Timer管理，使用了的Hashed time Wheel的模式，Time Wheel翻译为时间轮，是用于实现定时器timer的经典算法。
```
 class MessageTimer {
    @Autowired
    private Timer timer;
    @Autowired
    private MessageTimeoutTaskFactoryBean factoryBean;
    @Value("${service.timeout}")
    private long timeout;
    private Map<String, Timeout> timeoutMap = new ConcurrentHashMap<>();
    private Map<String, Long> countTimeMap = new ConcurrentHashMap<>();
    /**
     * 启动message的超时监控任务
     * @param message
     */
    public void start(NettyMessage.Netty message) {
        MessageTimeoutTask task;
        // 获取超时任务
        try {
            task = factoryBean.getObject();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return;
        }
        task.message = message;
        // 超时后执行task
        timeoutMap.put(
                message.getProcessInstanceId() + message.getActivityId(),
                timer.newTimeout(task, timeout, TimeUnit.SECONDS)
        );
    }

    /**
     * 把message对应的timeout从timeoutMap中移除，并取消超时任务
     * @param message
     */
    public void stop(NettyMessage.Netty message) {
        Timeout timeout = cancel(message);
        if (timeout != null) {
            timeout.cancel();
        }
    }

    /**
     * 把message对应的timeout从timeoutMap中移除
     * @param message
     * @return
     */
    public Timeout cancel(NettyMessage.Netty message) {
        Timeout timeout = timeoutMap.remove(message.getProcessInstanceId() + message.getActivityId());
        return timeout;
    }
    
```


### 3. netty
#### 1. 服务端创建

#### 2. 客户端创建

#### 3. 编解码

#### 4. ChannelHandler

### 4. 设计模式

### 5. 算法
round-robin: 负载均衡算法
Hashed time wheel: 定时时间轮算法


  [1]: http://172.18.80.126:8090/pages/viewpage.action?pageId=10226004