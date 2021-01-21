package com.autumn.demo.javabase.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/21
 * @time 11:03 下午
 * @description
 */
public class NewThread {

    /**
     * 实现Runnable
     */
    private static class UseRun implements Runnable{
        @Override
        public void run() {
            System.out.println("I am implements Runable");
        }
    }

    /**
     * 实现Callable, 允许返回值
     */
    private static class UseCall implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "callable";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        UseRun run = new UseRun();
        // 交给线程类
        Thread t = new Thread(run);
        t.interrupt();
        t.start();


        UseCall call = new UseCall();
        // FutureTask同时实现了Runnable接口
        FutureTask<String> futureTask = new FutureTask<>(call);
        new Thread(futureTask).start();
        // 获取结果值
        System.out.println(futureTask.get());

    }
}
