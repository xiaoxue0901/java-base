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

    // bossGroup用于接收连接, workerGroup用于具体的处理
    public static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
    public static final EventLoopGroup workGroup = new NioEventLoopGroup(BIZTHREADSIZE);

    public static void start() throws InterruptedException {
        // 创建服务端启动引导类ServerBootstrap
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 给引导类配置两大线程组, 确定了线程模型
        serverBootstrap.group(bossGroup, workGroup)
                // 指定IO模型
        .channel(NioServerSocketChannel.class)
        .childHandler(new ChannelInitializer<Channel>() {
            // 自定义客户端消息的业务处理逻辑
            @Override
            protected void initChannel(Channel channel) throws Exception {
                ChannelPipeline pipeline = channel.pipeline();
                pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4 ));
                pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                pipeline.addLast(new TcpServerHander());
            }
        });
        // 绑定端口, 调用sync方法阻塞直到绑定完成
      ChannelFuture future = serverBootstrap.bind(IP, PORT).sync();
      // 阻塞等待服务器channel关闭
      future.channel().closeFuture().sync();
      log.info("server start");
    }


    private static void shutDown() {
        // 优雅关闭线程组资源
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }

    public static void main(String[] args) throws InterruptedException {
        log.info("启动server...");
        NettyServer.start();
    }


}
