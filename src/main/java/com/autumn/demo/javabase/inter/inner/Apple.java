package com.autumn.demo.javabase.inter.inner;

import com.autumn.Application;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/30
 * @time 16:52
 * @description
 */
public class Apple {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void creat() {
        Apple apple = new Apple();
        // 编写内部类对象的构造器
        Apple.LittleApple littleApple = apple.new LittleApple();
    }

    class LittleApple{

        public void eat() {
            // 外围类引用
            String name = Apple.this.name;
        }
    }
}
