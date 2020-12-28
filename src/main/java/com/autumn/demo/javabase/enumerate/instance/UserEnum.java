package com.autumn.demo.javabase.enumerate.instance;

import com.autumn.demo.javabase.bean.User;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/28
 * @time 18:53
 * @description
 */
public enum  UserEnum {
    /**
     * 枚举
     */
    INSTANCE;

    private User user;

    UserEnum() {
        user = new User();
    }

    public static User getInstance() {
        return INSTANCE.user;
    }
}
