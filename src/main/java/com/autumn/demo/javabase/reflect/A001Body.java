package com.autumn.demo.javabase.reflect;

import lombok.Data;

/**
 * @author xql132@zcsmart.com
 * @date 2019/11/27 17:09
 * @description
 */
@Data
public class A001Body {
    @BodyAnnonation(len = 20, code = "STR")
    private String name;
    @BodyAnnonation(len = 2, code = "STR")
    private String nation;
    @BodyAnnonation(len = 2, code = "INT")
    private int age;
}
