package com.autumn.demo.javabase.java8.chap5;

import lombok.Data;

/**
 * @author xql132@zcsmart.com
 * @date 2019/12/27 10:31
 * @description 交易员
 */
@Data
public class Trader {
    private String name;
    private String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }
}
