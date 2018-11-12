package com.autumn.demo.javabase.base;

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

        synchronized (this) {
            long key = System.currentTimeMillis() / 1000;
            Integer count = countPerSecond.get(key);
            countPerSecond.clear();
            if (count == null) {
                countPerSecond.put(key, 1);
            }
            else {
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
}
