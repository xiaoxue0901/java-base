package com.autumn.demo.javabase.base.demo_generic;

/**
 * @author xql132@zcsmart.com
 * @date 2019/1/18 17:01
 * @description
 */
public class GenericMethod<T> {
    T data;

    /**
     * 定义泛型方法
     * @param u
     * @param <U>
     * @return
     */
    private <U> U getResult(U u) {
        return u;
    }

    /**
     * 定义泛型方法.
     * @param k
     * @param v
     * @param <K>
     * @param <V>
     * @return
     */
    private <K, V> V getValue(K k, V v) {
        return v;
    }
}
