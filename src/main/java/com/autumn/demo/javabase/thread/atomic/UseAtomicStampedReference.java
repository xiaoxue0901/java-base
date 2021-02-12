package com.autumn.demo.javabase.thread.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/12
 * @time 10:35 下午
 * @description 演示版本戳的原子操作类
 */
@Slf4j
public class UseAtomicStampedReference {

    static AtomicStampedReference<String> asr = new AtomicStampedReference("mark", 0);

    public static void main(String[] args) throws InterruptedException {
        final  int oldStamp = asr.getStamp();// 初始的版本号
        final String oldReference = asr.getReference();

        log.info("old Reference:{}, stamp:{}",oldReference,  oldStamp);
        Thread rightStampThread = new Thread(()->{
            String reference = asr.getReference();

            log.info("{}, 当前变量值:{}, 当前版本戳:{}, result:{}", Thread.currentThread().getName(), reference, asr.getStamp(),
                    asr.compareAndSet(oldReference, oldReference+"java", oldStamp, oldStamp+1));

        });

        Thread errorStampThread = new Thread(()->{
            String reference = asr.getReference();

            log.info("{}, 当前变量值:{}, 当前版本戳:{}, result:{}", Thread.currentThread().getName(), reference, asr.getStamp(),
                    asr.compareAndSet(oldReference, oldReference+"java", oldStamp, oldStamp+1));

        });

        rightStampThread.start();
        rightStampThread.join();
        errorStampThread.start();
        errorStampThread.join();
        log.info("{}====={}", asr.getReference(), asr.getStamp());

    }
}
