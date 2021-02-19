package com.autumn.demo.javabase.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/12
 * @time 11:39 下午
 * @description
 */
public class LockDemo {
    private Lock lock = new ReentrantLock();
    private int count;

    public void  increment() {
        // 拿到锁
        lock.lock();
        try {
            count++;
        } finally {
            // 一定要在finally中释放锁.
            lock.unlock();
        }

    }

    public synchronized void  incr2(){
        count++;
    }
}
