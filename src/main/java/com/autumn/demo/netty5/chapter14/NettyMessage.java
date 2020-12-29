package com.autumn.demo.netty5.chapter14;

import lombok.Data;

/**
 * @author xql132@zcsmart.com
 * @date 2019/7/17 9:38
 * @description 2. 对netty协议栈使用到的数据结构进行定义
 * 心跳消息, 握手请求和握手应答消息都可以统一由NettyMessage承载, 故不需要对这几类消息进行单独的数据结构的定义.
 */
@Data
public class NettyMessage {
    private Header header; // 消息头
    private Object body; // 消息体

}
