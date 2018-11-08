package com.autumn.demo.javabase.yb.way2;

/**
 * @author xql132@zcsmart.com
 * @date 2018/9/21 11:52
 * @description
 */
public class Test {
    public static void main(String[] args) {
        String a = new AsynNoResultTask().testMain();
        System.out.println("执行主线程任务, 不等待子线程执行结果:"+ a);
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

}
