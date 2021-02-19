package com.autumn.demo.javabase.thread.future;

import com.autumn.demo.javabase.thread.SleepTools;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/12
 * @time 3:12 下午
 * @description
 */
@Slf4j
public class UseFuture {
    // 实现Callable接口, 运行有返回值
    private static class UseCallable implements Callable<Integer> {
        private int sum;
        @Override
        public Integer call() throws Exception {
            log.info("callable子线程开始计算");
            Thread.sleep(2000);
            for (int i = 0; i < 5000; i++) {
                sum = sum +i;
            }
            log.info("Callable计算已完成, 结果{}", sum);
            return sum;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        UseCallable useCallable = new UseCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(useCallable);
        new Thread(futureTask).start();

        Random random = new Random();
        SleepTools.ms(1000);
        if (random.nextBoolean()) {
            // 是获得结果还是终止任务
            log.info("get callable result: {}", futureTask.get());
        } else  {
            log.info("中断计算");
            futureTask.cancel(true);
        }
    }
}
