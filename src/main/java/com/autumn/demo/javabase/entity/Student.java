package com.autumn.demo.javabase.entity;

import lombok.Data;

/**
 * @author xql132@zcsmart.com
 * @date 2019/3/6 14:10
 * @description
 */
@Data
public class Student {
    private String id;
    private String name;
    private int age;

    public Student() {
    }

    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
