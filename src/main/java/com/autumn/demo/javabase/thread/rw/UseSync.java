package com.autumn.demo.javabase.thread.rw;

import com.autumn.demo.javabase.thread.SleepTools;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/13
 * @time 12:02 上午
 * @description
 */
public class UseSync implements GoodService {
    private GoodsInfo goodsInfo;

    public UseSync(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @Override
    public synchronized GoodsInfo getNum() {
        SleepTools.ms(5);

        return this.goodsInfo;
    }

    @Override
    public synchronized void setNum(int number) {
        SleepTools.ms(5);
        goodsInfo.changeNumber(number);

    }
}
