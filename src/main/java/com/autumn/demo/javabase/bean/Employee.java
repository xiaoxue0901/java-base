package com.autumn.demo.javabase.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author xql132@zcsmart.com
 * @date 2019/7/16 21:16
 * @description
 */
@Data
public class Employee {
    private String name;
    private double salary;
    private Date hireDay;
}
