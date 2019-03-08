package com.autumn.demo.javabase.designpattern.demo05_singleton;

/**
 * @author xql132@zcsmart.com
 * @date 2019/2/21 17:02
 * @description 写法二: 饿汉式(静态代码块)[可用]
 */
public class Singleton2 {
    // 1. static 成员变量
    private static Singleton2 singleton2;

    // 2. 初始化
    static {
        singleton2 = new Singleton2();
    }

    // 3. 私有构造函数
    private Singleton2() {
    }

    // 4. 静态方法获取单例
    public static Singleton2 getInstance() {
        return singleton2;
    }
}
