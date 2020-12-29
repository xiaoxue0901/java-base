package com.autumn.demo.designpattern.demo01_iterator.A1;

public class Book {
    // 成员变量： 书名
    private String name;

    // 构造函数: 构造Book对象时要传入书名.
    public Book(String name) {
        this.name = name;
    }

    // 获取书的名字
    public String getName() {
        return name;
    }
}
