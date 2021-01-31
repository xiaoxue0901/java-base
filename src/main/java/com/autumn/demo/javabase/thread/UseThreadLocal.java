package com.autumn.demo.javabase.thread;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/31
 * @time 10:47 下午
 * @description
 */
public class UseThreadLocal {
    static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        // 赋初始值
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };

    public void startThreadArray() {
        Thread[] runs = new Thread[3];
        for (int i=0;i<runs.length; i++) {
            runs[i] = new Thread(new TestThread(i));
        }
        for (int i=0;i<runs.length; i++) {
            runs[i] = new Thread();
        }
    }

    public static class TestThread implements Runnable {
         private int id;
        @Override
        public void run() {
            // 获取值
            Integer s = threadLocal.get();
            s = s + id;
            // 回写值
            threadLocal.set(s);
            // 清空, 加快
//            threadLocal.remove();
        }

        public TestThread(int id) {
            this.id = id;
        }
    }



}
