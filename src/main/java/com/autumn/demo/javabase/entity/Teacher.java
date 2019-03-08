package com.autumn.demo.javabase.entity;

import lombok.Data;

import java.util.List;

/**
 * @author xql132@zcsmart.com
 * @date 2019/3/6 14:10
 * @description
 */
@Data
public class Teacher {
    private String id;
    private String name;
    private String subject;
    private List<Student> students;
}
