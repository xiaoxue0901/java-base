package com.autumn.demo.javabase.enumerate.base;

/**
 * @author xql132@zcsmart.com
 * @date 2019/1/14 17:40
 * @description 枚举: 食物枚举
 */
public enum FoodEnum {
    /**
     * 食物
     */
    FRUIT("水果"),
    MEAT("肉"),
    RICE("大米");

    private String descripe;

    FoodEnum(String descripe) {
        this.descripe = descripe;
    }

}
