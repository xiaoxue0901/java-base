package com.autumn.demo.javabase.inter;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/30
 * @time 15:30
 * @description 在Moveable接口的基础上扩展一个接口
 */
public interface Powered extends Moveable {
    /**
     * 接口中不能包含实例域或静态方法, 但是可以包含常量
     * 接口中的域自动设置为: public static final
     * public static final double SPEED_LIMIT =95;
     */
    double SPEED_LIMIT = 95;
    /**
     *
     * @return
     */
    double milesPerGallon();
}
