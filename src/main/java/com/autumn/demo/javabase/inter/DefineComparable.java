package com.autumn.demo.javabase.inter;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/30
 * @time 15:10
 * @description 定义接口Comparable
 */
public interface DefineComparable<T> {

    /**
     * 相等: 0
     * 大于other: >0
     * 小于other: <0
     *
     * @param other
     * @return
     */
    int compareTo(T other);
}
