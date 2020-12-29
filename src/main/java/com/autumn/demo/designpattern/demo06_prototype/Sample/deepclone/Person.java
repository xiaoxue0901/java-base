package com.autumn.demo.designpattern.demo06_prototype.Sample.deepclone;

import com.autumn.demo.designpattern.demo06_prototype.Sample.framework.Manager;
import lombok.Data;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author xql132@zcsmart.com
 * @date 2018/11/14 10:47
 * @description
 */
@Data
public class Person implements Serializable, Product2 {
    private static final long serialVersionUID = 1189804186372312017L;
    private String name;
    private int age;
    private Car car;

    public Person(String name, int age, Car car) {
        this.name = name;
        this.age = age;
        this.car = car;
    }

    @Override
    public String use(String s) {
        System.out.println("输出深度赋值的值:"+ s);
        return s;
    }

    @Override
    public Person createClone() {
        Person p = null;
        try {
             p = Manager.createDeepObj(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return p;
    }
}
