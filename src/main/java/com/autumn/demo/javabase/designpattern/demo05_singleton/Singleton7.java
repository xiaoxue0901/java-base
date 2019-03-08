package com.autumn.demo.javabase.designpattern.demo05_singleton;

/**
 * @author xql132@zcsmart.com
 * @date 2019/2/21 17:57
 * @description 写法七: 静态内部类[推荐用]
 */
public class Singleton7 {
    // 1. 在私有静态内部类中定义static final 成员变量并实例化
    private static class Demo{
        private static final Singleton7 INSTANCE = new Singleton7();
    }

    // 2. 私有构造方法
    private Singleton7() {}

    // 3. 静态方法获取单例
    public static Singleton7 getInstance() {
        return Demo.INSTANCE;
    }
}
