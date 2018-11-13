package com.autumn.demo.javabase.netty5.chapter2.aio;//package com.xql.netty.chapter2.aio;
//
//import java.nio.ByteBuffer;
//import java.nio.channels.AsynchronousSocketChannel;
//import java.nio.channels.CompletionHandler;
//
///**
// * @author xql132@zcsmart.com
// * @date 2018/4/2
// * @time 18:34
// * @description
// */
//public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> {
//    @Override
//    public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
//        attachment.asynchronousServerSocketChannel.accept(attachment, this);
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        result.read(buffer, buffer, new );
//    }
//
//    @Override
//    public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
//        exc.printStackTrace();
//        attachment.latch.countDown();
//    }
//}
