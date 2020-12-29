package com.autumn.demo.netty5.chapter14;

import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

import java.io.IOException;

/**
 * @author xql132@zcsmart.com
 * @date 2019/7/17 11:15
 * @description
 */
public class MarshallingCodeCFactory {

    /**
     * 创建JBOSS Marshalling解码器MarshallingDecoder
     * @return
     */
    public static MarshallingDecoder buildMarshallingDecoder() {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);
        MarshallingDecoder decoder = new MarshallingDecoder(provider, 1024);
        return decoder;
    }

//    public static MarshallingEncoder buildMarshallingEncoder() {
//        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
//        final MarshallingConfiguration configuration = new MarshallingConfiguration();
//        configuration.setVersion(5);
//        MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory, configuration);
//        MarshallingEncoder encoder = new MarshallingEncoder(provider);
//        return encoder;
//    }

    public static Marshaller buildMarshalling() throws IOException {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        Marshaller marshaller = marshallerFactory.createMarshaller(configuration);
        return marshaller;
    }
}
