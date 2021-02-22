package com.autumn.demo.netty.bio;

import com.autumn.demo.javabase.thread.SleepTools;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/22
 * @time 9:45 下午
 * @description
 */
public class ServerTest {

    public static void main(String[] args) {
        new Thread(()->{
            try {
                Server.start();
            }catch (Exception e) {

            }
        }).start();

        SleepTools.ms(10);
        char[] op = {'+', '-', '*', '/'};
        Random random = new Random(System.currentTimeMillis());
        new Thread(() -> {
            while (true) {
                String expression = random.nextInt(10) + "" + op[random.nextInt(4)] + (random.nextInt(10)+1);
                Client.send(expression);
                SleepTools.ms(1);
            }
        }).start();
    }

}