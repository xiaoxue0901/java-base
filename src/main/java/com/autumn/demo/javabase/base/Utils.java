package com.autumn.demo.javabase.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xql132@zcsmart.com
 * @date 2018/11/12 17:58
 * @description
 */
public class Utils {
    AtomicInteger engineIndex = new AtomicInteger(0);

    private synchronized String selectEngineServer() {
        int index = engineIndex.getAndIncrement(); // 增长一个数, 返回前一个值.
        List<String> engineIds = new ArrayList<>();
        engineIds.add("E001");
        engineIds.add("E002");
        engineIds.add("E003");
        engineIds.add("E004");
        int x = index % engineIds.size();
        System.out.println("x的值是:"+ x);
        String engineId = engineIds.get(x);
        System.out.println("最终的engineId是:{}"+ engineId);
        return engineId;
    }

    public static void main(String[] args) {
        Utils obj = new Utils();
        for (int i = 0; i<10; i++) {
            obj.selectEngineServer();
        }
    }
}
