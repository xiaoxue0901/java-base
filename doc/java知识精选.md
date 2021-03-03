# java知识精选
## 问题
### 1. 关于深克隆和浅克隆的差异和原理
1. 克隆: 快速构造一个对象的副本
2. native Object clone()-> Object的方法. native方法: 调用c语言实现.
3. 实现Cloneable接口: 一种标准, 标识重写了clone 并支持克隆
   * 浅克隆: 仅仅复制克隆对象, 而不复制对方引用对象.
		1. 原始类型: 基本类型, 引用类型; 克隆原始对象: 浅克隆仅复制基本类型, 引用类型公用. 	
   * 深克隆: 
      	* 方式1: 将各个引用类型也实现Cloneable, 重写clone()方法, 将引用类型也调用email.clone()
      	* 方式2: 序列化方式, 使用流操作(ObjectOutputStream和ObjectInputStream), 将类转为字节流操作: 先读再写

## 2.如何将一个Java对象序列化到文件中.
[全方位解析java序列化](https://mp.weixin.qq.com/s/uWwim8QJ2xtmKEn4xsnWyw)
1. 序列化意义: 对象跨jvm传输,  
   * 流程: 将对象转为二进制数据(序列化)-> 网络传输-> 二进制数据反序列化为对象(反序列化)
   1. 对象序列化: 每个对象都是用一个序列号（serial number）保存的，这就是这种机制之所以称为对象序列化的原因。
   2. 序列化技术
   		* java原生序列化: 序列化后数据大, 不能实现跨语言传输
   		* xml
   		* json
   		* protobuf
   		* hession等等	
2. 实现序列化: 类要实现Serializable接口, serializableId是指纹.(同一个类的id不一样, 就不能成功的反序列化) 先写后读. ByteArrayOutputStream, ObjectOutputStream, ByteArrayInputStream, ObjectInputStream.
3. transient属性代表不能被序列化, 通过writeObject和readObject可以绕过transient
### 问题: 
	1. 序列化的底层是怎么实现的？
	本文第六小节可以回答这个问题，如回答 Serializable 关键字作用，序列化标志，源码中它的作用。还有，可以回答 writeObject 几个核心方法，如直接写入基本类型，获取 obj 类型数据，循环递归写入等。
	writeObject(Object)->writeObject0(Object, boolean)->writeOrdinaryObject()->writeClassDesc()->writeSerialData()->invokeWriteObject()->defaultWriteFields()

	2. 序列化时，如何让某些成员不要序列化？
	可以用 transient 关键字修饰，它可以阻止修饰的字段被序列化到文件中，在被反序列化后，transient 字段的值被设为初始值，比如 int 型的值会被设置为 0，对象型初始值会被设置为 null。

	3. 在 Java 中,Serializable 和 Externalizable 有什么区别？
	Externalizable 继承了 Serializable，给我们提供 writeExternal() 和 readExternal() 方法, 让我们可以控制 Java 的序列化机制, 不依赖于 Java 的默认序列化。正确实现 Externalizable 接口可以显著提高应用程序的性能。

	4. serialVersionUID 有什么用？
	可以看回本文第七小节，Java 序列化的机制是通过判断类的 serialVersionUID 来验证版本是否一致的。在进行反序列化时，JVM 会把传来的字节流中的 serialVersionUID 和本地相应实体类的 serialVersionUID 进行比较，如果相同，反序列化成功，如果不相同，就抛出 InvalidClassException 异常。

	5. 是否可以自定义序列化过程, 或者是否可以覆盖 Java 中的默认序列化过程？
	可以的。
	我们都知道,对于序列化一个对象需调用 ObjectOutputStream.writeObject(saveThisObject), 并用 ObjectInputStream.readObject() 读取对象, 但 Java 虚拟机为你提供的还有一件事, 是定义这两个方法。如果在类中定义这两种方法, 则 JVM 将调用这两种方法, 而不是应用默认序列化机制。同时，可以声明这些方法为私有方法，以避免被继承、重写或重载。

	6. 在 Java 序列化期间,哪些变量未序列化？
	static 静态变量和 transient 修饰的字段是不会被序列化的。静态（static）成员变量是属于类级别的，而序列化是针对对象的。transient 关键字修字段饰，可以阻止该字段被序列化到文件中。

## 3. Java的Buffer类
[初探java的buffer类](https://blog.csdn.net/czx2018/article/details/89502699)
[NIO-Buffer](https://www.iteye.com/blog/zachary-guo-1457542)

## 4. Java的多线程并发为什么不安全?
[多线程并发为什么不安全](https://www.cnblogs.com/dhcao/p/10982278.html)

## 5. 动态代理
[动态代理竟然如此简单](https://mp.weixin.qq.com/s/kxBZvm3NH1OoAKY_8T1-6w)

## 6. 线程池相关的问题
[线程池的7种创建方式，强烈推荐你用它](https://mp.weixin.qq.com/s/byLuH4uTwyDiJCBQiUbpDw)

## 7. 并发编程
1. queue: [45张图庖丁解牛18种Queue，给你整得明明白白！](https://mp.weixin.qq.com/s/CAhPCb81jS6MXMTA76k_uA)
2. 集合: [21张图带你领略集合的线程不安全 ](https://mp.weixin.qq.com/s/qJ0ELP4m9x8tESLXwlhpmw)
3. volatile: [​反制面试官 | 14张原理图 | 再也不怕被问 volatile! ](https://mp.weixin.qq.com/s/NuC06tTCe5spYWYv_23DmA)
4. CAS: [CAS原理太简单？](https://mp.weixin.qq.com/s/nLfvnhrbYkrq-rR36OlIwA)
5. ABA: [ABA原理](https://mp.weixin.qq.com/s/VMdWneLOMtJEFwgOaFfFKg)
6. 锁: [5000字 | 24张图带你彻底理解Java中的21种锁](https://mp.weixin.qq.com/s/1cWszX8MWXq_XNMXNyeDMg)
7. 队列: [10分钟搞定 Java 并发队列好吗？好的！](https://mp.weixin.qq.com/s/TYDZy0O0CXFwiKU1zcOt5A)

# 设计模式
[Spring 中经典的 9 种设计模式，打死也要记住啊！ ](https://mp.weixin.qq.com/s/-YkOeOk6t2fy7EruDdxigQ)
[设计模式之工厂模式](https://blog.csdn.net/can_chen/article/details/105924115)

# Spring

1. Spring的bean的初始化
	1. Spring对Bean的三类扩展点, 分别对应不同Bean的生命周期阶段
	   a. Aware接口
	   b. BeanPostProcessor
	   c. InitializingBean和init-method
	2. Spring的容器启动时bean的初始化流程
	   1.实例化Bean实例
	   2.设置对象属性
	   3.检查Aware的相关接口并设置相关依赖. (比如:实现 ApplicationContextAware 接口的 setApplicationContext 方法)
	   4.BeanPostProcessor前置处理
	   5.是否实现InitializingBean接口.(比如: 实现InitializingBean 接口的 afterPropertiesSet )
	   6.是否配置自定义的init-method
	   7.BeanPostProcessor后置处理
	   8.注册Destruction相关回调接口
	   		使用中
	   9.是否实现DisposableBean接口
	   10.是否配置自定义的destory-method
	   11. 结束
	   
	
# Netty
[面试官：Netty的线程模型可不是Reactor这么简单 ](https://mp.weixin.qq.com/s/vaqzvQCAoEfZn03dWXJ2BQ)
[Netty 实现原理浅析 ](https://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651479592&idx=1&sn=b2aff56737d0e44667dd881d24ae27df&chksm=bd2532578a52bb41c1ed7f7cbe7bfc5b53bee7325a989a375adbe0e39195c209c0b0368aadb3&scene=21#wechat_redirect)
[Netty in action：事件循环和线程模式 ](https://mp.weixin.qq.com/s/r7XaKcTAYZO9cTLBovWi6w)
[「Netty实战 01」从 BIO、NIO 聊到 Netty，最后还要实现个 RPC 框架](https://mp.weixin.qq.com/s/7Aaof1Z2J6pLGftHzzaRGQ)
[「Netty实战 02」手把手教你实现自己的第一个 Netty 应用!新手也能搞懂](https://mp.weixin.qq.com/s/I0fJEW--0McrXw941INoRw)
[「Netty实战 03」大白话 Netty 核心组件分析](https://mp.weixin.qq.com/s/n8PugpN7ixekfLhb48R2dA)

# web
[还分不清 Cookie、Session、Token、JWT？](https://mp.weixin.qq.com/s/TfJzvkF7B1lq_LNsbqMI0g)
[《我想进大厂》之网络篇夺命连环12问 ](https://mp.weixin.qq.com/s/_79l2xe_n_PLr0XWRoBUvA)

# Redis
**参考文献**
[一不小心肝出了4W字的Redis面试教程 ](https://mp.weixin.qq.com/s/qI334XgLHrJuU2Oqqi4uwg)
## 问题
1. redis的数据结构`string`的底层实现
	0. 参考: [redis为什么把简单的字符串设计成SDS](https://mp.weixin.qq.com/s/k41iYHZwYb3aiY_jmQiSfg)
	1. SDS: (simple dynamic string)简单动态字符串
	2. 数据结构
	```C
	struct sdshdr{
	int free; // buf数组未使用的字节的数量
	int len;	// buf[]数组保存的字符串的长度
	char buf[]; // 实际保存字符串的char类型数组
	}
	```
	3. 效率高: 使用len()获取字符串长度, 直接取len属性值,不用遍历,复杂度是O(1); 
	4. 数据溢出: C语言字符串超长后会侵占相邻字符串的内存空间, 但是redis的内存重分配策略可以避免数据溢出.
	5. 内存重分配策略
		1. 空间预分配: 优化SDS字符串增长操作. sds的空间自动扩容后, free的长度会变化:len<1M,free=len; len>=1M, free=1M.
		2. 惰性空间释放: 优化SDS字符串缩短操作, 当字符串缩短后,不立刻执行内存重分配,而是用free将释放的空间记录下来,用于后续的字符串增长使用.
	6. 数据格式多样性: C字符串会用`\0`空字符结尾标识字符串结束, 故只能支持文本数据,而图片,音视频等二进制格式的数据无法保存;redis以处理二进制的方式操作buf数组中的数据,所以存入的是什么数据,取出来还是什么样.
2. Redis是什么?
   * Redis 是 C 语言开发的一个开源的（遵从 BSD 协议）高性能键值对（key-value）的内存数据库，可以用作数据库、缓存、消息中间件等。
	* 丰富的数据类型，支持字符串（strings）、散列（hashes）、列表（lists）、集合（sets）、有序集合（sorted sets）
	* 支持数据持久化。
	* 主从复制，哨兵，高可用, 集群。
	* 可以用作分布式锁。
3. Redis的特点和功能?
   * 功能: 缓解高并发下对数据库的访问压力,快速响应. 
   * 使用场景: 
   1. 秒杀
   2. 消息队列
   3. 用作分布式锁
4. Redis实现的分布式锁
	1. 使用Redis的事务实现. watch->multi->执行redis命令->exec->finally unwatch (类似乐观锁,只要watch到key变化,就执行失败)
	2. 使用setnx(获取锁),getset,expire(设置有效期,防止死锁),del(释放锁)四个命令实现.
	3. 使用Redisson实现锁.
```java
	RLock lock = redisson.getLock("lockname");
		try{
		lock.lock(); // 获取锁
		} finally {
    	lock.unlock(); // 释放锁
		}
 ```
5. Redis为什么能这么快?
    1. Redis 完全基于内存，绝大部分请求是纯粹的内存操作，非常迅速，数据存在内存中，类似于 HashMap，HashMap 的优势就是查找和操作的时间复杂度是 O(1)。
	2. 数据结构简单，对数据操作也简单。
	3. 采用单线程，避免了不必要的上下文切换和竞争条件，不存在多线程导致的 CPU 切换，不用去考虑各种锁的问题，不存在加锁释放锁操作，没有死锁问题导致的性能消耗。
	4. 使用多路复用 IO 模型，非阻塞 IO。
6. Redis缓存的淘汰策略
    * 从已设置过期时间的KV集中淘汰
	1. volatile-lru:  最少使用的淘汰
	2. voladile-ttl: 优先对剩余时间短的数据淘汰
	3. voladile-random: 随机选择淘汰
	* 从所有KV集中淘汰
	4. allkeys-lru: 使用最少的数据淘汰
	5. allkeys-random: 随机选择数据淘汰
	6. noeviction: 不淘汰, 若超过最大内存, 返回错误信息.
	* 两个算法: LRU(Least Recently used)和LFU(Least Frequently used)
7. 持久化的两种方式
    1. RDB(Redis Database): RDB持久化就是将当前进程的数据以生成快照的形式持久化到磁盘中
	    * 特点: 1. 开机恢复数据快; 2. 写入持久化文件快。缺点: RDB会丢失最后一次快照的数据。
		* RDB的持久化也是Redis默认的持久化机制，它会把内存中的数据以快照的形式写入默认文件名为dump.rdb中保存。
		* 持久化触发时机. 3种方式:
			1. save命令: 通过阻塞当前Redis服务器，直到RDB完成为止
			2. bgsave命令:在后台fork一个与Redis主线程一摸一样的子线程，由子线程负责内存中的数据持久化。
			3. 自动: 也可以在redis.conf配置文件中，完成配置. save 900 1 (命令 间隔时间 key变化个数)
	2. AOF(Append Only File): AOF持久化机制是以日志的形式记录Redis中的每一次的增删改操作，不会记录查询操作，以文本的形式记录，打开记录的日志文件(appendonly.aof)就可以查看操作记录。
		* 特点: 数据完整性高, 增量复制. 
		* 默认不开启, 通过`appendonly no`修改为`appendonly yes`可开启. 
		* AOF持久化触发机制, 3种
			1. no: 表示等操作系统等数据缓存同步到磁盘中（快、持久化没保证）
			2. always: 同步持久化，每次发生数据变更时，就会立即记录到磁盘中（慢，安全）。
			3. everysec: 表示每秒同步一次（默认值，很快，但是会丢失一秒内的数据）。异步完成的.
		* AOF重写机制: 目的是避免了操作的冗余行, 保证了数据的最新. 流程:redis提供了bgrewriteaof命令。将内存中的数据以命令的方式保存到临时文件中，同时会fork出一条新进程来将文件重写
	3. 混合持久化(RDB+AOF): 通过bgrewriteaof来完成,fork出的子进程先将共享内存的数据以RDB方式写入aof文件中，然后再将重写缓冲区的增量命令以AOF方式写入文件中。
8. Redis高可用部分的主从复制和哨兵的基本原理
   1. 主从模式
		`概念: 一主多从，主数据库（master）可以读也可以写（read/write），从数据库仅读（only read）`
	  * 应用: 主从模式一般实现读写分离，主数据库仅写（only write），减轻主数据库的压力 
	  * 工作机制:
		1. 当slave启动后会向master发送SYNC(或PSYNC[runid][offset])命令，master收到从库的命令后通过bgsave保存快照（RDB持久化），在同步给从库期间的执行的写命令会被缓存到repl_bloking队列中。
		2. 然后master会将保存的快照发送给slave，并且继续缓存期间的写命令。
		3. slave收到主数据库发送过来的快照就会加载到自己的数据库中。
		4. 最后master将缓存的命令同步给slave，slave收到命令后执行一遍，这样master与slave数据就保持一致了
	  * 优缺点
		1. 优点: 
		   * 解决了单机版并发量大，导致请求延迟或者redis宕机服务停止的问题。实现读写分离, 分担主库的压力.
			* 解决了单机版单点故障的问题, 主库挂了后, 从库可以替代主库. 提高了系统的可用性和性能
		2. 缺点:
			* 数据一致性问题
			* 不具备自动容错和恢复的功能
   2. 哨兵模式
	   * 为什么要有哨兵模式: 主从的出现故障后，不会自动恢复，需要人为干预
		 `原理: 在主从的基础上，实现哨兵模式就是为了监控主从的运行状况，对主从的健壮进行监控，只要有异常就发出警告，对异常状况进行处理。
		 主要功能包括主节点存活检测、主从运行情况检测、自动故障转移、主从切换。`
	   * 哨兵的功能点		 
			1. 监控: 监控master和slave是否正常运行，以及哨兵之间也会相互监控
			2. 自动故障恢复：当master出现故障的时候，会自动选举一个slave作为master顶上去。
		* 哨兵的工作原理:
			1. 当哨兵启动后，会与master建立一条连接，用于订阅master的_sentinel_:hello频道。并且还会建立一条定时向master发送INFO命令获取master信息的连接。
			2. 当哨兵与master建立连接后，定期会向（10秒一次）master和slave发送INFO命令，若是master被标记为主观下线，频率就会变为1秒一次。
			3. 定期的向master、slave和其它哨兵发送PING命令（每秒一次），以便检测对象是否存活，若是对方接收到了PING命令，无故障情况下，会回复PONG命令。
			4. 总结: 哨兵通过建立这两条连接、通过定期发送INFO、PING命令来实现哨兵与哨兵、哨兵与master之间的通信。
		* 上线和下线
			1. 主观下线: 若是某一时刻哨兵发送的PING在指定时间内没有收到回复,那么发送PING命令的哨兵就会认为该master主观下线
			2. 客观下线: 如果一个主服务器被标记为主观下线，并且有足够数量的 Sentinel（至少要达到配置文件指定的数量）在指定的时间范围内同意这一判断，那么这个主服务器被标记为客观下线。
		* 选举算法
			1. master被认为客观下线后,发现master下线的sentinel会发起选举(Raft算法), 选出大佬哨兵.
			2. 大佬哨兵对故障进行自动恢复, 从slave中选出一个当master, 将剩余从节点指向新的主节点进行数据复制.
	3. Cluster模式
		`集群模式实现了Redis数据的分布式存储，实现数据的分片，每个redis节点存储不同的内容，并且解决了在线的节点收缩（下线）和扩容（上线）问题。`
	    * 集群的原理: 
		  1. 数据分区
			1. 在Redis集群中采用的是虚拟槽分区算法，会把redis集群分成16384 个槽（0 -16383）。
			2. 当客户端请求过来，会首先通过对key进行CRC16 校验并对 16384 取模（CRC16(key)%16383）计算出key所在的槽，然后再到对应的槽上进行取数据或者存数据，这样就实现了数据的访问更新。
		  2. 节点通信	
			1. 新上线的节点，会通过 Gossip 协议向老成员发送Meet消息，表示自己是新加入的成员。
			2. 老成员收到Meet消息后，在没有故障的情况下会恢复PONG消息
			3. 后续发送定期PING消息，实现节点之间的通信
		  3. 数据请求
			 1. redis底层维护了myslots数组: 存放每个节点的槽信息. 目的:只表示自己是否存储对应的槽数据
			 1. redis底层维护的clusterNode数组,大小16384: 用于储存负责对应槽的节点的ip、端口等信息	
		  4. 扩容和收缩
			 1. 节点的收缩和扩容时，会重新计算每一个节点负责的槽范围，并发根据虚拟槽算法，将对应的数据更新到对应的节点。
9. Redis和Memcached的区别
	* 存储方式上：Memcache 会把数据全部存在内存之中，断电后会挂掉，数据不能超过内存大小。Redis 有部分数据存在硬盘上，这样能保证数据的持久性。
	* 数据支持类型上：Memcache 对数据类型的支持简单，只支持简单的 key-value，，而 Redis 支持五种数据类型。
	* 使用底层模型不同：它们之间底层实现方式以及与客户端之间通信的应用协议不一样。Redis 直接自己构建了 VM 机制，因为一般的系统调用系统函数的话，会浪费一定的时间去移动和请求。
	* Value 的大小：Redis 可以达到 1GB，而 Memcache 只有 1MB。
10. Redis缓存的几大问题.
	1. 数据一致性问题. 
	   a. 如果对数据的一致性要求很高，那么就不能使用缓存。
	   b. 采用合适的策略保证数据一致性: 
	   	1. 强一致性: 更新数据库后及时更新缓存或者淘汰缓存, 加上缓存失效时间
	   	2. 准一致性: 更新数据库后, 异步更新缓存. (用多线程或者MQ实现)
	   	2. 最终一致性: 缓存失败时增加重试机制。(采用任务调度框架, 按照一定的频率更新.)
	2. 缓存穿透
	   `概念: 缓存穿透是指查询一条数据库和缓存都没有的一条数据，而用户不断发起请求, 就会一直查询数据库，对数据库的访问压力就会增大`
	   1. 参数合法性校验
	   2. 缓存空对象	  
	   3. 布隆过滤器: 一种基于概率的数据结构，主要用来判断某个元素是否在集合内. 缺点就是要维持容器中的数据
	3. 缓存击穿
	   `概念: 缓存击穿是指一个key非常热点，在不停的扛着大并发，大并发集中对这一个点进行访问，当这个key在失效的瞬间，持续的大并发就穿破缓存，直接请求数据库，瞬间对数据库的访问压力增大。还有就是冷门数据的访问导致的`
	   1. 加锁(互斥锁, 细粒度的锁等): 在查询缓存和查询数据库的过程中加锁, 只能第一个进来的请求进行执行，当第一个请求把该数据放进缓存中，接下来的访问就会直接集中缓存，防止了缓存击穿。
	   2. 设置热点数据永不过期
	4. 缓存雪崩
		`概念: 缓存雪崩 是指在某一个时间段，缓存集中过期失效。此刻无数的请求直接绕开缓存，直接请求数据库`
	   	* 原因:1. redis宕机;2. 大部分数据失效
		1. 设置不同的过期时间，防止同意之间内大量的key失效: 批量往 Redis 存数据的时候，把每个 Key 的失效时间都加个随机值就好了，这样可以保证数据不会再同一时间大面积失效。
		2. 搭建高可用的集群，防止单机的redis宕机: Redis 是集群部署，将热点数据均匀分布在不同的 Redis 库中也能避免全部失效。
		3. 设置热点数据永不过期，有更新操作就更新缓存

11. Redis的过期键删除策略
	1. 定时删除: 创建一个定时器，定时的执行对key的删除操作
	2. 惰性删除: 每次只有再访问key的时候，才会检查key的过期时间，若是已经过期了就执行删除。
	3. 定期删除: 每隔一段时间，就会检查删除掉过期的key。

12. redis的数据类型
	0. redis中核心对象redisObject表示所有的key和value. 用redisObject表示五种数据类型: String, Hash,List,Set,Zset
```java
redisObject{
	数据类型(type) // String, Hash,List,Set,Zset
	编码方式(encoding) //表示数据的存储方式:  embstr,raw,int,ht,linkedlist,ziplist,inset,skiplist
	数据指针(ptr) // 存储具体内容
	虚拟内存(vm)
	其他信息
 }
 ```
	1. 数据结构介绍
		1. hashtable: key(String), value(key,value); 
			* 哈希冲突后,形成单向链表(链地址法);
			* hash表的扩展和收缩: refresh: h[0]和h[1], 使用used值来扩容和收缩
			* 渐进式refresh: hash中数据量非常大的处理模式.h[0]和h[1]同时操作.
		2. ziplist: 是一组连续内存块组成的顺序的数据结构，压缩列表能够节省空间，压缩列表中使用多个节点来存储数据。 
			* 组成包含: zlbytes,zltail, zllen, entry1,entry2,...zlend.
			* entry包含: previous_entry_len, encoding, content
		3. linkedlist和quicklist: 双向链表, 有指向前后节点的指针. 插入,修改时间复杂度O(1). 查询是O(n)
		4. intset: 整数集合. 保存整数值的数据结构类型, int16_t,int32_t,int64_t
			* 组成: encoding, length, contents[]
		5. skiplist: 跳跃表, 有序的数据结构.它通过每一个节点维持多个指向其它节点的指针，从而达到快速访问的目的。
	2. 各数据类型对应的数据结构存储方式.
		1. String: int, raw(SDS), embstr(超过32字节的字符串数据用此种数据结构)
		2. Hash: ziplist, hashtable. 使用场景: 1.存储用户数据;2. 分布式生成唯一id
		3. List: 3.2之前(ziplist+linkedlist), 3.2之后(quicklist). 使用场景: lpush, brpop实现阻塞队列
		4. Set: hashtable,intset, 使用场景: 去重, 抽奖, 共同好友
		5. Zset: ziplist和skiplist ,使用场景: 排序
13. Redis的事务
	`概念: Redis事务是一组命令的集合，将多个命令进行打包，然后这些命令会被顺序的添加到队列中，并且按顺序的执行这些命令。`
	* 执行事务的步骤
	    0. watch: 指定监视某个key. (一旦key被其他客户端修改, 则exec会放弃执行队列中的命令)
		1. 开始事务(MULTI)
		2. 命令入队
		3. 执行事务(EXEC), 撤销事务(DISCARD)
		0. unwatch: 取消监视之前通过WATCH 命令监视的key，通过执行EXEC 、DISCARD 两个命令之前监视的key也会被取消监视

# MySQL
**参考文献**
1. [MySQL海量数据优化](https://mp.weixin.qq.com/s/oyC0ugJWhanpkpIay54ZFQ)
2. [数据库读写分离这个坑，让刚入职的我一脸懵逼](https://mp.weixin.qq.com/s/5E0ipyB9lzFwhI830RUiAw)
3. [我们为什么要分库分表？](https://mp.weixin.qq.com/s/-5lxXz8vU94KQMursEwPWg)
4. [请签收MySQL灵魂十连 ](https://mp.weixin.qq.com/s/h8GDJzCokDjM8OtTi3Ek-A)
5. [面试官：MySQL是如何保证不丢数据的？ ](https://mp.weixin.qq.com/s/2rsYy6QA5FQPqK9hjtvj6A)
6. [14 个必须掌握的数据库面试题](https://mp.weixin.qq.com/s/WxVR1ADB4usdKJfxYOWJmg)
7. [炸裂！MySQL 82 张图带你飞 ](https://mp.weixin.qq.com/s/QSQ1m_aHw4wBhv7_AQHDow)
8. 

## 问题
1. 事务四大特性
	* 原子性, 一致性,隔离性, 持久性
	

# Mybatis
**参考文献**
1. [MyBatis 的执行流程，写得太好了叭](https://mp.weixin.qq.com/s/WjsyxcofGtvD7NAbR83sBA)


# Zookeeper
> ZooKeeper 是 Apache 软件基金会的一个软件项目，它为大型分布式计算提供开源的分布式配置服务、同步服务和命名注册。ZooKeeper 曾经是 Hadoop 的一个子项目，但现在是一个顶级独立的开源项目。
> 一致性在 ZK 中是最终一致性，ZK 无法保证实时的强一致性，有一个时间窗口，但是最终 ZK 集群中的数据都会是一样的。
1. [欢迎来到 ZooKeeper 动物世界！](https://mp.weixin.qq.com/s/LchqL9XtNLop1Je-DMVGbw)
2. [分布式应用为啥要用ZooKeeper ？ZooKeeper 常见概念解读！](https://mp.weixin.qq.com/s/6TPXZPkRSTuT7kOeL9h4AA)

# RabbitMq
[RabbitMQ实现即时通讯居然如此简单！连后端代码都省得写了？](https://mp.weixin.qq.com/s/NU4go-JPNVyDNVFx_QcMQg)

# Linux
[还在百度Linux命令？推荐一套我用起来特顺手的命令](https://mp.weixin.qq.com/s/m0dFpUKuFsYN2HCK3gPOKQ)
[后端程序员必备的 Linux 基础知识+常见命令](https://mp.weixin.qq.com/s/Jl9EMt1fgceVDCakT4GeCA)
# 综合性题目
1. 缓存理解: [面试官:你是怎么理解缓存的](https://mp.weixin.qq.com/s/mzGrRcoBQeOL7XS3-OwAkw)
2. [如何进行数据库设计](https://mp.weixin.qq.com/s/BINugSuh4T1nyEr9HIkJFQ)
3. [高并发场景下，到底先更新缓存还是先更新数据库？ ](https://mp.weixin.qq.com/s/kJU3bJK1FNz6yhkxMQUHTw)
4. [面试官：面对千万级、亿级流量怎么处理？](https://mp.weixin.qq.com/s/SeamvHyHLMUF1vrhUy1BXg)
5. [分布式面试题集合](https://mp.weixin.qq.com/s/06mqY68_E6fj6mAb15RBQg)

# 工具
[Linux命令记不住怎么办？试试这个神奇的“小抄”工具！ ](https://mp.weixin.qq.com/s/2auw521R3e2js2j_fN0iBg)


