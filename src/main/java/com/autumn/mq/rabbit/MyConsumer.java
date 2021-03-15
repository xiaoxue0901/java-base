package com.autumn.mq.rabbit;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author xql132@zcsmart.com
 * @date 2021/3/15
 * @time 14:47
 * @description
 */
public class MyConsumer {
    public static final String QUEUE_NAME = "ORIGIN_QUEUE";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("guest");
        factory.setPassword("guest");

        try {
            // 建立连接
            Connection connection = factory.newConnection();
            // 创建消息通道
            Channel channel = connection.createChannel();
            // String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            System.out.println("waiting for message");
            // 创建消费者
            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String msg = new String(body, StandardCharsets.UTF_8);
                    System.out.println("receive message :"+ msg);
                }
            };
            // 开始获取消息
            // String queue, boolean autoAck, Consumer callback
            channel.basicConsume(QUEUE_NAME, true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
