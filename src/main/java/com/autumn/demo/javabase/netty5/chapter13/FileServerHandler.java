package com.autumn.demo.javabase.netty5.chapter13;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * @author xql132@zcsmart.com
 * @date 2019/7/15 15:33
 * @description
 */
@Slf4j
public class FileServerHandler extends SimpleChannelInboundHandler<String> {
    // 换行符
    private static final String CR = System.getProperty("line.separator");


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        File file = new File(msg);
        if (file.exists()) {
            if (!file.isFile()) {
                ctx.writeAndFlush("not a file: " + file + CR);
                return;
            }
            ctx.write(file +" " + file.length() + CR);
            RandomAccessFile randomAccessFile = new RandomAccessFile(msg, "r");
            // FileChannel: 文件通道, 用于对文件进行读写操作
            // Position: 文件操作的指针位置, 读取或者写入的起始点
            // count: 操作的总字节数
            FileRegion region = new DefaultFileRegion(randomAccessFile.getChannel(), 0, randomAccessFile.length());
            ctx.write(region);
            ctx.writeAndFlush(CR);
            randomAccessFile.close();
        } else {
            ctx.writeAndFlush("File not fount" + file +CR);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("异常:", cause);
         ctx.close();
    }
}
