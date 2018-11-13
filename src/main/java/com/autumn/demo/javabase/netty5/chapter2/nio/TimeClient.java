package com.autumn.demo.javabase.netty5.chapter2.nio;

/**
 * @author xql132@zcsmart.com
 * @date 2018/4/2
 * @time 17:22
 * @description
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        new Thread(new TimeClientHandler("172.80.90.34", port), "TimeClient-001").start();
    }

}
