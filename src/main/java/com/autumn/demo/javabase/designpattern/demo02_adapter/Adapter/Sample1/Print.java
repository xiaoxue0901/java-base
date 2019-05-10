package com.autumn.demo.javabase.designpattern.demo02_adapter.Adapter.Sample1;

/**
 * 定义打印方法
 */
public interface Print {
    // 抽象方法: 弱化字符串
    public abstract void printWeak();
    // 抽象方法: 强化字符串
    public abstract void printStrong();
}
