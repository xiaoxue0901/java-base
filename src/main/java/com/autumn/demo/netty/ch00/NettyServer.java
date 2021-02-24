package com.autumn.demo.netty.ch00;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/24
 * @time 8:47 下午
 * @description
 */
@Slf4j
public class NettyServer {
    private static final String IP = "127.0.0.1";
    public static final int PORT = 6666;

    // 核心线程数
    public static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2;
    // 线程数
    public static final int BIZTHREADSIZE = 100;

    public static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
    public static final EventLoopGroup workGroup = new NioEventLoopGroup(BIZTHREADSIZE);

    public static void start() throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup)
        .channel(NioServerSocketChannel.class)
        .childHandler(new ChannelInitializer<Channel>() {

            @Override
            protected void initChannel(Channel channel) throws Exception {
                ChannelPipeline pipeline = channel.pipeline();
                pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4 ));
                pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                pipeline.addLast(new TcpServerHander());
            }
        });

      ChannelFuture future = serverBootstrap.bind(IP, PORT).sync();
      future.channel().closeFuture().sync();
      log.info("server start");
    }


    private static void shutDown() {
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }

    public static void main(String[] args) throws InterruptedException {
        log.info("启动server...");
        NettyServer.start();
    }


}
