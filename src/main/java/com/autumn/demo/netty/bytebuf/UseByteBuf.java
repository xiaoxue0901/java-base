package com.autumn.demo.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.UnpooledHeapByteBuf;

/**
 * @author xql132@zcsmart.com
 * @date 2021/3/19
 * @time 14:56
 * @description  ByteBuf使用
 */
public class UseByteBuf {

    public void useHeapBuf() {
        ByteBuf buf = new UnpooledHeapByteBuf(ByteBufAllocator.DEFAULT, 200, 1000);
        if (buf.hasArray()) {
            byte[] array = buf.array();

        }

    }
}
