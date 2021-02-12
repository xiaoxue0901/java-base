package com.autumn.demo.javabase.thread.forkjoin;

import com.autumn.demo.javabase.thread.SleepTools;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/11
 * @time 8:49 上午
 * @description Fork/Join的同步用法,同时演示返回结果值:统计整形数组中所有元素的和.
 */
@Slf4j
public class SumArray {

    private static class SumTask extends RecursiveTask<Integer> {
        // 阈值
        private final static int THRESHOLD = MakeArray.ARRAY_LENTH/10;

        private int[] src;
        private int fromIndex;
        // 统计到哪里结束的下标
        private int toIndex;

        public SumTask(int[] src, int fromIndex, int toIndex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        protected Integer compute() {
            if(toIndex-fromIndex < THRESHOLD) {
                int count = 0;
                for (int i = fromIndex; i<= toIndex; i++ ) {
                    SleepTools.ms(1);
                    count = count + src[i];
                }
                return count;
            } else {
                // fromIndex...mid..... toIndex
               int mid = ( fromIndex + toIndex)/2 ;
               SumTask left = new SumTask(src, fromIndex, mid);
               SumTask right = new SumTask(src, mid+1, toIndex);
               invokeAll(left, right);
                return left.join() + right.join();
            }
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        long start = System.currentTimeMillis();
        SumTask sumTask = new SumTask(MakeArray.makeArray(), 0, MakeArray.ARRAY_LENTH-1);
        // 同步调用
        pool.invoke(sumTask);
        int result = sumTask.join();
        log.info("result:{}, spend time:{}", result, System.currentTimeMillis() - start);
    }
}
