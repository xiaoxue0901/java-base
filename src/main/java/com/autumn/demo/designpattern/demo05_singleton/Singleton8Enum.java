package com.autumn.demo.designpattern.demo05_singleton;

/**
 * @author xql132@zcsmart.com
 * @date 2019/2/21 18:04
 * @description 写法八: 用枚举实现单例模式[推荐用]
 * 更详细见 com.autumn.demo.javabase.demoenum.instance.DemoEnum
 */

public enum Singleton8Enum {
    INSTANCE;
    public void getInstance() {
        System.out.println("用枚举实现单例模式");
    }

    // 复杂实例参见: com.autumn.demo.javabase.demoenum.instance.DemoEnum
}
