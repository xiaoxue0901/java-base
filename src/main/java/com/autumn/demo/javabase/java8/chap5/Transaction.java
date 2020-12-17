package com.autumn.demo.javabase.java8.chap5;

import lombok.Data;

/**
 * @author xql132@zcsmart.com
 * @date 2019/12/27 10:31
 * @description 交易信息
 */
@Data
public class Transaction {
    private Trader trader;
    private int year;
    private int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }
}
