package com.autumn.demo.javabase.generic;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/25
 * @time 17:39
 * @description 泛型接口
 */
public interface BeanFactory<T> {
    /**
     * 获取class类的实例
     * @param clazz
     * @return
     */
    T getBean(Class<T> clazz);
}
