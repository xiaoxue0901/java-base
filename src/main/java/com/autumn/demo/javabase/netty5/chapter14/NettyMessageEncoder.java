//package com.autumn.demo.javabase.netty5.chapter14;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.codec.MessageToMessageEncoder;
//import io.netty.handler.codec.marshalling.MarshallerProvider;
//import io.netty.handler.codec.marshalling.MarshallingEncoder;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * @author xql132@zcsmart.com
// * @date 2019/7/17 9:50
// * @description 4. NettyMessageEncoder用于NettyMessage消息的编码
// */
//public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {
//
//    MarshallingEncoder marshallingEncoder;
//
//    public NettyMessageEncoder(MarshallerProvider provider) {
//        this.marshallingEncoder = new MarshallingEncoder(provider);
//    }
//
//    @Override
//    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, List<Object> out) throws Exception {
//        if (msg == null || msg.getHeader() == null ) {
//            throw new Exception("The encode message is null");
//        }
//        ByteBuf sendBuf = Unpooled.buffer();
//        sendBuf.writeInt(msg.getHeader().getCrcCode());
//        sendBuf.writeInt(msg.getHeader().getLength());
//        sendBuf.writeLong(msg.getHeader().getSessionId());
//        sendBuf.writeByte(msg.getHeader().getType());
//        sendBuf.writeByte(msg.getHeader().getPriority());
//        sendBuf.writeByte(msg.getHeader().getAttachment().size());
//
//        String key = null;
//        byte[] keyArray = null;
//        Object value = null;
//        for (Map.Entry<String, Object> param : msg.getHeader().getAttachment().entrySet()) {
//            key = param.getKey();
//            keyArray = key.getBytes("UTF-8");
//            sendBuf.writeInt(keyArray.length);
//            sendBuf.writeBytes(keyArray);
//            value = param.getValue();
//            marshallingEncoder.encode(value, sendBuf);
//        }
//        key = null;
//        keyArray = null;
//        value = null;
//        if (msg.getBody() != null) {
//            marshallingEncoder.encode(msg.getBody(), sendBuf);
//        } else {
//            sendBuf.writeInt(0);
//            sendBuf.setInt(4, sendBuf.readableBytes());
//        }
//
//    }
//}
