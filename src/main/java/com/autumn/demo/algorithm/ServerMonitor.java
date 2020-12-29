package com.autumn.demo.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * User: Rolandz
 * Date: 7/21/15
 * Time: 1:04 AM
 */
public class ServerMonitor {
    private AtomicLong all = new AtomicLong(0);
    private AtomicLong current = new AtomicLong(0);

    Map<Long, Integer> countPerSecond = new HashMap<>();

    public void incrementRunningProcess() {
        current.incrementAndGet();
        all.incrementAndGet();

        // 每秒的交易吞吐量.
        synchronized (this) {
            // 当前秒
            long key = System.currentTimeMillis() / 1000;
            System.out.println("key 是: "+key);
            // 已秒为key值, 取出对应的count.
            Integer count = countPerSecond.get(key);
            System.out.println("count是: "+ count);
            // 清空
            countPerSecond.clear();
            // 如果当前秒中无值, 则在map中存入key=当前秒, 初始值为1.
            if (count == null) {
                countPerSecond.put(key, 1);
            }
            else {
                // 当前秒中已有值, 则count递增
                countPerSecond.put(key, ++ count);
            }
        }
    }

    public void decrementRunningProcess() {
        current.decrementAndGet();
    }

    public long getCountSinceServerStart() {
        return all.get();
    }

    public long getRunningCount() {
        return current.get();
    }

    public int getCountPerSecond() {
        Integer count = countPerSecond.get(System.currentTimeMillis() / 1000);
        return count == null ? 0 : count;
    }

    public static void main(String[] args) {
        new ServerMonitor().incrementRunningProcess();
    }
}
