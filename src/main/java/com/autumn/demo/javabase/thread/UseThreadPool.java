package com.autumn.demo.javabase.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xql132@zcsmart.com
 * @date 2021/3/11
 * @time 15:15
 * @description
 */
public class UseThreadPool {

    private static ArrayBlockingQueue queue = new ArrayBlockingQueue(100);

    public void threadPool() {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10,100, 10, TimeUnit.SECONDS,queue);
        // LinkedBlockingQueue
        Executors.newFixedThreadPool(10);
        //LinkedBlockingQueue
        Executors.newSingleThreadExecutor();
        //SynchronousQueue
        Executors.newCachedThreadPool();
        //DelayedWorkQueue
        Executors.newScheduledThreadPool(1);
    }
}
