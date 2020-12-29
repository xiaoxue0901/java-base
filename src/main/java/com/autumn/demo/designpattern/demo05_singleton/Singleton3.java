package com.autumn.demo.designpattern.demo05_singleton;

/**
 * @author xql132@zcsmart.com
 * @date 2019/2/21 17:31
 * @description 写法三: 懒汉式(线程不安全)[不可用]
 */
public class Singleton3 {
    // 1. static 成员变量
    private static Singleton3 singleton3;

    // 2. 私有构造方法
    private Singleton3() {
    }

    // 3. 静态方法获取单例.
    public static Singleton3 getInstance() {
        // 这步判断, 如果有2个线程同时进入, 则会产生2个实例, 就不是单例的了. 线程不安全.
        if (null == singleton3) {
            singleton3 = new Singleton3();
        }
        return singleton3;
    }
}
