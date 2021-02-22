package com.autumn.demo.netty.bio;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/22
 * @time 9:45 下午
 * @description
 */
public class ClientTest {

    public static void main(String[] args) {
        char[] op = {'+', '-', '*', '/'};
        Random random = new Random(System.currentTimeMillis());
        new Thread(() -> {
            while (true) {
                String expression = random.nextInt(10) + "" + op[random.nextInt(4)] + (random.nextInt(10)+1);
                Client.send(expression);
            }
        }).start();
    }

}