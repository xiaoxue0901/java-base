package com.autumn.demo.javabase.designpattern.demo07_builder.build;

/**
 * @author xql132@zcsmart.com
 * @date 2018/11/8 15:27
 * @description 定义了决定文档结构的方法的抽象类
 */
public abstract class Builder {
    public abstract void makeTitle(String title);
    public abstract void makeString(String str);
    public abstract void makeItems(String[] items);
    public abstract void close();
}
