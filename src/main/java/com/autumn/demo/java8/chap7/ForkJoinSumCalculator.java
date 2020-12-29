package com.autumn.demo.java8.chap7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author xql132@zcsmart.com
 * @date 2019/12/30 17:04
 * @description
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    public static ForkJoinPool pool = new ForkJoinPool();
//    要求和的数组
    private final long[] numbers;
    private final int start;
    private final int end;
    /**不再将任务分解为子任务的数组大小*/
    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        ForkJoinSumCalculator leftFork = new ForkJoinSumCalculator(numbers, start, start + length/2);
        //利用另一个ForkJoinPool线程异步执行新建的子任务
        leftFork.fork();
        ForkJoinSumCalculator rightFork = new ForkJoinSumCalculator(numbers, start + length/2, end);
        // 同步执行第二个子任务, 有可能允许进一步递归划分
        Long rightResult = rightFork.compute();
        // 读取第一个子任务的结果, 如果尚未完成就等待.
        Long leftResult = leftFork.join();
        return rightResult + leftResult;
    }

    /**
     * 计算方式:累加
     * @return
     */
    private long computeSequentially() {
        long sum = 0;
        for(int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1,n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return pool.invoke(task);
    }
}
