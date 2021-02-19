package com.autumn.demo.javabase.thread.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/12
 * @time 10:22 下午
 * @description
 */
@Slf4j
public class UseAtomicTest {

    static AtomicInteger ai = new AtomicInteger(10);

    public static void main(String[] args) {
        // get: 10, 加1: 11
        log.info("{}", ai.getAndIncrement());
        // 加1: 12, get: 12
        log.info("{}", ai.incrementAndGet());
        // get : 12
        log.info("{}", ai.get());
    }
}
