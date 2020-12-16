package com.autumn.demo.javabase.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xql132@zcsmart.com
 * @date 2019/7/16 21:16
 * @description
 */
@Data
public class Employee implements Serializable {
    private String name;
    private double salary;
    private Date hireDay;

    public Employee() {
    }

    public Employee(String name, double salary, Date hireDay) {
        this.name = name;
        this.salary = salary;
        this.hireDay = hireDay;
    }
}
