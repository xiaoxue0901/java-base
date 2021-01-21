package com.autumn.demo.javabase.thread;


import lombok.extern.slf4j.Slf4j;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/21
 * @time 11:29 下午
 * @description
 */
@Slf4j
public class HasInterruptException {
    private static class UseThread extends Thread {
        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            // 线程名称
            String threadName = Thread.currentThread().getName();
           while (!isInterrupted()) {
               log.info("{} is run", threadName);
               System.out.println("线程");
               try {
                   Thread.sleep(30);
               } catch (InterruptedException e) {
                   e.printStackTrace();
                   // InterruptedException会将中断标识为复位成false
                   log.info("threadName:{}nterrupt flag is:{}", threadName, isInterrupted());
                   // 再调用一次
                   interrupt();
               }
               log.info("thread:{}interrupt flag is:{}", threadName, isInterrupted());
           }
            log.info("thread:{}interrupt flag is:{}", threadName, isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread endThread = new UseThread("endThread");
        endThread.start();
        endThread.interrupt();
    }
}
