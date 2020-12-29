package com.autumn.demo.designpattern.demo02_adapter.Adapter.Sample1;

/**
 * 打印广告: 1. 带括号;2. 带*号
 */
public class Banner {
    // 私有变量: 字符串
    private String string;

    // 构造函数
    public Banner(String string) {
        this.string = string;
    }

    // 将字符串用括号括起来
    public void showWithParen() {
        System.out.println("(" + string + ")");
    }

    // 用字符串前后加*号
    public void showWithAster() {
        System.out.println("*" + string + "*");
    }
}
