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

## 8. JVM
**参考资料**
1. [JVM（8）：JVM 知识点总览 - 高级 Java 工程师面试必备 ](https://mp.weixin.qq.com/s/EDieD01pqJyMIAiTB3V4Eg)
2. [JVM（2）: JVM 内存结构 ](https://mp.weixin.qq.com/s/NpeHBU8VlGNPEMAIefgTvA)
3. [Java 类的加载机制 ](https://mp.weixin.qq.com/s/3LOSQnLNuaa-dEGXA-q4-w)
4. [JVM（3）：Java GC 算法垃圾收集器](https://mp.weixin.qq.com/s/-ujo_noQUM4KLqOPSbQdOw)
5. [JVM（4）：JVM 调优 - 命令篇](https://mp.weixin.qq.com/s/9iDFxy1N1YKYcCzvis-U6A)
6. [JVM（7）：JVM 调优 - 工具篇 ](https://mp.weixin.qq.com/s/1TmJvnof_yWDAuqffCVhOQ)
7. [JVM夺命连环10问](https://mp.weixin.qq.com/s/hXXcp7JiNxpFeUxSuHyXhQ)

# 设计模式
**参考资料**
1. [Spring 中经典的 9 种设计模式，打死也要记住啊！ ](https://mp.weixin.qq.com/s/-YkOeOk6t2fy7EruDdxigQ)
2. [设计模式之工厂模式](https://blog.csdn.net/can_chen/article/details/105924115)

# Mybatis
**参考文献**
1. [MyBatis 的执行流程，写得太好了叭](https://mp.weixin.qq.com/s/WjsyxcofGtvD7NAbR83sBA)
2. [Mybatis 缓存特性的使用及源码分析](https://mp.weixin.qq.com/s/GuBVXxJHSzgA0p607mpdtw)
3. [MyBatis 动态 SQL 底层原理分析 ](https://mp.weixin.qq.com/s/BLY1HZUtmYA_w1_MZfcYRQ)
4. [MyBatis 拦截器原理探究 ](https://mp.weixin.qq.com/s/nFdjgTrVrI0q90fZFUxm2w)
5. [MyBatis源码：原来 resultMap 解析完是这样](https://mp.weixin.qq.com/s/UmWct7D2KZZqwfHEfgWmvA)
6. [Mybatis TypeHandler 的简单应用及源码分析](https://mp.weixin.qq.com/s/GLb_-dLVAWYb6-_GWGIF8w)
7. [Mybatis 源码解读-设计模式总结](https://mp.weixin.qq.com/s/CGjr91c_6Zm4mF6t6gcv6A)

# Spring
**参考文献**
1.[万字 Spring Cloud Gateway2.0，面向未来的技术，了解一下？](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651485415&idx=2&sn=d0fe001960c1bc60131b4cbbd19e2e15&chksm=bd2518988a52918e4e51957acece723f04604f0c81c719eca5b1e3c03b407f0ec671c48efd5f&mpshare=1&scene=24&srcid=03037fpikP0aISD7szsOH2lz&sharer_sharetime=1614785067154&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[谈谈几个 Spring Cloud 常见面试题及答案](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651489281&idx=2&sn=613fd2403345382f4d20ab8673ad18ae&chksm=bd25e87e8a526168acfb33ddc0013d4c099c53072f3c7f3875f6c14a9fc3117a9d832802ae78&mpshare=1&scene=24&srcid=03035eCU84FaOd6IQW6TpAQH&sharer_sharetime=1614785003782&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[Spring 的 Bean 生命周期，11 张高清流程图及代码，深度解析](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651486951&idx=1&sn=cade003b51d142e26a515635e83bfaa2&chksm=bd2516988a529f8e01d3ee0e405a3f86c9427d390bb8af56f03b56828c245aa393c221970d4a&mpshare=1&scene=24&srcid=0303AftC9RfpkjdjhCDvss20&sharer_sharetime=1614784974796&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[从 Spring Cloud 看一个微服务框架的「五脏六腑」 ](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651480992&idx=1&sn=7dcd4df36a3bfcebacb6b6ec86d0a105&chksm=bd250fdf8a5286c9889403599073bc4cc7bcf133bf68b1c7e956efaca30f16e9e39402e7ac44&mpshare=1&scene=24&srcid=0303E4FWuEBP7cKmuXoy55yv&sharer_sharetime=1614784937636&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[拜托！面试请不要再问我Spring Cloud底层原理](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651482754&idx=1&sn=f9cfb4c5b978bfb8a470cd3df0eb487c&chksm=bd2506fd8a528feb330cd9c2a8eb8321ded24e7c6a73277c9ba67bd96c8d9061fe178842015c&mpshare=1&scene=24&srcid=0303dgKCdVptOieXb76lGbKk&sharer_sharetime=1614784757171&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[Spring Cloud 20000 字总结，收藏！](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651489181&idx=2&sn=3ba2e20f840766a39a585d4679c6e393&chksm=bd25efe28a5266f433243dcacaf7e038344737f858fc0087f9b8d7d79584037a1aebe8402678&mpshare=1&scene=24&srcid=0303rdOb9gvYJa8f0jRcrNmV&sharer_sharetime=1614784689200&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[《我想进大厂》之Spring夺命连环10问](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651491607&idx=1&sn=376f404ebd243247820f8c4ba29255f6&chksm=bd25e1688a52687e341e4221b46e7647f5fc1ddf4fcae40a40f8c06073f4101505dcddeda033&mpshare=1&scene=24&srcid=0303xPbLFy7kdP0O4Iqs1obD&sharer_sharetime=1614784597172&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[如果我是面试官，我会问你 Spring 那些问题？](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651485881&idx=1&sn=2669fc00cbd9609bca916df2c73db34e&chksm=bd251ac68a5293d0e7548f2421d6c8fe560ee9bcef01bd5bb06bab234e9ebe42bd05f2e71010&mpshare=1&scene=24&srcid=03031vcZlRLcUs5VgGFGyzqN&sharer_sharetime=1614784545070&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[浅析 Spring 中的事件驱动机制 ](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651479156&idx=2&sn=e84fd558bf849cef11c916938c78fdf9&chksm=bd25300b8a52b91d20b3b7d098618c646d5fe048ad081b39df6a9101e8c12994b8c23a52ae55&mpshare=1&scene=24&srcid=03032bfB8d0dAHhkCEM3Gr7c&sharer_sharetime=1614784461439&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[Spring 源码分析 : 非懒加载的单例 Bean 初始化过程 ( 上 ) ( 1 )](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651478432&idx=2&sn=8f79f37a2d45e11c799222ad292c9901&chksm=bd2535df8a52bcc9c0fcebd92094ec1b3b092a1fee60e51479971da20b14aa24d2e666d5faa4&mpshare=1&scene=24&srcid=0303fH83F4hff0QVV2cotf60&sharer_sharetime=1614784244372&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[Spring Security 的原理及教程（ 上 ） ](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651479090&idx=2&sn=ecfac563e4eeb7542a48fed4a16416a8&chksm=bd25304d8a52b95b83e186ab9b7c7773e3c9b893bee8b74c848e357e81bae05b738418bec15c&mpshare=1&scene=24&srcid=0303pF3fTtj1WmHUskOAI69M&sharer_sharetime=1614784180019&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[Spring 源码分析：Bean 加载流程概览及配置文件读取 ](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651478430&idx=1&sn=3580249620ed0e21c878234fcea9c08a&chksm=bd2535e18a52bcf75b06a57075ec55c320f90016c3aae484f03ee080861ecb615c0925b38e5f&mpshare=1&scene=24&srcid=0303lgGwvrJfDXxyeJqrVS1w&sharer_sharetime=1614784126632&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[Spring AMQP 错误处理策略详解 ](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651485787&idx=2&sn=95e7d96e7c90f51b617670b2cd3e85b2&chksm=bd251a248a529332b93c9611c7bbbc39164c8b7bfd12c81871cbd83b8f176b0e3432ecc01b6c&mpshare=1&scene=24&srcid=0303DN0Af8NgvSicGbIP8dRN&sharer_sharetime=1614784053354&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[Spring 核心 ：FactoryBean ](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651481445&idx=2&sn=0dc18771636d4b11e5a887f9258328c4&chksm=bd25091a8a52800cdc6195ac0e576361658a6d2b525a58ff2505238fa9d3cc86b965758b31c3&mpshare=1&scene=24&srcid=03031BT5q8qLdpswf1vryT4m&sharer_sharetime=1614784016325&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[聊一聊 Spring 中的线程安全性 ](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651481966&idx=1&sn=475e3e281ec6e15fb84e5aee5af40859&chksm=bd250b118a528207b9d939bff682398d0d5683ec365b9ccd19d7974504af51efe135ed33838c&mpshare=1&scene=24&srcid=0303J66U5T45j0W8k4IBBAmu&sharer_sharetime=1614783965424&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[Spring 中的统一异常处理 ](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651481803&idx=2&sn=cddfae6e0640ac9b1ac6a81480cdf8a4&chksm=bd250ab48a5283a297b39ce7c195f8f6dc0a783072487125dca54dffc6ded3d7693e01d96e3b&mpshare=1&scene=24&srcid=0303q33ifKxCNDLjmeVpTYsR&sharer_sharetime=1614783921238&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[Spring 源码探究 : 容器 ](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651482269&idx=1&sn=3864454746096db6830cd1a67edb7cce&chksm=bd2504e28a528df447564a655799c752f07ff7b71c26c40e5f4ea1cb8b0e3c1bbfe9ce0cd652&mpshare=1&scene=24&srcid=0303YyD25TVILFYtTR7zi9L7&sharer_sharetime=1614783882929&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[Spring Boot面试问题集锦 ](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651485632&idx=2&sn=4e68e0b5d88d59a028ebc5cb25b913bd&chksm=bd2519bf8a5290a979ccf87f17644d9b75126180b78e57a39e855b23b2ce18d8c0d8a2199403&mpshare=1&scene=24&srcid=0303OR1vHqYxWNI3LWNX8MCp&sharer_sharetime=1614783861646&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[Spring 事务管理 ](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651483890&idx=1&sn=a11cd4b321683b5673d0a08657db8689&chksm=bd25028d8a528b9b80dca38852dba6cace08226339125f5cf55adf9132033fba19080d6ae0da&mpshare=1&scene=24&srcid=0303QSqUM1LNGDugTAFBk4hE&sharer_sharetime=1614783783814&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[深入分析 Spring 框架中 AOP 与动态代理的整合原理 ](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651483455&idx=1&sn=d35ce888e60883c1f655cd04e03470fc&chksm=bd2501408a528856479995e0622249cea90cc24690daf05c0829b36c9b712f5399ae05657f11&mpshare=1&scene=24&srcid=0303IHOMYE6DgZTyIUZFbxKv&sharer_sharetime=1614783736991&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[Spring Boot：启动原理解析](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651483916&idx=2&sn=82d54598440a4cb409299b16d87a89fd&chksm=bd2503738a528a658af5468be80efd5a236cedc2c0f3f33d89a20c9049c8b77b4b694392ef9e&mpshare=1&scene=24&srcid=0303QrWZ4hgjVtZyWdv3alcx&sharer_sharetime=1614783692040&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[Spring Boot：启动原理解析](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651486250&idx=2&sn=36484c1182a5454d2f6245d707e56ff0&chksm=bd2514558a529d43e581fc5d049119e7ff2a898b972318b11ba535b80ab4afaf9123cf07e274&mpshare=1&scene=24&srcid=0303nBHy4BvzYya9A6MtnrPf&sharer_sharetime=1614783652450&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[Spring AOP是什么？你都拿它做什么？](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651482435&idx=1&sn=4124d3d4a11d10c1398f7d2934591a64&chksm=bd25053c8a528c2aa4b9c562e42b201e9e0c139bb659919c9b1b73edae3d566ad9eb3230baf0&mpshare=1&scene=24&srcid=030368NPZpWE3VhUJJAXgEzd&sharer_sharetime=1614783612896&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[Spring 知识点提炼](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651479323&idx=1&sn=a2d5cc1af9409afd9769821571cdba1e&chksm=bd2531648a52b8726bb04432543df9d2764133e45a0fd2eb4a37e970d06eee63cbb526480201&mpshare=1&scene=24&srcid=0303dBq4PCYbgTpuINoyoAz2&sharer_sharetime=1614783500932&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[Spring AOP 的实现机制 ](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651480861&idx=1&sn=ed21a270d10bb735eb5b4519306b0e61&chksm=bd250f628a528674717608187947d98befdb816007353f7d344ffaa53cec8f284e6e70bc06c2&mpshare=1&scene=24&srcid=0303FPvu68qlWoJGn0RD89cj&sharer_sharetime=1614783455382&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[Spring AOP 的实现原理](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651480177&idx=1&sn=c2d7772af7285a60bcb2cadd2d7a79df&chksm=bd250c0e8a52851832f08635f13f48fe682aa0655335e0998eae7e3fa94c07710655ef2cf240&mpshare=1&scene=24&srcid=03032Dc4qzFUs4tv6FdvimSS&sharer_sharetime=1614783432082&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[你必须要懂的 Spring：Aop](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651484616&idx=1&sn=feb6275ead51abfab94e4eda4b395ec0&chksm=bd251db78a5294a10a80da01732ce20eb61908ab70243a63228a0e83898a06dbc4b0b285cfb2&mpshare=1&scene=24&srcid=0303mN1QRoCGbyI7HTeJIZWh&sharer_sharetime=1614783409854&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[下次面试别再问我什么是 Spring AOP 和代理了！](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651482306&idx=1&sn=38cb83c3b1b646814096e78a89db3cf1&chksm=bd2504bd8a528dab8bfaba33ba8b35edc92dcb9124fff107348d634e33b0a58db22de0300d80&mpshare=1&scene=24&srcid=0303vOnI445zE9I74rUbIHib&sharer_sharetime=1614783378632&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[面试必问：Spring循环依赖的三种方式 ](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651482735&idx=1&sn=5c7ed61dc9b1f661f68ac6e17338d1c4&chksm=bd2506108a528f062720bb9e79f6528abfaa27cd4e8cd457aaf1a2fa8022564c09a748fe671a&mpshare=1&scene=24&srcid=0303CJn5Cw8orHFAAraMYdKw&sharer_sharetime=1614783349066&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[Spring 面试问题 TOP 50 ](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651481817&idx=1&sn=5d9271fd8e531f405185734a42dab6c3&chksm=bd250aa68a5283b03f655c449c83a7a00965d40d2cb24b8cb7acc0e9be8999387e5d9e9692f6&mpshare=1&scene=24&srcid=0303fAvGsjUnrK7XqLCuNdtG&sharer_sharetime=1614783312184&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[图解 Spring 循环依赖，写得太好了！](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651488388&idx=1&sn=f1e97aa73c5965a375e3741b9fb7a8fd&chksm=bd25ecfb8a5265ed1054cec40e76e208524b09ec3ddd1d6595ab9e1e133a3629f35802228814&mpshare=1&scene=24&srcid=0303GNOBKqGHMGS2Uk2KCRDn&sharer_sharetime=1614783271236&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[Spring 的 Controller 是单例还是多例？怎么保证并发的安全](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651491932&idx=1&sn=488ccf2966cd8266226cdd456fc42380&chksm=bd25e2238a526b35aea107834c93ddbe130ca1e2f60f69e47932a7942c0e319cc869a42ef507&mpshare=1&scene=24&srcid=0303Yd7eXbcS88YoldAhgNdX&sharer_sharetime=1614783230890&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[面试官: 讲讲 Spring 事务有哪些坑?](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651491027&idx=2&sn=f5eb01fbb179efa727aed2e8f147f496&chksm=bd25e6ac8a526fba3d6a88f5129c9f3592cbc92e57759a32b005248baa54775fde5c999c4c62&mpshare=1&scene=24&srcid=0303iGs01LN3PLhgdlxuv2cO&sharer_sharetime=1614783207278&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[3W 字的 Spring Boot 超详细总结 ](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651490602&idx=1&sn=6a7c5440d02bd860ab03cb204855a968&chksm=bd25e5558a526c43ea7df1074296300f7c26c36363208f38bdc60331c9bf2343077a52347f14&mpshare=1&scene=24&srcid=0303JPSWjIiyh1En97y1l1Cg&sharer_sharetime=1614783160289&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)

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
8. [很用心的为你写了 9 道 MySQL 面试题](https://mp.weixin.qq.com/s/EDWYhnqDf0WGIbkpq_m3RQ)
9. [MySQL常见面试题](https://sowhat.blog.csdn.net/article/details/71158104)

**总结**
1. MySQL事务四大特性
	`事务概念: 事务是一组操作，组成这组操作的各个单元，要不全都成功要不全都失败，这个特性就是事务。`
   	1. 原子性: 原子性指的就是 MySQL 中的包含事务的操作要么全部成功、要么全部失败回滚
	2. 一致性: 一致性指的是一个事务在执行前后其状态一致。两人转账后总额不变
	3. 持久性: 持久性指的是一旦事务提交，那么发生的改变就是永久性的，即使数据库遇到特殊情况比如故障的时候也不会产生干扰。
	4. 隔离性: 当多个事务同时进行时，就有可能出现脏读(dirty read)、不可重复读(non-repeatable read)、幻读(phantom read) 的情况，为了解决这些并发问题，提出了隔离性的概念。
2. MySQL的隔离级别
	1. 读未提交(read-uncommited)-问题:脏读: 读未提交指的是一个事务在提交之前，它所做的修改就能够被其他事务所看到。
	2. 读已提交(read-commited)-问题:不可重复读(数据不一致): 读已提交指的是一个事务在提交之后，它所做的变更才能够让其他事务看到。
	3. 可重复读(repeatable-read)-问题: 幻读: 可重复读指的是一个事务在执行的过程中，看到的数据是和启动时看到的数据是一致的。未提交的变更对其他事务不可见。
	4. 串行化(serializable)-问题:性能差: 对于同一行记录，写会加写锁，读会加读锁。当出现读写锁冲突的时候，后访问的事务必须等前一个事务执行完成，才能继续执行。
3. MySQL的基础架构
	* 连接器: 管理连接, 包括权限认证
	* 查询缓存: key-value结构,存在内存中. 不建议开启
	* 分析器: 词法分析, 语法分析
	* 优化器: 执行计划, 选择索引
	* 执行器: 执行SQL, 返回结果
	* 存储引擎: 存储数据,提供读写接口
4. SQL的执行顺序	
	`FROM(笛卡尔积)->ON->JOIN->WHERE->GROUP BY(计算表达式)->HAVING->SELECT->DISTINCT->UNION->ORDER BY->LIMIT`
5. 使用UNION和UNION ALL
	1. 通过 union 连接的 SQL 分别单独取出的列数必须相同
	2. 使用 union 时，多个相等的行将会被合并，由于合并比较耗时，一般不直接使用 union 进行合并，而是通常采用 union all 进行合并
6. SQL优化经验
    0. 使用 EXPLAIN 命令优化你的 SELECT 查询，对于复杂、效率低的 sql 语句，我们通常是使用 explain sql 来分析这条 sql 语句，这样方便我们分析，进行优化。
	1. 查询语句无论是使用哪种判断条件 等于、小于、大于， WHERE 左侧的条件查询字段不要使用函数或者表达式
	2. 当你的 SELECT 查询语句只需要使用一条记录时，要使用 LIMIT 1
	3. 不要直接使用 SELECT *，而应该使用具体需要查询的表字段，因为使用 EXPLAIN 进行分析时，SELECT * 使用的是全表扫描，也就是 type = all。
	4. 为每一张表设置一个 ID 属性
	5. 避免在 WHERE 字句中对字段进行 NULL 判断, 不使用!=或<>,  not in
	6. 为搜索字段创建索引
	7. 使用 LIKE %abc% 不会走索引，而使用 LIKE abc% 会走索引
	8. 选择合适的字段类型，选择标准是 尽可能小、尽可能定长、尽可能使用整数。
	9. 分库分表, 分表: 进行水平切割(通过建立结构相同的几张表分别存储数据)或者垂直分割(将经常一起使用的字段放在一个单独的表中，分割后的表记录之间是一一对应关系。)
7.MySQL优化
	 `SQL优化主要分4个方向：SQL语句跟索引、表结构、系统配置、硬件; 优化思路:最大化利用索引、尽可能避免全表扫描、减少无效数据的查询`  
	  1. SQL语句优化大致举例
		 1、合理建立覆盖索引：可以有效减少回表。
		 2、union，or，in都能命中索引，建议使用in
   		 3、负向条件(!=、<>、not in、not exists、not like 等) 索引不会使用索引，建议用in。
   		 4、在列上进行运算或使用函数会使索引失效，从而进行全表扫描
   		 5、小心隐式类型转换，原字符串用整型会触发CAST函数导致索引失效。原int用字符串则会走索引。
   		 6、不建议使用%前缀模糊查询(左%会使索引失效)。避免使用or, 使用or不会命中索引.
   		 7、多表关联查询时，小表在前，大表在后。在 MySQL 中，执行 from 后的表关联查询是从左往右执行的(Oracle 相反)，第一张表会涉及到全表扫描。
   		 8、调整 Where 字句中的连接顺序，MySQL 采用从左往右，自上而下的顺序解析 where 子句。根据这个原理，应将过滤数据多的条件往前放，最快速度缩小结果集。
		 9. 使用复合索引时遵循最左匹配原则
	  2. SQL调优大致思路
		 1、先用慢查询日志定位具体需要优化的sql
		 2、使用 explain 执行计划查看索引使用情况(key,key_len,type,extra)
		 3. 表结构优化
			1. 尽量使用TINYINT、SMALLINT、MEDIUM_INT作为整数类型而非INT，如果非负则加上UNSIGNED
			2. VARCHAR的长度只分配真正需要的空间 。
			3. 尽量使用TIMESTAMP而非DATETIME 。
			4. 单表不要有太多字段，建议在20以内。
			5. 避免使用NULL字段，很难查询优化且占用额外索引空间。字符串默认为''
			6. 使用枚举或整数代替字符串类型
		 4. 读写分离
		 5. 分库分表
			1. 垂直分库：将应用分为若干模块，比如订单模块、用户模块、商品模块、支付模块等等。其实就是微服务的理念。
			2. 垂直分表：基于表或字段划分，表结构不同。一般将不常用字段跟数据较大的字段做拆分。
			3. 水平分表:于数据划分，表结构相同，数据不同。
		 6. 使用缓存
			1. 服务层使用缓存(直写式[在数据写入数据库后，同时更新缓存，维持数据库与缓存的一致性], 回写式[当有数据要写入数据库时，只会更新缓存，然后异步批量的将缓存数据同步到数据库上]), 数据访问层.mybatis
			2. web层
			3. 浏览器客户端
		
8.分页查询优化,`select * from t_user where id limit 1000000,10`
	1. 子查询优化. `select * from t_user where id >=(select id from t_user order by id limit 1000000,1) limit 10`
	2. 使用join分页: `select * from t_user t1 inner join (select id from t_user order by id limit 9000000,10) t2 on t1.id=t2.id`
	3. 使用前一次查询的最大ID: `select* from t_user where id>=9999990 order by id limit 10`
	4. 通过伪列对id分页: 通过伪列划分区间:0-1000;1001-2000..., 再开启多线程进行批量查询. 这样可以快速把全量数据查询出来.
		`SELECT id FROM
		(SELECT @rownum :=@rownum +1 AS rownum, id FROM t_user t1, (SELECT @rownum := 0) t2 ORDER BY t1.id asc) t3
		WHERE t3.rownum % 1000 =0;`
9. 快速获取海量数据并加载到缓存
	* 使用伪列对ID进行分页，然后开启多个线程同时查询，把全量数据加载到缓存
10. 读写分离情况下, 主从延时解决方法
	1. 数据库同步写: 主库写的操作的数据同步到从库后, 写操作才能完成
	2. 选择行强制读主库
	3. 中间件选择路由法: 
	   * 读写通过中间件来操作, 写操作记录key, 将数据同步给从库后,删除key, 
	   * 读操作先检查key, key存在,读主库, 不存在, 读从库
	4. 缓存路由法
		* 写操作, 记录key, 设置失效时间为主从延时时间, 异步将数据同步给从库
		* 读操作, key存在, 读主库; key不存在, 读从库;
11. MySQL与PostgreSQL的区别
	[postgreSQL与MySQL的比较](https://blog.csdn.net/u012679583/article/details/78291846)
	* PG具备更高的可靠性，对数据一致性完整性的支持高于MySQL，因此PG更加适合严格的企业应用场景（比如金融、电信、ERP、CRM）；
	* MySQL查询速度较快，更加适合业务逻辑相对简单、数据可靠性要求较低的互联网场景（比如google、facebook、alibaba）。

	
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


