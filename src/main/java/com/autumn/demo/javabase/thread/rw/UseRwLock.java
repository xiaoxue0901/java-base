package com.autumn.demo.javabase.thread.rw;

import com.autumn.demo.javabase.thread.SleepTools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/13
 * @time 12:16 上午
 * @description
 */
public class UseRwLock implements GoodService {
    private GoodsInfo goodsInfo;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    // 读锁
    private final Lock getLock = lock.readLock();
    // 写锁
    private final Lock setLock = lock.writeLock();
    @Override
    public GoodsInfo getNum() {
        getLock.lock();
        try {
            SleepTools.ms(5);
            return this.goodsInfo;
        } finally {
            getLock.unlock();
        }

    }

    @Override
    public void setNum(int number) {
        setLock.lock();
        try {
            SleepTools.ms(5);
            goodsInfo.changeNumber(number);
        }finally {
            setLock.unlock();
        }

    }
}
