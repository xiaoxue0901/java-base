package com.autumn.demo.javabase.thread.tools;

import com.autumn.demo.javabase.thread.SleepTools;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/11
 * @time 7:21 下午
 * @description
 * 类说明: 演示CountDownLatch, 有5个初始化的线程; 6个扣除点.
 * 扣除完毕后, 主线程和业务线程才能继续自己的工作.
 */
@Slf4j
public class UseCountDownLatch {
    //
    static CountDownLatch latch = new CountDownLatch(6);

    // 初始化线程
    private static class InitThread implements Runnable {
        @Override
        public void run() {
            log.info("thread_:{}, ready init work...", Thread.currentThread().getId());
            // 初始化线程完成工作了
            latch.countDown();
            for (int i=0; i<=2; i++) {
                log.info("thread:{} .... continue do its work", Thread.currentThread().getId());
            }
        }
    }

    // 业务线程
    private static class BusiThread implements Runnable {
        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i=0; i<3;i++) {
                log.info("busThread:{} do business----", Thread.currentThread().getId());
            }
        }
    }

    public static void main(String[] args) {
        // 单独的初始化线程
        new Thread(()->{
            SleepTools.ms(1);
            log.info("thread_{} ready init worker stemp 1st...", Thread.currentThread().getId());
            // 每完成一次初始化动作, 扣减一次
            latch.countDown();
            log.info("begin step 2nd");
            SleepTools.ms(1);
            log.info("thread_{} ready init worker stemp 2nd...", Thread.currentThread().getId());
            latch.countDown();

        }).start();

        new Thread(new BusiThread()).start();
        for (int i=0;i<3;i++) {
            Thread thread = new Thread(new InitThread());
            thread.start();
        }
        latch.countDown();
        log.info("Main do ites work.....");
    }

}
