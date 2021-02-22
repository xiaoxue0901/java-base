package com.autumn.demo.javabase.thread.atomic.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/13
 * @time 10:45 上午
 * @description
 */
public class SelfLock implements Lock {

    // state表示获取到锁state=1 获取到了锁; state=0: 表示当前没有线程获取到锁
    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                // 表示当前线程拿到锁,
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            // 把state改为0
            if (getState() == 0) {
                throw new UnsupportedOperationException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        // 是否占用
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
