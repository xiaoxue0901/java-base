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
1. [全方位解析java序列化](https://mp.weixin.qq.com/s/uWwim8QJ2xtmKEn4xsnWyw)
### 问题   
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
**参考资料**
1. queue: [45张图庖丁解牛18种Queue，给你整得明明白白！](https://mp.weixin.qq.com/s/CAhPCb81jS6MXMTA76k_uA)
2. 集合: [21张图带你领略集合的线程不安全 ](https://mp.weixin.qq.com/s/qJ0ELP4m9x8tESLXwlhpmw)
3. volatile: [​反制面试官 | 14张原理图 | 再也不怕被问 volatile! ](https://mp.weixin.qq.com/s/NuC06tTCe5spYWYv_23DmA)
4. CAS: [CAS原理太简单？](https://mp.weixin.qq.com/s/nLfvnhrbYkrq-rR36OlIwA)
5. ABA: [ABA原理](https://mp.weixin.qq.com/s/VMdWneLOMtJEFwgOaFfFKg)
6. 锁: [5000字 | 24张图带你彻底理解Java中的21种锁](https://mp.weixin.qq.com/s/1cWszX8MWXq_XNMXNyeDMg)
7. 队列: [10分钟搞定 Java 并发队列好吗？好的！](https://mp.weixin.qq.com/s/TYDZy0O0CXFwiKU1zcOt5A)

### 问题
1. CAS是什么? 原理?
    1.  全称:Compare and Swap.Compare-And-Swap（比较并交换）。比较变量的现在值与之前的值是否一致，若一致则替换，否则不替换。
    2. 作用: 原子性更新变量值，保证线程安全。
    3. CAS指令：需要有三个操作数，变量的当前值（V），旧的预期值（A），准备设置的新值（B）
    4. CAS指令执行条件：当且仅当V=A时，处理器才会设置V=B，否则不执行更新。
    5. CAS是一种系统原语，原语属于操作系统用于范畴，是由若干条指令组成，用于完成某个功能的一个过程，并且原语的执行必须是连续的，在执行过程中不允许被中断，所以CAS是一条CPU的原子指令，不会造成所谓的数据不一致的问题，所以CAS是线程安全的。
    6. 原理: 通过do..while来自旋实现, 1. 从主内存中拿到V值. 2. 判断: 用旧值A与V判断,一致则将V改为B, 结束自旋. 
    7. CAS会带来ABA的问题
2. ABA问题
    `概念: CAS只管开头和结尾，中间过程不关心，只要头尾相同，则认为可以进行修改，而中间过程很可能被其他人改过。`
    1. ABA 问题的危害
        * 带有消耗类的场景，比如库存减少，商品卖出。这种情况会消耗资源, 数据不一致. 
    2. 如何解决ABA问题  
        * 思路: 使用CAS+版本号.
        * 具体: 用带版本号的原子引用类AtomicStampedReference  
## 8. JVM
**参考资料**
1. [JVM（8）：JVM 知识点总览 - 高级 Java 工程师面试必备 ](https://mp.weixin.qq.com/s/EDieD01pqJyMIAiTB3V4Eg)
2. [JVM（2）: JVM 内存结构 ](https://mp.weixin.qq.com/s/NpeHBU8VlGNPEMAIefgTvA)
3. [Java 类的加载机制 ](https://mp.weixin.qq.com/s/3LOSQnLNuaa-dEGXA-q4-w)
4. [JVM（3）：Java GC 算法垃圾收集器](https://mp.weixin.qq.com/s/-ujo_noQUM4KLqOPSbQdOw)
5. [JVM（4）：JVM 调优 - 命令篇](https://mp.weixin.qq.com/s/9iDFxy1N1YKYcCzvis-U6A)
6. [JVM（7）：JVM 调优 - 工具篇 ](https://mp.weixin.qq.com/s/1TmJvnof_yWDAuqffCVhOQ)
7. [JVM夺命连环10问](https://mp.weixin.qq.com/s/hXXcp7JiNxpFeUxSuHyXhQ)
8. [这些不可不知的JVM知识，我都用思维导图整理好了 ](https://mp.weixin.qq.com/s/oyxKUxdJRLOjdM8a6fCQYA)

**问题**
1. 说说JVM的内存布局?
    1. 虚拟机栈
        1. 栈是线程私有的内存区域, 每个方法在执行的时候都会在栈创建一个栈帧.方法的调用过程就对应着栈的入栈和出栈的过程
        2.  每个栈帧的结构又包含局部变量表、操作数栈、动态连接、方法返回地址。
    2. 堆
        1. 堆是 Java 虚拟机中最大的一块内存，是线程共享的内存区域，基本上所有的对象实例数组都是在堆上分配空间。
        2. 堆区细分为 Young 区年轻代和 Old 区老年代，其中年轻代又分为 Eden、S0、S1 3个部分，他们默认的比例是 8:1:1 的大小。
    3. 元数据区(方法区-永久代)
        1. 包含类的元信息和运行时常量池。
        2. class 文件就是类和接口的定义信息。
        3. 运行时常量池就是类和接口的常量池运行时的表现形式。
    4. 本地方法区
        * 主要用于执行本地 native 方法的区域。
    5. 程序计数器
        * 也是线程私有的区域，用于记录当前线程下虚拟机正在执行的字节码的指令地址。
2. 知道new一个对象的过程吗?
    * 当虚拟机遇见 new 关键字时候，实现判断当前类是否已经加载。如果类没有加载，首先执行类的加载机制，加载完成后再为对象分配空间、初始化等。
        1. 首先校验当前类是否被加载，如果没有加载，执行类加载机制；
        2. 类加载机制
            1. 加载：就是从字节码加载成二进制流的过程；
            2. 验证：当然加载完成之后，当然需要校验 class 文件是否符合虚拟机规范.(文件格式验证,元数据验证, 字节码验证,符号引用验证)         
            3. 准备: 为类的静态变量分配内存，并将其初始化为默认值; final static 常量显示赋值
            4. 解析：把常量池中符号引用(以符号描述引用的目标)替换为直接引用(指向目标的指针或者句柄等)的过程； 
            5. 初始化: 对类变量进行初始化。如果存在父类，先对父类进行初始化。
        3. 对象分配内存空间和初始化的过程
            1. 为对象分配合适大小的内存空间；
            2. 为实例变量赋默认值；
            3. 设置对象的头信息，对象 hashcode、GC 分代年龄、元数据信息等；
            4. 执行构造函数 (init) 初始化  
3. 知道双亲委派模型吗?
    1. 类加载器自顶向下分为:
       1. Bootstrap ClassLoader（启动类加载器）：默认会去加载 JAVA_HOME/lib 目录下的 jar；
       2. Extention ClassLoader（扩展类加载器）：默认去加载 JAVA_HOME/lib/ext 目录下的 jar；
       3. Application ClassLoader（应用程序类加载器）：比如我们的 Web 应用，会加载 Web 程序中 ClassPath 下的类；
       4. User ClassLoader（用户自定义类加载器）：由用户自己定义。
    2. 在加载类的时候，首先都会向上询问自己的父加载器是否已经加载。如果没有则依次向上询问；如果没有加载，则从上到下依次尝试是否能加载当前类，直到加载成功。
4. 说说有哪些垃圾回收算法          
    1. 标记-清除算法
        * 缺点: 
            1.标记的过程需要遍历所有的 GC ROOT，清除的过程也要遍历堆中所有的对象, 效率低下
            2. 带来内存碎片的问题.
    2. 复制算法
        * 缺点
            1. 可使用内存空间缩小了一半.
    3. 标记-整理算法
    4. 分代收集算法: 把Java堆分成新生代和老年代. 根据各年代的特点采用最适当的收集算法.
5. 什么是GC ROOT? 有哪些GC ROOT?
    1. 如何判断一个对象是否存活?
        1. 引用计数法
        2. 可达性分析算法
    2. 可以作为GC ROOT的对象包含: 
        1. 栈中引用的对象
        2. 静态变量, 常量引用的对象
        3. 本地方法栈native方法引用的对象.                
6. 垃圾收集器? 
    1. Serial收集器
        * 使用一个线程去回收. 新生代和老年代串行回收. 新生代复制算法、老年代标记-整理；垃圾收集的过程中会Stop The World（服务暂停）
        * 参数控制：-XX:+UseSerialGC  串行收集器
    2. ParNew收集器
        * Serial收集器的多线程版本。新生代并行，老年代串行；新生代复制算法、老年代标记-整理算法
        *  参数控制
            -XX:+UseParNewGC  ParNew收集器
            -XX:ParallelGCThreads 限制线程数量 
    3. Paraller收集器
        * 类似ParNew收集器，Parallel收集器更关注系统的吞吐量。
        * 可以通过参数来打开自适应调节策略
            1. 动态调整参数以提供最合适的停顿时间或最大的吞吐量.
            2. 通过参数控制GC的时间不大于多少毫秒或比例
        * 参数控制：-XX:+UseParallelGC  使用Parallel收集器+ 老年代串行             
    4. CMS
        * CMS 收集器是以获取最短停顿时间为目标的收集器。相对于其他的收集器 STW 的时间更短暂，可以并行收集是它的特点，同时它基于标记-清除算法。
        * GC过程
            1. 初始标记:标记 GC ROOT 能关联到的对象，需要 STW；
            2. 并发标记:从 GCRoots 的直接关联对象开始遍历整个对象图的过程，不需要 STW；
            3. 重新标记:为了修正并发标记期间，因用户程序继续运作而导致标记产生改变的标记，需要 STW；
            4. 并发清除:清理删除掉标记阶段判断的已经死亡的对象，不需要 STW。
    5. G1: G1 收集器是 JDK9 的默认垃圾收集器，不再区分年轻代和老年代进行回收。        
7. 年轻代和老年代都有哪些垃圾回收器?
    1. 年轻代的垃圾收集器包含有 Serial、ParNew、Parallel。
    2. 老年代则包括 Serial Old 老年代版本、CMS、Parallel Old 老年代版本和 JDK11 中全新的 G1 收集器。
8. 什么时候会触发YGC和FGC?对象什么时候进入老年代?
    1. YGC: 当一个新的对象来申请内存空间的时候，如果 Eden 区无法满足内存分配需求，则触发 YGC。使用中的 Survivor 区和 Eden 区存活对象送到未使用的 Survivor 区。
    2. FGC: 如果 YGC 之后还是没有足够空间，则直接进入老年代分配。如果老年代也无法分配空间，触发 FGC，FGC 之后还是放不下则报出 OOM 异常。
    3. 对象什么时候进入老年代
        * 一直在 Survivor 区来回复制的对象，通过 -XX：MaxTenuringThreshold 配置交换阈值，默认15次。如果超过次数同样进入老年代。
        * 动态年龄的判断机制: 如果在 Survivor 空间中相同年龄所有对象大小的总和大于 Survivor 空间的一半，年龄大于或等于该年龄的对象就可以直接进入老年代。
9. 频繁FullGC怎么排查?
    1. 内存分配不合理，比如 Eden 区太小，导致对象频繁进入老年代. 通过启动参数配置可以查看出来
    2. 存在内存泄漏.   可以通过下列步骤排查: 
        1. jstat -gcutil 或者查看 gc.log 日志，查看内存回收情况。      
        2. dump 出内存文件在具体分析。
10. JVM调优经验
    1. 几个主要参数含义
            -Xms 设置初始堆的大小，-Xmx 设置最大堆的大小；
            -XX:NewSize 年轻代大小，-XX:MaxNewSize 年轻代最大值，-Xmn 则是相当于同时配置 -XX:NewSize 和 -XX:MaxNewSize 为一样的值；
            -XX:NewRatio 设置年轻代和年老代的比值。如果为3，表示年轻代与老年代比值为 1:3，默认值为2；
            -XX:SurvivorRatio 年轻代和两个 Survivor 的比值。默认值为8，代表比值为 8:1:1；
            -XX:PretenureSizeThreshold 当创建的对象超过指定大小时，直接把对象分配在老年代；
            -XX:MaxTenuringThreshold 设定对象在 Survivor 复制的最大年龄阈值，超过阈值转移到老年代；
            -XX:MaxDirectMemorySize 当 Direct ByteBuffer 分配的堆外内存到达指定大小后，即触发 Full GC。
    2. 调优
            为了打印日志方便排查问题最好开启GC日志。开启GC日志对性能影响微乎其微，但是能帮助我们快速排查定位问题。-XX:+PrintGCTimeStamps -XX:+PrintGCDetails -Xloggc:gc.log
            一般设置 -Xms=-Xmx。这样可以获得固定大小的堆内存，减少 GC 次数和耗时，可以使得堆相对稳定；
            -XX:+HeapDumpOnOutOfMemoryError 让 JVM 在发生内存溢出的时候自动生成内存快照，方便排查问题；
            -Xmn 设置新生代的大小。太小会增加 YGC，太大会减小老年代大小，一般设置为整个堆的1/4到1/3；
            设置 -XX:+DisableExplicitGC 禁止系统 System.gc()。防止手动误触发 FGC 造成问题。                    

## 9. IO
1. Linux中IO介绍
    1. Linux中的用户空间和内核空间
        1. 内核空间(kernel space): 内核代码运行的空间.当内核运行在内核空间时就处于内核态;可以执行任意指令, 调用系统的一切资源.
        2. 用户空间(user space): 用户程序代码运行的空间.当内核运行在用户空间时就处于用户态;只能执行简单的运算指令, 不能直接调用系统资源. 要通过系统接口, 才能向内核发出指令.
    2. PIO和DMA(Direct Memory Accesss)    
        1. PIO: 读取磁盘文件到内存中，数据要经过CPU存储转发
        2. DMA(直接内存访问): DMA可以不经过CPU而直接进行磁盘 和内存（内核空间）的数据交换。
    3. 缓存IO和直接IO
        1. 缓存IO(默认IO操作): 数据从磁盘先通过DMA copy到内核空间，再从内核空间通过cpu copy到用户空间.
        2. 直接IO: 数据从磁盘通过DMA copy到用户空间
    4. IO访问方式
        1. 磁盘IO: 
            * read->高速页缓存(内核)->磁盘; 
            * write->用户地址空间复制到内核缓存---->操作系统异步将数据写入磁盘, 除非显示的调用了sync()方法才会同步.
        2. 网络IO: 
            * read->DMA copy 数据到内核空间->CPU控制将内核数据copy到用户模式下的buffer; 
            * write->用户模式下buffer中的数据DMA方式copy到内核的socket buffer中->通过DMA copy将内核模式下的socket buffer中的数据copy到网卡设备中传送。 
        3. 磁盘IO和网络IO对比    
            1. 磁盘IO主要的延时是由（以15000rpm硬盘为例）： 机械转动延时决定.(机械磁盘的主要性能瓶颈+寻址延时+块传输延时)
            2. 网络IO主要延是由： 服务器响应延时 + 带宽限制 + 网络延时 + 跳转路由延时 + 本地接收延时 决定。
            3. 总结: 两者一般来说网络IO延时要大于磁盘IO的延时
2. 同步IO和异步IO
	* `同步和异步是针对应用程序和内核的交互(用户空间和内核空间数据交互的方式)`
	* 同步: 指的是用户进程触发IO操作并等待或者轮询的去查看IO操作是否就绪. (用户空间要的数据, 必须等到内核空间给它才做其他事情)
	* 异步: 指用户进程触发IO操作以后便开始做自己的事情, 当IO操作完毕的时候会得到IO完成的通知. (内核空间要的数据, 不必等到内核空间给它. 而是内核空间完成后会通知用户进程,并把数据直接给到用户空间)
3. 阻塞IO和非阻塞IO	: 指的是用户空间和内核空间IO操作的方式
	* `阻塞方式下读取或者写入函数将一直等待; 非阻塞方式下: 读取或者写入的函数会立刻返回一个状态值`
	* 阻塞: 用户空间通过系统调用(system call)向内核空间发送IO操作时, 该调用是阻塞的.
	* 非阻塞: 用户通过系统调用想内核空间发送IO操作时, 该调用是不阻塞的, 直接返回的, 只是返回的可能没有数据.
4. IO设计模式	
	1. Reactor: 反应器设计模式是一种为处理并发服务请求，并将请求提交到
	   一个或者多个服务处理程序的事件设计模式。当客户端请求抵达后，服务处理程序
	   使用多路分配策略，由一个非阻塞的线程(Boss)来接收所有的请求，然后派发这些请求至
	   相关的工作线程(worker)进行处理。
	   * 服务端启动一条单线程, 用于轮询IO操作是否就绪, 当有就绪的才进行相应的读写操作, 这样就减少了服务器产生大量线程, 也不会出现线程直接切换产生的性能消耗
	2. Proactor:运用于异步I/O操作，Proactor模式中，应用程序不需要进行实际的读写过程，它只需要从缓存区读取或者写入即
	   可，操作系统会读取缓存区或者写入缓存区到真正的IO设备.
5. IO模型
	1. 同步阻塞IO(Blocking IO->BIO): 传统IO模型
	2. 同步非阻塞IO(Non-blocking IO):  默认创建的socket都是阻塞的，非阻塞IO要求socket被设置为NONBLOCK。注意这里所说的NIO并非Java的NIO（New IO）库。
	3. IO多路复用(IO Multiplexing-NIO): 经典的Reactor设计模式,包含selector,poll, epoll, 也称为异步阻塞IO,Java中的selector和Linux中的epoll都是此模型 
	4. 异步IO(Asynchronous IO->AIO): 经典的Proactor设计模式. 异步非阻塞IO
6. IO多路复用技术及epoll实现原理                                                                                                                             
	1. select，poll，epoll都是IO多路复用的机制。I/O多路复用就通过一种机制，可以监视多个描述符，一旦某个 描述符就绪，能够通知程序进行相应的操作。
	   * 在select/poll时代，服务器进程每次都把这100万个连接告诉操作系统(从用户态复制句柄数据结构到内核态)，让
		 操作系统内核去查询这些套接字上是否有事件发生，轮询完后，再将句柄数据复制到用户态，让服务器应用程序轮询
		 处理已发生的网络事件，这一过程资源消耗较大，因此，select/poll一般只能处理几千的并发连接。
	2. redis的io模型主要是基于epoll实现的
		* epoll是poll的一种优化，返回后不需要对所有的fd进行遍历，在内核中 维持了fd的列表。select和poll是将这个内核列表维持在用户态，然后传递到内核中。与poll/select不同，
		  epoll不再是一个单独的系统调用，而是由epoll_create/epoll_ctl/epoll_wait三个系统调用组成，后面将会
		  看到这样做的好处。epoll在2.6以后的内核才支持。	



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
8. [超经典的 25 道 MyBatis 面试题！](https://mp.weixin.qq.com/s/j2Tw4FEJZ5krRpXZp6KGHw)
**问题**
1. 什么是 Mybatis？
	1. Mybatis是一个半ORM(OBJECT RELATION MAPPER)框架, 内部封装了JDBC.
	2. mybatis可以使用xml或注解来配置和映射原生信息, 将数据库表映射成POJO对象. 
	3. 通过xml或注解的方式将各种要执行的statement封装起来, 并通过java对象和statement中的sql的动态参数进行映射生成最终
	执行的sql语句. 最后由mybatis框架执行sql并将结果集映射为java对象并返回
2. MyBatis 的优点
	1. 基于SQL编程, 相当灵活. sql统一在xml文件中管理, 提供xml标签,支持编写动态sql语句, 并可以重用
	2. 封装了jdbc, 消除了jdbc大量冗余代码, 不需要手动开关连接.
	3. 只要JDBC支持的数据库, mybatis都支持
	4. 提供映射标签, 支持pojo和数据库字段的关系映射.
4. MyBatis 框架的缺点
	1. sql编写工作量大. 
	2. sql语句依赖数据库, 导致数据库移植性差, 不能随意变更数据库.
5. MyBatis 框架适用场合
	1. 对性能要求高, 需求变化多的项目.
MyBatis 与 Hibernate 有哪些不同？
	1. mybatis不是一个完全的ORM框架, 因为mybatis需要程序员自己编写sql语句
	2. mybatis直接编写sql, 灵活度高, 但sql与数据库依赖性大, 不能变更数据库
	3. hibernate对象/关系映射能力强, 数据库无关性好, 对于关系模型要求高的软件,如果用hibernate开发可以节省很多代码, 提高效率
6.` #{}和${}的区别是什么？`
	1. `#{}`是预编译处理, ${}是字符串替换
	2. mybatis在处理`#{}`时, 会将sql中的#{}替换成?号, 调用PrepareStatement的set方法来赋值
	3. mybaits在处理$符号时, 就是把${}替换成变量的值.
	4. 使用#{}可以有效的防止sql注入, 提高系统安全性.
7. 当实体类中的属性名和表中的字段名不一样 ，怎么办 ？
	1. 查询结果定义别名
	2. resultMap标签定义映射字段名和实体类属性名的一一对应关系.
8. 模糊查询 like 语句该怎么写?
   0. %: 匹配0个或多个; _: 匹配一个
	1. 使用函数concat('%', #{},'%')
	2. 在代码中加好通配符%
9. Mapper 接口的工作原理是什么？Mapper 接口里的方法，参数不同时，方法能重载吗？
	1. 当调用接口方法时: 接口全限定名(包名+类名)+方法名=key, 根据key唯一定位一个MapperStatement(每一个<select>, update, insert, delete标签都会被解析为MapperStatement).
	2. mapper中接口的方法不能重载.原因: 全限名+方法名 的保存和寻找策略
	3. mapper接口的工作原理: JDK动态代理, mybatis运行时会使用jdk动态代理为mapper接口生成代理对象proxy, 
	代理对象会拦截接口方法, 转而执行MapperStatment所代表的的sql, 然后将sql结果返回.
10. Mybatis 是如何进行分页的？分页插件的原理是什么？
	1. mybatis是使用RowBounds对象进行分页, 她是针对ResultSet结果集执行的内存分页.
	2. 分页插件的基本原理: 根据mybatis提供的插件接口, 实现自定义插件, 在插件的拦截方法内拦截待执行的sql, 
	然后重写sql, 根据dialect方言, 添加对应的物理分页语句和物理分页参数.
11. Mybatis是如何将sql执行结果封装为目标对象并返回的？都有哪些映射形式？
	1. 使用标签, 逐一定义数据库列名和对象属性名之间的映射关系.
	2. 使用sql列的别名功能.
	3. 有列名和属性名的映射关系后, mybatis通过反射创建对象. 同时使用反射给对象的属性逐一赋值并返回,
	那些找不到映射关系的属性, 是无法完成赋值的.
12. 如何执行批量插入?
	1. 用foreach标签
13. 如何获取自动生成的(主)键值?
	* 如果采用自增长策略，自动生成的键值在 insert 方法执行完后可以被设置到传入 的参数对象中
14. 在 mapper 中如何传递多个参数?
	1. 用#{0}, #{1}代表
	2. 用@Param注解
	3. 用map封装参数
15. Mybatis 动态 sql 有什么用？执行原理？有哪些动态 sql？
	1. Mybatis 动态 sql 可以在 Xml 映射文件内，以标签的形式编写动态 sql，执行原理 是根据表达式的值 完成逻辑判断并动态拼接 sql 的功能。
	2. Mybatis 提供了 9 种动态 sql 标签：trim | where | set | foreach | if | choose | when | otherwise | bind。
16. Xml 映射文件中，除了常见的 select|insert|updae|delete 标签之外，还有哪些标签？
	1. <resultMap>、<parameterMap>、<sql>、<include>、<selectKey>，
	2. 加上动态 sql 的 9 个标签，其中为 sql 片段标签，通过标签引入 sql 片段，为不支持自增的主键生成策略标签。
17. Mybatis 的 Xml 映射文件中，不同的 Xml 映射文件，id 是否可以重复？
	* 不同的 Xml 映射文件，如果配置了 namespace，那么 id 可以重复；如果没有配置 namespace，那么 id 不能重复；
18. 为什么说 Mybatis 是半自动 ORM 映射工具？它与全自动的区别在哪里？
	* Hibernate 属于全自动 ORM 映射工具，使用 Hibernate 查询关联对象或者关联集合对象时，可以根据对象关系模型直接获取，所以它是全自动的。
	* 而 Mybatis在查询关联对象或关联集合对象时，需要手动编写 sql 来完成，所以，称之为半自动 ORM 映射工具。
19. 一对一、一对多的关联查询 ？
	1. 一对一: <association>
	2. 一对多: <collection>
20. Mybatis 是否支持延迟加载？如果支持，它的实现原理是什么？
	1. Mybatis 仅支持 association 关联对象和 collection 关联集合对象的延迟加 载; 可以配置是否启用延迟加载 lazyLoadingEnabled=true|false
	2. 原理: CGLIB动态代理, 使用CGLIB创建目标对象的代理对象, 当调用目标方法时，进入拦截器方法，比如调用 a.getB().getName()，
	   拦截器 invoke()方法发现 a.getB()是null 值，那么就会单独发送事先保存好的查询关联 B 对象的 sql，把 B 查询上来，
	   然后调用 a.setB(b)，于是 a 的对象 b 属性就有值了，接着完成 a.getB().getName()方法的调用
21. Mybatis 的一级、二级缓存
	1. 一级缓存: 基于perpetualCache的HashMap本地缓存, 其存储作用域为Session, 当Session flush或close之后, 
		该session中所有的cache将清空.默认打开一级缓存
	2. 二级缓存: 基于perpetualCache的HashMap本地缓存, 作用域是Mapper(namespace), 并且可以自定义存储源, 如Encache. 
		默认不打开二级缓存, 使用二级缓存, 属性类要实现Serializable序列化接口(保存对象状态).可以在映射文件中配置.
	3.  对于缓存数据更新机制，当某一个作用域(一级缓存 Session/二级缓存Namespaces)的进行了 C/U/D 操作后，默认该作用域下所有 select 中的缓存将被 clear。  
22. 什么是 MyBatis 的接口绑定？有哪些实现方式？
	1. 接口绑定: mybatis中任意定义接口, 然后把接口里面的方法和sql语句绑定, 直接调用接口方法就可以了.
	2. 实现方式:
		1. 注解绑定: 在接口方法上加@Select, @Update等注解, 里面包含sql语句.
		2. 通过xml里面写SQL来绑定, 要指定xml映射中的namespace必须为接口的全路径名.
23. 使用 MyBatis 的 mapper 接口调用时有哪些要求？
	1. xml中的namespace是mapper接口的全路径名.
	2. xml中的sql的id和接口的方法名一致, 入参类型一致, 出参类型一致.
24. 简述 Mybatis 的插件运行原理，以及如何编写一个插件  
	1. Mybatis 仅可以编写针对 ParameterHandler、ResultSetHandler、StatementHandler、Executor 这 4 种接口的插件.
	2. 使用JDK动态代理, 为需要拦截的接口生成代理对象以实现接口方法拦截功能. (InvocationHandler的invoke()方法)
	3. 编写插件: 
	   	1. 实现Mybatis的Interceptor接口并复写intercept(), 
	   	2. 给插件编写注解, 指定要拦截哪一个接口的哪些方法.
	    3. 在配置文件中配置编写的插件.
	

# Spring
**参考文献**
1. [深入理解 Spring 事务原理 ](https://mp.weixin.qq.com/s/FIMhan9hxijTkG4uLyEwsw)
2. [面试官: 讲讲 Spring 事务有哪些坑?](https://mp.weixin.qq.com/s/BRRELMbULFL-2eZRSehC7w)
3. [了解这些，你就可以在Spring启动时为所欲为了](https://mp.weixin.qq.com/s/ZQHagS3MZABH-dWGlMTulg)

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
1.[深入分析 Spring 框架中 AOP 与动态代理的整合原理 ](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651483455&idx=1&sn=d35ce888e60883c1f655cd04e03470fc&chksm=bd2501408a528856479995e0622249cea90cc24690daf05c0829b36c9b712f5399ae05657f11&mpshare=1&scene=24&srcid=0303IHOMYE6DgZTyIUZFbxKv&sharer_sharetime=1614783736991&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
1.[Spring Boot：启动原理解析](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651483916&idx=2&sn=82d54598440a4cb409299b16d87a89fd&chksm=bd2503738a528a658af5468be80efd5a236cedc2c0f3f33d89a20c9049c8b77b4b694392ef9e&mpshare=1&scene=24&srcid=0303QrWZ4hgjVtZyWdv3alcx&sharer_sharetime=1614783692040&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)
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
1.[3W 字的 Spring Boot 超详细总结 ](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651490602&idx=1&sn=6a7c5440d02bd860ab03cb204855a968&chksm=bd25e5558a526c43ea7df1074296300f7c26c36363208f38bdc60331c9bf2343077a52347f14&mpshare=1&scene=24&srcid=0303JPSWjIiyh1En97y1l1Cg&sharer_sharetime=1614783160289&sharer_shareid=0acafc1fb7821bd49f15ef63553c6711#rd)

## 问题
1. Spring的bean的初始化
	1. Spring对Bean的三类扩展点, 分别对应不同Bean的生命周期阶段
	   a. Aware接口
	   b. BeanPostProcessor
	   c. InitializingBean和init-method
	2. Spring的容器启动时bean的初始化流程
	   1.实例化Bean实例
	   2.设置对象属性
	   3.检查Aware的相关接口并设置相关依赖. (比如:实现 ApplicationContextAware 接口的 setApplicationContext 方法)
	   4.实现BeanPostProcessor接口进行前置处理, postProcessBeforeInitialization(bean初始化前操作)
	   5.是否实现InitializingBean接口.(比如: 实现InitializingBean 接口的 afterPropertiesSet )
	   6.是否配置自定义的init-method
	   7.BeanPostProcessor后置处理,postProcessorAfterInitialization(bean初始化后操作)
	   8.注册Destruction相关回调接口
	   		使用中
	   9.是否实现DisposableBean接口
	   10.是否配置自定义的destory-method
	   11. 结束
2. Spring的事务
	1. Spring事务的基本原理
		1. 概念`Spring事务的本质是数据库对事务的支持, 没有数据库对事务的支持, spring是无法提供事务功能的.`
		2. 纯JDBC操作数据库, 用到事务,具体步骤:
			1. 获取连接: Connection conn = DriverManager.getConnection();
			2. 开启事务: conn.setAutoCommit(true/false);
			3. 执行CRUD
			4. 提交事务conn.commit()/回滚事务conn.rollback();
			5. 关闭连接: conn.close();
		3. Spring的事务管理功能
			1. 开启spring的事务管理功能后, 就省略了2和4步骤, 由spring自动完成.
			2. 注解方式的事务处理	
				1. 配置文件开启注解驱动, 在相关的类和方法上加上@Transactional注解.
				2. Spring启动时解析生成相关的bean, 对于有@Transactional注解的类和方法, 自动生成代理类, 并根据@Transactional的相关参数进行配置和注入,
					这样就在代理中把相关的事务处理了.(开启正常提交事务, 异常回滚事务)
				3. 真正的数据库层面的事务提交和回滚是通过binlog或redo log实现了.
	2. Spring的事务机制
		1. 概念: `用统一的机制处理不同的数据访问技术的事务处理, Spring的事务机制提供了一个PlatformTransactionManager接口, 不同的数据访问技术的事务用不同的接口实现`
	   	2. 数据访问技术及实现
			* JDBC: DataSourceTransactionManager
			* JPA: JpaTransactionManager
			* Hibernate: HibernateTransactionManager
			* JDO: JdoTransactionManager
			* 分布式事务: JtaTransactionManager
		3. 声明式事务
			1. 概念: `Spring支持声明式事务, 即使用注解来选择需要事务的方法或类; 使用@Transactional注解在方法上表明该方法需要事务支持.`
	3. AOP代理的两种实现
		1. Java动态代理: `jdk是代理接口,私有方法必然不会存在接口里, 所以不会被拦截到;`
		    1. 通过实现InvocationHandler接口创建自己的调用处理器.
			2. 通过为 Proxy 类指定 ClassLoader 对象和一组 interface 来创建动态代理类；
			3. 通过反射机制获得动态代理类的构造函数，其唯一参数类型是调用处理器接口类型；
			4. 通过构造函数创建动态代理类实例，构造时调用处理器对象作为参数被传入
		2. CGLIB代理: `cglib是子类, private的方法,final方法不会出现在子类, 也不会被拦截`
		    1. 是一个基于ASM的字节码生成库, 他运行我们在运行时对字节码进行修改和动态生成. CGLIB通过继承方式实现代理.
			2. Enhancer: 来指定要代理的对象, 实际处理逻辑的对象, 最终通过create()方法得到代理对象, 对这个对象的所有非final方法的调用都会转发给MethodInterceptor. -> create()产生代理对象.
			3. MethodInterceptor: 动态代理方法的调用都会转发到intercept()方法进行增强.
			4. intercept(): proxy.invokeSuper(obj, args);
	```java
   Enhancer enhancer = new Enhancer();
   enhancer.setSuperClass(目标对象类);
   enhancer.setCallback(实现MethodInterceptor的类);
   目标类 = enhancer.create();
	```
		3. 原理区别: 
		   * java动态代理是利用反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理。
		   * cglib动态代理是利用asm开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。
	4. Spring事务的传播属性
		1. 概念: `Spring事务的传播属性:定义在存在多个事务同时存在的时候, spring应该如何处理这些事务的行为`
		2. 数据库隔离级别:
			1.Read-uncommited: 读未提交, 导致脏读
		    2. Read-commited: 读已提交, 避免脏读, 允许不可重复读和幻读(sqlserver,oracle的默认隔离级别)
			3. Repeatable-Read: 可重复读, 会导致幻读. (mysql的默认隔离级别)
			4. Serializable: 串行化读. 事务只能一个一个执行, 执行效率低.
		3. Spring的隔离级别
			`和数据库的隔离级别相比, 多了ISOLATION_DEFAULT: 使用数据库默认的隔离级别; 其他的4个隔离级别和数据库的相对应`
		4. 事务的嵌套(spring事务传播机制)   
		   `假设外层事务 Service A 的 Method A()[PROPAGATION_REQUIRED] 调用 内层Service B 的 Method B()[用下面的事务传递规则]`
			1. PROPAGATION_REQUIRED(spring 默认): methodA有事务, methodB沿用A的事务; methodA没有事务, methodB自己创建一个事务.
			2. PROPAGATION_REQUIRES_NEW: 新创建事务, 与methodA的事务无关.
			3. PROPAGATION_SUPPORTS: 沿用A的事务, 如果A没有开启事务, 则B也没有事务. 内部方法的事务性完全依赖于最外层的事务
			4. PROPAGATION_NESTED: 
			   a. 捕获异常, 执行异常分支逻辑
			   b. 外部事务A, 通过具体配置决定是回滚还是提交.
	5. SpringBoot对事务的支持		   
		* `通过org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration类, 自动开启对注解事务的支持`
		* @Transactional(readOnly=true): 只读事务. `从这一点设置的时间点开始（时间点a）到这个事务结束的过程中，其他事务所提交的数据，该事务将看不见`
		* 只读事务的场合: 一次执行多条查询语句. 避免出现多条数据查询之间数据被其他用户改变的情况, 保证整体的统计查询不会出现数据不一致的状态.	
	6. Spring事务不生效的情况
		* 场景1: 在一个Service内部(或者说在一个类中)，事务方法之间的嵌套调用，普通方法和事务方法之间的嵌套调用，都不会开启新的事务！
		* 原因: spring事务是通过动态代理实现的, 动态代理最终要调用原始对象, 而原始对象在取调用方法时,就不会再出发代理了.
		* 场景2: 方法修饰符不是public
		* 原因: AOP的实现: JDK动态代理和CGLIB代理都不支持对非public方法的反射调用
		* 场景3: 发生了错误的异常或者异常被吞了.
		* 原因: rollBackFor中指定的异常默认是RuntimeException, 没有在这个范围内的异常不会回滚.
		* 场景4: 数据库不支持事务
		* 原因: Spring 事务用的是数据库的事务，如果数据库不支持事务，那 Spring 事务肯定是无法生效滴。
	7. 如何保证Spring事务的连接唯一性
		* Connection 在事务开始时封装在了 ThreadLocal 里，后面事务执行过程中，都是从 ThreadLocal中 取的。肯定能保证唯一，因为都是在一个线程中执行。
3. Spring启动监听方式:
	* Bean的构造函数方式
	* 使用@PostConstruct注解
	* 实现InitializingBean接口
	* 监听ApplicationListener事件
	* 使用Constructor注入方式
	* 实现Spring的CommondLinRunner
	* SmartLifecycle机制
	* 实现BeanPostProcessor
4. Spring事件发布流程	
	1. 要发布的事件要继承ApplicationEvent: `UserRegisterEvent extends ApplicationEvent`
	2. 事件发布: 
```java
@Service
@Slf4j
public class UserService implements ApplicationEventPublisherAware {
private ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void register(String username) {
        log.info("register 执行用户{}的注册逻辑", username);
        // ...发布
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this, username));
    }
}
```
	3. 事件监听:
		1. 实现接口ApplicationListener<UserRegisterEvent>
		2. 使用注解: @EventListener
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
1. [一不小心肝出了4W字的Redis面试教程 ](https://mp.weixin.qq.com/s/qI334XgLHrJuU2Oqqi4uwg)
2. [Redis最佳实践：7个维度+43条使用规范，带你彻底玩转Redis](https://mp.weixin.qq.com/s/wmGZDCOqqes0LpQeIn3fpA)
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
[SpringBoot整合RabbitMQ实战](https://mp.weixin.qq.com/s/oEjS0RG_GC2WyLRS-WWfyQ)

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


