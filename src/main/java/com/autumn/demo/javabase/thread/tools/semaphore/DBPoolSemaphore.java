package com.autumn.demo.javabase.thread.tools.semaphore;

import com.autumn.demo.javabase.thread.pool.SqlConnectImpl;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/12
 * @time 2:37 下午
 * @description
 */
@Slf4j
public class DBPoolSemaphore {
    private final static int POOL_SIZE = 10;
    private final Semaphore useful;
    private final Semaphore useless;

    private static LinkedList<Connection> pool = new LinkedList<>();

    public DBPoolSemaphore() {
        this.useful = new Semaphore(POOL_SIZE);
        this.useless = new Semaphore(0);
    }

    static {
        for (int i = 0; i<POOL_SIZE; i++) {
            pool.addLast(SqlConnectImpl.fetchConnection());
        }
    }

    public void returnConnect(Connection connection) throws InterruptedException {
        if (null!=connection) {

            useless.acquire();
            synchronized (pool) {
                pool.addLast(connection);
            }
            useful.release();
        }
    }

    public Connection takeConnect() throws InterruptedException {
        useful.acquire();
        Connection conn;
        synchronized (pool) {
            conn = pool.removeFirst();
        }
        useless.release();
        return conn;
    }
}
