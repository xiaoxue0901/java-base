package com.autumn.demo.javabase.thread.tools;

import com.autumn.demo.javabase.thread.SleepTools;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/12
 * @time 2:06 下午
 * @description
 */
@Slf4j
public class UseCyclicBarrier {
    private static ConcurrentHashMap<String, Long> resultMap = new ConcurrentHashMap<>();

    private static CyclicBarrier barrier = new CyclicBarrier(5);
    private static class CollectThread implements Runnable {
        @Override
        public void run() {
            StringBuffer buffer = new StringBuffer();
            for (Map.Entry<String, Long> workResult : resultMap.entrySet()) {
                buffer.append("["+workResult.getValue()+"]");
            }
         log.info("the result:{}", buffer);
            log.info("do other business........");
        }
    }

    private static class SubThread implements Runnable {
        @Override
        public void run() {
            long id = Thread.currentThread().getId();
            resultMap.put(id+"", id);
            Random random = new Random();
            if (random.nextBoolean()) {
                SleepTools.ms(200+id);
                log.info("thread_{} do something", id);
            }
            log.info("{} ... is await", id);
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            SleepTools.ms(1000+id);
            log.info("thread_{} ...do its business", id);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i< 4; i++) {
            Thread thread = new Thread(new SubThread());
            thread.start();
        }
    }
}
