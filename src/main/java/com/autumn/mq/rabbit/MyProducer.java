package com.autumn.mq.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author xql132@zcsmart.com
 * @date 2021/3/15
 * @time 14:38
 * @description
 */
public class MyProducer {
    public static final String QUEUE_NAME = "ORIGIN_QUEUE";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        // 连接ip
        factory.setHost("127.0.0.1");
        // 连接端口
        factory.setPort(5672);
        // 虚拟机
        factory.setVirtualHost("/");
        // 用户
        factory.setUsername("guest");
        // 密码
        factory.setPassword("guest");

        try {
            // 建立连接
            Connection connection = factory.newConnection();
            // 创建消息通道
            Channel channel = connection.createChannel();
            // 消息
            String msg = "hello world , Rabbit mq";
            // 声明队列
            // String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            // 发送消息.(发送到默认交换机AMQP Default，Direct)
            // 如果有一个队列名称和routing key 相等, 那么消息会路由到这个队列
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes(StandardCharsets.UTF_8));
            // 直连交换机
            channel.basicPublish("direct", "key1", null, msg.getBytes(StandardCharsets.UTF_8));
            // 主题交换机
            channel.basicPublish("topic", "*.key", null, msg.getBytes(StandardCharsets.UTF_8));
            // 广播交换机
            channel.basicPublish("fanout", "", null, msg.getBytes(StandardCharsets.UTF_8));

            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
