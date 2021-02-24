package com.autumn.demo.netty.nio;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/23
 * @time 10:21 下午
 * @description
 */
@Slf4j
public class BufferDemo {

    public static void decoee(String str) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        // 写模式: position: str.getBytes.len; limit=128
        byteBuffer.put(str.getBytes(StandardCharsets.UTF_8));
        // 翻转, 开启读模式: position=0; limit=str.getBytes.len
        byteBuffer.flip();

        // 将byteBuffer解码为CharBuffer
        Charset utf8 = Charset.forName("UTF-8");
        CharBuffer charBuffer = utf8.decode(byteBuffer);
        // array()返回的就是内部的数组引用, 编码以后的有效长度是0-limit
        char[] charArray = Arrays.copyOf(charBuffer.array(), charBuffer.limit());
        log.info("运行结果:{}", charArray);
    }
}
