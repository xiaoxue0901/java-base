package com.autumn.demo.javabase.thread;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/9
 * @time 11:40 下午
 * @description
 */
@Slf4j
public class UseJoin {
    static class JumpQueue implements Runnable {
        // 用来插队的线程
        private Thread thread;

        public JumpQueue(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                // join(): 线程等待指定的线程终止
                // 此处thread是main线程, 那么join()意思是main线程全部执行完毕后, 再执行JumpQueue线程的run方法的内容.
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("#######{} terminated", Thread.currentThread().getName());

        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread previous = Thread.currentThread();
        // 现在是主线程
        for (int i = 1; i < 11; i++) {
            // 要插队的线程
            Thread thread = new Thread(new JumpQueue(previous), String.valueOf(i)) ;
            // 主线程打印的数据
            log.info("{} jump a queue the thread: {}", previous.getName(), thread.getName());
            thread.start();
            previous = thread;
        }
        // 主线程休眠2秒
        Thread.sleep(2000);
        // 打印主线程名称: 在thread中调用了main线程的join()方法, 故会一直等待main线程的逻辑执行完毕后, 才会执行thread的run()方法
        log.info("{} terminate", Thread.currentThread().getName());
        // useJoin();

    }

    public static void useJoin() throws InterruptedException {
        log.info("我是主线程开始");
        Thread c = new Thread(() -> {
            log.info("我是线程c :{}", "c");
            try {
                Thread.sleep(10000);
                log.info("c等待结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread a = new Thread(() -> {
            // 让c插队
            c.start();
            try {
                c.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("我是线程a :{}", Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
                log.info("a等待结束");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread b = new Thread(() -> {
            log.info("我是线程b");
            try {
                Thread.sleep(3000);
                log.info("b等待结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });



        log.info("我是主线程");
        a.start();
        a.join();
        b.start();
        b.join();
        log.info("我是主线程end");
    }


}
