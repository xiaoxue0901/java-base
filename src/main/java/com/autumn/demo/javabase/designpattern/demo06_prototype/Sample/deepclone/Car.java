package com.autumn.demo.javabase.designpattern.demo06_prototype.Sample.deepclone;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xql132@zcsmart.com
 * @date 2018/11/14 10:50
 * @description
 */
@Data
public class Car implements Serializable {
    private static final long serialVersionUID = -7944023211339133422L;
    private String brand; // 品牌
    private int maxSpeed; // 时速

    public Car(String brand, int maxSpeed) {
        this.brand = brand;
        this.maxSpeed = maxSpeed;
    }
}
