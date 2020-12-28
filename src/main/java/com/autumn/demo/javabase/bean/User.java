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

    public static void main(String[] args) {
        // 心得-定义 User类可以用new User();获取很多不同的实例对象. 枚举是列举出有穷序列集.
        // 定义枚举类, 里面只列举出一个值, 此值代表一个User类的实例. 通过getInstance()方法可以获取到INSTANCE的值: 一个User类的实例.
        // Enum: 自由序列化, 线程安全, 保证单例
        // Enum是由class实现的->enum作为一个类来实现单例;
        // Enum是通过继承Enum类实现的, enum不能作为子类继承其他类.也不能被继承, 是final修饰的. 但是可以用来实现接口
        // Enum有且仅有private的构造器. 防止外部的额外构造.
        User user1 = SingletonEnum.INSTANCE.getInstance();
        User user2 = SingletonEnum.INSTANCE.getInstance();
        User user3 = SingletonEnum.INSTANCE.getInstance();
        User user4 = SingletonEnum.INSTANCE.getInstance();
        log.info("获取的实例哈希值:{}", user1.hashCode());
        log.info("获取的实例哈希值:{}", user2.hashCode());
        log.info("获取的实例哈希值:{}", user3.hashCode());
        log.info("获取的实例哈希值:{}", user4.hashCode());
    }
}
