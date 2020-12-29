package com.autumn.demo.designpattern.demo05_singleton;

/**
 * @author xql132@zcsmart.com
 * @date 2019/2/21 17:45
 * @description 写法六: DCL(双重检查锁机制 Double Check Lock) [推荐用]
 */
public class Singleton6 {
    // 1. static成员变量
    private static Singleton6 singleton6;

    // 2. 私有构造方法
    private Singleton6(){

    }

    // 3. 静态方法获取单例. 使用双重检查
    public static Singleton6 getInstance() {
        if (null == singleton6) { // 第一重检查
            synchronized (Singleton6.class) { // 锁Singleton6类.
                if (null == singleton6) { // 第二重检查
                    return new Singleton6();
                }
            }
        }
        return singleton6;
    }
}
