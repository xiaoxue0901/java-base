package com.autumn.demo.javabase.thread.rw;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/13
 * @time 12:03 上午
 * @description
 */
public class GoodsInfo {
    private String name;
    private int num;
    private int price;

    public void changeNumber(int number) {
    }

    public GoodsInfo(String name, int num, int price) {
        this.name = name;
        this.num = num;
        this.price = price;
    }
}
