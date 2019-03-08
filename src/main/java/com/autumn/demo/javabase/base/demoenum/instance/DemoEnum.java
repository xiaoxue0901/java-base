package com.autumn.demo.javabase.base.demoenum.instance;

/**
 * @author xql132@zcsmart.com
 * @date 2019/2/21 14:49
 * @description 用单例模式实现枚举
 */
public enum DemoEnum {
    // 理解: INSTANCE是DemoEnum的实例.
    INSTANCE;

    // 定义INSTANCE包含参数User.
    private User instance;

    // 枚举类的私有构造方法. 默认是private的.
     DemoEnum() {
        instance = new User();
    }

    // 获取枚举类的具体参数user
    public User getInstance() {
        return instance;
    }
}
