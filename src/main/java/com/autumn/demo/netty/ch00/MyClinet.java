package com.autumn.demo.netty.ch00;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/24
 * @time 9:42 下午
 * @description
 */
@Slf4j
public class MyClinet extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("client receive:{}", msg);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello server, i am client".getBytes(StandardCharsets.UTF_8)));

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("get server exception:", cause);
    }
}
