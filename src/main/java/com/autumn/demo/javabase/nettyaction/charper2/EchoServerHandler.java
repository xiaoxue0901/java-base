package com.autumn.demo.javabase.nettyaction.charper2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;


/**
 * @author xql132@zcsmart.com
 * @date 2019/3/24 11:31
 * @description 回声服务处理器: 实现核心业务逻辑
 * 1. Echo服务器会响应传入的消息, 所以需要实现ChannelInboundHandler接口. 目的: 定义响应入站事件的方法.
 * 2. 此程序只会用到少量ChannelInboundHandler接口的方法, 故直接继承ChannelInboundHandlerAdapter.
 * 3. ChannelInboundHandlerAdapter提供了ChannelInboundHandler的默认实现.
 */
@Slf4j
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        log.info("Server received: {}", in.toString(CharsetUtil.UTF_8));
        // 将收到的消息写给发送者, 而不冲刷出站消息
        ctx.write(in);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将未决消息冲刷到远程节点, 并且关闭该Channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 打印异常栈跟踪
        log.info("异常信息:", cause);
        // 关闭该channel
        ctx.close();
    }
}
