package com.autumn.demo.netty5.chapter2.aio;

/**
 * @author xql132@zcsmart.com
 * @date 2018/4/2
 * @time 18:04
 * @description
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                System.out.println("数组值:"+args[0]);
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }


    }
}
