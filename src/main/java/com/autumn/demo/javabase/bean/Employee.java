package com.autumn.demo.javabase.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xql132@zcsmart.com
 * @date 2019/7/16 21:16
 * @description
 */

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getHireDay() {
        return hireDay;
    }

    public void setHireDay(Date hireDay) {
        this.hireDay = hireDay;
    }
}
