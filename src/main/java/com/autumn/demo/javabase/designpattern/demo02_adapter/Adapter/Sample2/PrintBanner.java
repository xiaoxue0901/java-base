package com.autumn.demo.javabase.designpattern.demo02_adapter.Adapter.Sample2;


public class PrintBanner extends Print {
    // 成员变量: Banner实例
    private Banner banner;

    // 构造函数: 生成一个banner实例
    public PrintBanner(String string) {
        this.banner = new Banner(string);
    }

    // 调用banner的方法实现功能
    public void printWeak() {
        banner.showWithParen();
    }

    // 调用banner的方法实现功能
    public void printStrong() {
        banner.showWithAster();
    }
}
