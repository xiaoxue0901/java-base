package com.autumn.demo.javabase.bean;

import com.autumn.demo.javabase.io.stream.SerialClone;
import lombok.Data;

import java.util.Date;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/16
 * @time 20:17
 * @description
 */
@Data
public class Operator extends SerialClone {
    private String name;
    private double salary;
    private Date hireDay;

    public Operator(String name, double salary, Date hireDay) {
        this.name = name;
        this.salary = salary;
        this.hireDay = hireDay;
    }
}
