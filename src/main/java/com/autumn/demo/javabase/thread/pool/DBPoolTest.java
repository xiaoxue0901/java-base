package com.autumn.demo.javabase.thread.pool;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/9
 * @time 7:50 下午
 * @description
 */
@Slf4j
public class DBPoolTest {
    static DBPool pool = new DBPool(10);
    // 控制器: 控制main线程将会等待所有Woker结束后才能继续执行
    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        // 线程数量
        int threadCount = 50;
        end = new CountDownLatch(threadCount);
        // 每个线程的操作次数
        int count = 20;
        // 计数器: 统计可以拿到连接线程
        AtomicInteger got = new AtomicInteger();
        // 计数器: 统计没有拿到连接的线程
        AtomicInteger noGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new Worker(count, got, noGot), "worker_" + i);
            thread.start();
        }
        // main线程在此处等待
        end.wait();
        log.info("总共尝试了:{}", (threadCount * count));
        log.info("拿到连接的次数:{}", got);
        log.info("没能连接的次数:{}", noGot);
    }

    static class Worker implements Runnable {
        int count;
        AtomicInteger got;
        AtomicInteger noGot;

        public Worker(int count, AtomicInteger got, AtomicInteger noGot) {
            this.count = count;
            this.got = got;
            this.noGot = noGot;
        }

        @Override
        public void run() {
            while (count > 0) {
                try {
                    // 从线程池中获取连接, 如果1000s内无法获取到, 将会返回null
                    // 分别获取连接获取的数量got 和未获取到的数量noGot
                    Connection connection = pool.fetchConn(1000);

                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConn(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        noGot.incrementAndGet();
                        log.info("{}等待超时!", Thread.currentThread().getName());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }

}
