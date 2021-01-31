package com.autumn.demo.javabase.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/30
 * @time 12:03 下午
 * @description
 */
@Slf4j
public class StartAndRun {
    public static class ThreadRun extends Thread {
        @Override
        public void run() {
            int i = 90;
            while (i>0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("I am:{}, and now th i={}", Thread.currentThread().getName(), i++);
            }
        }
    }

    public static void main(String[] args) {
        ThreadRun run = new ThreadRun();
        run.setName("BeCalled");
        // 设置优先级
        run.setPriority(1);

        run.start();
    }
}
