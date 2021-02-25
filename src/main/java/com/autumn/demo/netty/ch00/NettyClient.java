package com.autumn.demo.netty.ch00;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/24
 * @time 9:34 下午
 * @description
 */
public class NettyClient implements Runnable {
    @Override
    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4));
                            pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                            pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                            pipeline.addLast("encoder",new StringEncoder(CharsetUtil.UTF_8));
                            pipeline.addLast("handler", new MyClinet());

                        }
                    });

            for (int i = 0; i < 10; i++) {
                ChannelFuture future = bootstrap.connect("127.0.0.1", 6666).sync();
                future.channel().writeAndFlush("hello server: {}"+Thread.currentThread().getName()+"--->"+i);
                future.channel().closeFuture().sync();
            }

        } catch (Exception e) {

        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new NettyClient(), ">>>this thread"+i).start();

        }
    }
}
