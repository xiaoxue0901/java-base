package com.autumn.demo.javabase.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/9
 * @time 11:40 下午
 * @description
 */
@Slf4j
public class UseJoin {
    static class JumpQueue implements Runnable {
        // 用来插队的线程
        private Thread thread;

        public JumpQueue(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("#######{} terminated", Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 现在是主线程
        Thread previous = Thread.currentThread();
        for (int i = 0; i<10 ; i++) {
            Thread thread = new Thread(new JumpQueue(previous), String.valueOf(i));
            log.info("{} jump a queue the thread: {}", previous.getName(), thread.getName());
            thread.start();
            previous = thread;
        }

        // 主线程休眠2秒
        Thread.sleep(2);
        log.info("{} terminate", Thread.currentThread().getName());

    }
}
