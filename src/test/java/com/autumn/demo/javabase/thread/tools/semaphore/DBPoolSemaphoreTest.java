package com.autumn.demo.javabase.thread.tools.semaphore;

import com.autumn.demo.javabase.thread.SleepTools;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.util.Random;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/12
 * @time 2:44 下午
 * @description
 */
@Slf4j
public class DBPoolSemaphoreTest{

    private static DBPoolSemaphore dbpool = new DBPoolSemaphore();

    private static class BusiThread extends Thread {
        @Override
        public void run() {
            try {
                Random r = new Random();
                long start = System.currentTimeMillis();
                Connection connection = dbpool.takeConnect();
                log.info("thread_{}, 获取数据库连接共耗时{} ms", Thread.currentThread().getId(), System.currentTimeMillis()-start);
                // 模拟业务操作, 线程持有连接查询数据
                SleepTools.ms(100+r.nextInt(100));
                log.info("查询数据完成, 归还链接");
                dbpool.returnConnect(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i<50; i++) {
            Thread thread = new BusiThread();
            thread.start();
        }
    }

}