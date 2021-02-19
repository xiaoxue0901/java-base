package com.autumn.demo.javabase.thread;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/11
 * @time 8:44 上午
 * @description
 */
public class SleepTools {
    public static void ms(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
