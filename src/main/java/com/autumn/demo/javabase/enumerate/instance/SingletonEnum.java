package com.autumn.demo.javabase.enumerate.instance;

import com.autumn.demo.javabase.bean.User;

/**
 * @author xql132@zcsmart.com
 * @date 2019/2/21 14:49
 * @description 用枚举实现单例模式
 */
public enum SingletonEnum {
    /**
     * 1. 理解: INSTANCE是DemoEnum的实例.
     */
    INSTANCE;

    /**
     * 2. 定义INSTANCE包含参数User.*.
     */
    private User instance;

    /**
     * 3. 枚举类的私有构造方法. 默认是private的.
     */
    SingletonEnum() {
        instance = new User();
    }

    /**
     * 4. 获取枚举类的具体参数user
     */
    public User getInstance() {
        return instance;
    }
}
