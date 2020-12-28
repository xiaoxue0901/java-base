<h1>I/O流</h1>

目的: 此包放置I/O流相关实例.
1. CombinedStreamFilter: 介绍如何对流进行组合使用, 从而达到最大效果.
2. TextStream: 文本输入和输出, 主要介绍了InputStreamReader和InputStreamWriter: 中介流, 转换字节和字符.
3. ZipStream: ZIP文件操作
4. BinaryStream: 二进制流操作,DataOutputStream和DataInputStream
5. RandomAccessFileStream: 随机读写文件, 可读可写, 性能低, 测试可用
6. ObjectStream: 对象流, 可以直接读写对象
7. SerialClone: 序列化
8. PathDemo: Paths类的操作: 类路径相关操作
9. FileDemo: Files类的使用, 包括创建, 删除, 移动, 重命名文件等操作
10. BufferDemo: 缓冲流的使用, 7中类型的缓冲流.
11. FileUtils: I/O流常用操作集合
    
-------
<h2>正则表达式</h2>

[地址-菜鸟学院](http://www.runoob.com/regexp/regexp-syntax.html)

`正则表达式(regular expression)描述了一种字符串匹配的模式（pattern），可以用来检查一个串是否含有某种子串、将匹配的子串替换或者从某个串中取出符合某个条件的子串等。`

<h2>通配符</h2>

 > ^[0-9]+abc$
 
* ^ 为匹配输入字符串的开始位置。
* [0-9]+匹配多个数字， [0-9] 匹配单个数字，+ 匹配一个或者多个。
* abc$匹配字母 abc 并以 abc 结尾，$ 为匹配输入字符串的结束位置。
------------------------

- ? 匹配0或1个字符
- \+ 匹配一个或多个
- \* 匹配0个或多个字符

------------------------



 