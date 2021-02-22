package com.autumn.demo.javabase.thread.atomic.aqs;

import com.autumn.demo.javabase.thread.SleepTools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/13
 * @time 10:59 上午
 * @description
 */
public class TestMyLock {
    private void test() {
        final Lock lock = new SelfLock();
//        final Lock lock = new ReentrantLock();
        class Worker extends Thread {
            @Override
            public void run() {
                lock.lock();

                try {
                    SleepTools.ms(1000);
                    System.out.println(Thread.currentThread().getName());
                    SleepTools.ms(1000);
                }finally {
                    lock.unlock();
                }
                SleepTools.ms(1000);
            }
        }

        for (int i = 0; i < 10; i++) {
            Worker worker = new Worker();
            worker.setDaemon(true);
            worker.start();

        }
        for (int i = 0; i < 10; i++) {
            SleepTools.ms(1000);
            System.out.println();
        }


    }


    public static void main(String[] args) {
        TestMyLock lock = new TestMyLock();
        lock.test();
    }
}
