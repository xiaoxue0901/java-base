#netty学习笔记

### Unix的5种IO模型
#### 同步阻塞模型
1. 阻塞I/O
2. 非阻塞I/O
3. I/O复用
4. 信号驱动的I/O
5. 异步I/O
前4个是同步的; 最后一个是异步的.

### Java的BIO模型
1. 原始版本: client->Accpter->一个Client请求创建一个线程
2. 升级版本: client->Accpter->线程池-> 创建线程处理Client请求

3. accept()阻塞原因: 获取句柄是加锁的.synchronized代码块.socketAccept()方法前后分别加锁和释放锁.
### Java的NIO模型
1. NIO+单线程Reactor模式
2. NIO+多线程Reactor模式
3. NIO+主从多线程Reactor模式

### 总结
同步IO和异步IO的区别: 数据访问的时候数据是否阻塞
阻塞IO和非阻塞IO的区别: 应用程序的调用是否立刻返回

### NIO
channel, buffer, selector, reactor模型
`概述: 1.什么是NIO? 2. 内核空间与用户空间 3. 内核缓冲与进程缓冲 4. 同步与异步 5. 阻塞与非阻塞`
IO分类
BIO: 同步阻塞
NIO: 同步非阻塞
AIO: 异步非阻塞
java NIO核心组件
channel: 面向Buffer的通道, 和Buffer一起操作. 操作一块内容, 双向: 可以写,可以读
Buffer: channel之间通过Buffer操作数据
Selector: 单线程轮询的模式, 找操作系统, 询问之前发起的交易操作结果
unsafe: 通过堆外内存, 操作底层硬件.

TCP:
    ServerSocketChannel
    SocketChannel
UDP:     
DatagramChannel
面向文件操作: 
FileChannel

Java NIO Buffer
指针
* capacity
* position
* limit
* mark

flip(): position和limit, 
读写模式: 

## Netty
Netty概述: 
Netty组件: Channel, ByteBuf, NIOEventLoop, ChannelHandler, Pipeline
handler和childHandler的区别
服务端的socket是在哪里初始化的?
netty服务端启动多少个线程
netty是如何解决JDK空轮询的bug
netty是如何保证异步串行无锁化?
config: NioServerSocketChannel
从bind()开始整理源码流程

代码: 1. Buffer, FileChannel的使用; 
2. NIO通信实现聊天室
3. Netty通行的demo
4. bind()开始看源码
5. CountDownLatch()
6. TCP/IP完善.
理论知识: BIO模型和NIO模型








