package com.autumn.demo.javabase.algorithm.sort;

/**
 * @author xql132@zcsmart.com
 * @date 2020/9/22
 * @time 17:08
 * @description
 */
public class SortUtil {
    /**
     * 数组data中,2个位置的值交换
     * @param data
     * @param i
     * @param j
     */
    public static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
