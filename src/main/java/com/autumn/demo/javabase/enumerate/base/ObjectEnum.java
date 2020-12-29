package com.autumn.demo.javabase.enumerate.base;

import com.autumn.demo.javabase.bean.User;

/**
 * @author xql132@zcsmart.com
 * @date 2019/2/21 15:26
 * @description 枚举的新用法. 可以是枚举实例
 */
public enum ObjectEnum {
    /**
     * 枚举实例
     */
    OBJECT {
        @Override
        public User getUser() {
            return new User("李四", "北京西路");
        }
    }, OBJECT2 {
        @Override
        public User getUser() {
            return new User("小明");
        }
    };

    private User user;

    ObjectEnum() {
        this.user = getUser();
    }

    public abstract User getUser();
}
