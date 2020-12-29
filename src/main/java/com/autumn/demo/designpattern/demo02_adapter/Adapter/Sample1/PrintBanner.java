package com.autumn.demo.designpattern.demo02_adapter.Adapter.Sample1;

/**
 * 1. 继承Banner
 * 2. 实现Print
 */
public class PrintBanner extends Banner implements Print {

    // 构造函数:
    public PrintBanner(String string) {
        super(string);
    }

    // 实现Print接口方法
    public void printWeak() {
        showWithParen();
    }

    // 实现Print接口方法
    public void printStrong() {
        showWithAster();
    }
}
