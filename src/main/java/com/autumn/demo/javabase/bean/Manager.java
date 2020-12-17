package com.autumn.demo.javabase.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/16
 * @time 13:05
 * @description
 */
public class Manager extends Employee implements Serializable {
    public Manager(String name, double salary, Date hireDay) {
        super(name, salary, hireDay);
    }
}
