package com.autumn.demo.javabase.thread;


import lombok.extern.slf4j.Slf4j;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/21
 * @time 11:29 下午
 * @description
 */
@Slf4j
public class EndThread {
    private static class UseThread extends Thread {
        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            // 线程名称
            String threadName = Thread.currentThread().getName();
//           while (!isInterrupted()) {
            while (!Thread.currentThread().isInterrupted()) {
               log.info("{} is run", threadName);
               System.out.println("线程");
           }
           log.info("threadName:{} interrupt flag is :{}", threadName, isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread endThread = new UseThread("endThread");
        endThread.start();
        Thread.sleep(20);
        endThread.interrupt();
    }
}
