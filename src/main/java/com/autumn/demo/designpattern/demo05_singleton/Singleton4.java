package com.autumn.demo.designpattern.demo05_singleton;

/**
 * @author xql132@zcsmart.com
 * @date 2019/2/21 17:34
 * @description 写法四: 懒汉式(线程安全, synchronize同步方法)[效率低]
 */
public class Singleton4 {
    // 1. static成员变量
    private static Singleton4 singleton4;

    // 2. 私有构造方法
    private Singleton4() {
    }

    // 3. 静态同步方法获取单例. synchronized修饰, 多线程情形下要排队等待对象锁释放.
    public static synchronized Singleton4 getInstance() {
        if (null == singleton4) {
            singleton4 = new Singleton4();
        }
        return singleton4;
    }
}
