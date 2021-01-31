package com.autumn.demo.javabase.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/31
 * @time 10:07 下午
 * @description
 */
@Slf4j
public class DeamonThread {

    private static class UseThread extends Thread {
        @Override
        public void run() {
            while (!isInterrupted()) {
                log.info("{} I am extends Thread", Thread.currentThread().getName());
            }
            log.info(" interrupted flag is {}", isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseThread useThread = new UseThread();
        // 设置守护线程
        useThread.setDaemon(true);
        useThread.start();
        Thread.sleep(5);
        useThread.interrupt();
    }
}
