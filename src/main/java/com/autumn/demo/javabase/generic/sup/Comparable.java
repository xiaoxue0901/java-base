package com.autumn.demo.javabase.generic.sup;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/30
 * @time 18:12
 * @description 声明一个泛型接口
 */
public interface Comparable<T> {
    /**
     *
     * @param other
     * @return
     */
    int compareTo(T other);
}
