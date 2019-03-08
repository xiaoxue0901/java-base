package com.autumn.demo.javabase.designpattern.demo05_singleton;

/**
 * @author xql132@zcsmart.com
 * @date 2019/2/21 16:49
 * @description 写法一: 饿汉式(静态常量)[可用]
 */
public class Singleton1 {
    // 1. 定义static成员变量
    private final static Singleton1 singleton1 = new Singleton1();

    // 2. 定义私有构造方法
    private Singleton1() {
    }

    // 3. 静态方法获取单例.
    public static Singleton1 getInstance() {
        return singleton1;
    }
}
