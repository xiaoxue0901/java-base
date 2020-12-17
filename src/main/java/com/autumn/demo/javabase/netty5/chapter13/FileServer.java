package com.autumn.demo.javabase.netty5.chapter13;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xql132@zcsmart.com
 * @date 2019/7/15 14:45
 * @description
 */
@Slf4j
public class FileServer {

    public void run(int port) throws Exception {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(
                                    // 将文件内容编码成字符串
                                    new StringEncoder(CharsetUtil.UTF_8),
                                    // 能够按照回车换行符对数据报进行解码
                                    new LineBasedFrameDecoder(1024),
                                    // 将数据报解码成字符串. LineBaseFrameDecoder+StringDecoder=文本换行解码器
                                    new StringDecoder(CharsetUtil.UTF_8),
                                    new FileServerHandler(),
                                    // 解决大文件或者码流传输过程中可能发生的内存溢出问题
                                    new ChunkedWriteHandler());
                        }
                    });
            // 绑定端口, 同步等待返回结果
            ChannelFuture f = b.bind(port).sync();
            log.info("start file server at port: {}", port);
            f.channel().closeFuture().sync();
        } finally {
            // 优雅停机
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        // 启动服务端
        new FileServer().run(port);
    }
}
