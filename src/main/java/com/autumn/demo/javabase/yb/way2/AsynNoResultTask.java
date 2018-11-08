package com.autumn.demo.javabase.yb.way2;

/**
 * @author xql132@zcsmart.com
 * @date 2018/9/21 11:49
 * @description
 */
public class AsynNoResultTask {

    public void task() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 子线程任务
//                try {
////                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                // 开始计数
                int sum = 0;
                for (int i = 0 ; i< 1000; i++) {
                    sum+=i;
                }
                System.out.println("子线程执行完毕:"+sum);
            }
        });
        thread.start();

    }

    public String testMain() {
        task();
        return "hello world!";
    }
}
