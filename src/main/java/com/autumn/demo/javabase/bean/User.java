package com.autumn.demo.javabase.bean;

import com.autumn.demo.javabase.enumerate.instance.SingletonEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xql132@zcsmart.com
 * @date 2019/2/21 14:50
 * @description
 */
@Data
@Slf4j
public class User {
    private String name;
    private String address;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, String address) {
        this.name = name;
        this.address = address;
    }

}
