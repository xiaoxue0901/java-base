<h2> 1.1 Elasticsearch概念</h2>

## 1.1相关概念

`Elastic`: 一个采用Restful API标准的高扩展性和高可用性的实时数据分析的全文搜索工具.



* Node(节点): 单个的装有elasticsearch服务并且提供故障转移和扩展的服务器.
* Cluster(集群): 一个集群就是由一个或多个node组织在一起, 共同工作,共同分享,整个数据具有负载均衡功能的集群.
(集群的解释: 是指在多台不同的服务器中部署相同应用或者服务模块, 构成一个集群, 通过负载均衡设备对外提供服务)
* Document(文档): 一个文档是一个可被索引的基础信息单元.
* Index(索引): 索引就是一个拥有几分相似特征的文档的集合.(注意:英文小写)
* Type(类型): 一个索引中, 可以定义一种或者多种类型.
* Field(列): Field是Elasticsearch中的最小单位, 相当于数据中的某一列.
* Shards(分片): Elasticsearch将索引分为若干份, 每个部分都是一个shard.(分片原因: 一个索引的大小
可能会超出单个节点的这个硬盘限制的大小, 在索引创建的时候定义, 默认5分, 索引创建完毕就无法更改).
* Replicas(复制): Replicas是索引一份或多分的拷贝.

比喻:

| 关系型数据库(比如mysql)|非关系型数据库(elasticsearch)|
|数据库Database|索引Index|
|表Table|类型Type|
|数据行Row|文档Document|
|数据列Column|字段Field|

非结构化数据:文本,图片,音频
结构化数据:数字符号 (关系型数据库存储)

## 1.2 Elasticsearch架构

 ![架构](D:\workspace\github\java-base\src\main\resources\elasticsearch架构.png)


---

## 1.3 Elasticsearch的安装及相关插件的介绍
 
 ### 安装准备
 * 安装JDK, 配置Java环境变量(版本在1.8之上)
 * 安装Elasticsearch
 * 启动Elasticsearch
 * Elasticsearch目录结构
 
 安装JDK.
 1. 将下载的文件放在/opt目录下
 2. 检查是否有安装JDK. java -version
 3. 已安装但版本不对, 则卸载后安装JDK8版本的.
 4. 卸载命令: yum remove java 
 5. 未卸载成功则用组的方式卸载: yum groupremove java
 6. 解压: tar zxvf jdk-xxx.tar.gz
 7. 配置环境变量: vim /etc/profile 配置完毕后重启或者其他方式让配置生效.
 
 安装elasticsearch
 
 安装插件
 * Head
 * Bigdesk
 
 ## 1.4 倒排索引
 * 什么是倒排索引?
 倒排索引(Inverted index): 也被称为**反向索引**,置入档案或反向档案. 是一种索引方法, 被用来存储在全文搜索下**某个单词**在一个文档或者一组文档中的**存储位置的映射**.是文档检索系统中最常用的数据结构.
 
 常规的索引建立方式:
 文档-->关键词的映射过程(正向索引)
 
 倒排反向建立索引:
 关键词-->文档的映射 把正向索引的结果重新构造成的倒排索引.(反向索引)
 
 例: 参见倒排索引.png
 ![倒排索引](../doc/倒排索引.png)
 
 ## 1.5 ElasticSearch API 操作
  * Mavel插件
  sense功能: 写AUID代码
  
  * 5.1 索引初始化操作
  创建索引之前可以对索引做初始化操作. 比如指定shards(碎片)数量以及replicas(备份)数量
  ```$xslt
CURL-XPUT 'http://192.168.1.10:9200/library/'-d'{
    "settings":{
        "index":{
         # 设置以后不能更改
        "number_of_shards":5,
        "number_of_replicas":1
        }
    }
}'

# 上面的number_of_replicas还可以换成:
blocks.read_only: 设为true, 则当前索引只允许读,不允许写或更新.
blocks.read: 设为true, 则进制读操作.
blocks.write: 设为true, 则禁止写操作.
blocks.metadata: 设为true, 则禁止对metadata操作.
```




 
 
 
 
 
 
 
 
 
 
 
 





