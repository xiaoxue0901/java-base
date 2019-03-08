package com.autumn.demo.javabase.entity;

import lombok.Data;

import java.util.List;

/**
 * @author xql132@zcsmart.com
 * @date 2019/3/6 14:09
 * @description
 */
@Data
public class School {
    private String address;
    private String name;
    private List<Teacher> teacher;
}
