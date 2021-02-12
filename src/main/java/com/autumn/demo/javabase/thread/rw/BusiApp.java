package com.autumn.demo.javabase.thread.rw;

import com.autumn.demo.javabase.thread.SleepTools;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/13
 * @time 12:06 上午
 * @description
 */
@Slf4j
public class BusiApp {
    static final int readWriteRatio = 10;// 读写线程比例
    static final int minThreadCount = 3; // 最少线程数

    private static class GetThread implements Runnable {
        private GoodService goodService;

        public GetThread(GoodService goodService) {
            this.goodService = goodService;
        }

        @Override
        public void run() {
            long stat = System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {
                goodService.getNum();
            }
            log.info("{} 读取商品数据耗时 {} ms", Thread.currentThread().getName(), System.currentTimeMillis() - stat);
        }
    }

    private static class SetThread implements Runnable {
        private GoodService goodService;

        public SetThread(GoodService goodService) {
            this.goodService = goodService;
        }

        @Override
        public void run() {
            long stat = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                SleepTools.ms(50);
                goodService.setNum(i);
            }
            log.info("{} 写商品数据耗时 {} ms", Thread.currentThread().getName(), System.currentTimeMillis() - stat);
        }
    }

    public static void main(String[] args) {
        GoodsInfo goodsInfo = new GoodsInfo("cup", 10000, 10000);
//        GoodService goodService = new UseSync(goodsInfo);
        GoodService goodService = new UseRwLock();
        for (int i = 0; i < minThreadCount; i++) {
            Thread setT = new Thread(new SetThread(goodService));
            for (int j = 0; j < readWriteRatio; j++) {
                Thread getT = new Thread(new GetThread(goodService));
                getT.start();
            }
            SleepTools.ms(100);
            setT.start();

        }
    }
}
