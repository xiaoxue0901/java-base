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
 
 
 
 
 
 
 





