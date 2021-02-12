package com.autumn.demo.javabase.thread.tools;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/12
 * @time 2:53 下午
 * @description
 */
public class UseExchange {
    private static final Exchanger<Set<String>> exchange = new Exchanger<Set<String>>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                // 存储数据的容器
                Set<String> setA = new HashSet<>();
                // 添加数据
                // set.add
                setA = exchange.exchange(setA); // 交换set, 交换后setA的数据就变成了setB
                // 处理交换后的数据
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()-> {
            try {
                Set<String> setB = new HashSet<>();
                // 添加数据
                // set.add
                // 到达exchange后会阻塞, 直到2个线程同时到达exchange, 执行数据交换.
                setB = exchange.exchange(setB); // 交换set,交换后setB的数据就变成了setA
                // 处理交换后的数据
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
