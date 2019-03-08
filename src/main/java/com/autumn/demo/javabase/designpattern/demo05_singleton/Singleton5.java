package com.autumn.demo.javabase.designpattern.demo05_singleton;

/**
 * @author xql132@zcsmart.com
 * @date 2019/2/21 17:38
 * @description 写法五: 懒汉式(线程安全, 同步代码块)[不推荐]
 */
public class Singleton5 {
    // 1. static成员变量
    private static Singleton5 singleton5;

    // 2. 私有构造方法
    private Singleton5() {
    }

    // 3. 静态同步代码块方法获取单例. 效率低. 与用synchronized修饰方法一样.
    public static Singleton5 getInstance1() {
      synchronized (Singleton5.class) {
          if (null == singleton5) {
              return new Singleton5();
          }
          return singleton5;
      }
    }

    // 3. 静态同步方法获取单例. null == sinleton5这一步无法避免会有线程安全问题
    public static Singleton5 getInstance2() {
        if (null == singleton5) { // 线程不安全
            synchronized (Singleton5.class) {
                return new Singleton5();
            }
        }
        return singleton5;
    }
}
