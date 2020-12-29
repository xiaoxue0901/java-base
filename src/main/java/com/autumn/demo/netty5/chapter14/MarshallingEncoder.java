package com.autumn.demo.netty5.chapter14;


import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.Marshaller;

import java.io.IOException;

/**
 * @author xql132@zcsmart.com
 * @date 2019/7/17 10:54
 * @description Netty消息编码工具类
 */
public class MarshallingEncoder {
    // 长度占位符
    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];

    Marshaller marshaller;

    public MarshallingEncoder() throws IOException {
        this.marshaller = MarshallingCodeCFactory.buildMarshalling();
    }

    public void encode(Object msg, ByteBuf out) {
        int lengthPos = out.writerIndex();
        out.writeBytes(LENGTH_PLACEHOLDER);
//        ChannelBufferByteOutput output = new ChannelBufferByteOutput(out);
//        marshaller.start(new Chann);
    }
}
