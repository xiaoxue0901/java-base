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
### Java的NIO模型
1. NIO+单线程Reactor模式
2. NIO+多线程Reactor模式
3. NIO+主从多线程Reactor模式

### 总结
同步IO和异步IO的区别: 数据访问的时候数据是否阻塞
阻塞IO和非阻塞IO的区别: 应用程序的调用是否立刻返回