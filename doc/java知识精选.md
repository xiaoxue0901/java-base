# java知识精选
## 1. 关于深克隆和浅克隆的差异和原理
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
## Redis
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

2. 