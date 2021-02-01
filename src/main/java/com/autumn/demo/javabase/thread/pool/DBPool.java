package com.autumn.demo.javabase.thread.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/1
 * @time 10:58 下午
 * @description 实现数据库连接池
 */
public class DBPool {

    // 数据库连接池的容器
    private static LinkedList<Connection> pool = new LinkedList<>();

    public DBPool(int initalSize) {
        if (initalSize > 0) {
            for (int i = 0; i< initalSize; i++) {
                pool.addLast(SqlConnectImpl.fetchConnection());
            }
        }
    }

    // 在mils时间范围内还拿不到数据库连接, 就返回一个null
    public Connection fetchConn(long mils) throws InterruptedException {
        synchronized (pool){
            if (mils <0) {
                // 用不超时
                while (pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long overtime = System.currentTimeMillis() +mils;
                long remain = mils;
                while (pool.isEmpty() && remain>0) {
                    pool.wait(remain);
                    remain = overtime - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()){
                    result = pool.removeFirst();
                }
                return result;
            }
        }

    }

    // 放回数据库连接
    public void releaseConn(Connection conn) {
        if (conn !=null) {
            synchronized (pool) {
                pool.addLast(conn);
                pool.notifyAll();
            }

        }

    }
}
