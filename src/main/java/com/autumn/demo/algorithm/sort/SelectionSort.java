package com.autumn.demo.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author xql132@zcsmart.com
 * @date 2020/9/22
 * @time 17:06
 * @description 选择排序
 * 概要: 时间复杂度:n的平方; 空间复杂度:1 稳定性: 不稳定 => 不推荐使用
 * 描述: 选择排序（Selection sort）是一种简单直观的排序算法。
 * 它的工作原理是：第一次从待排序的数据元素中选出最小（或最大）的一个元素，存放在序列的起始位置，然后再从剩余的未排序元素中寻找到最小（大）元素，然后放到已排序的序列的末尾。以此类推，直到全部待排序的数据元素的个数为零。选择排序是不稳定的排序方法。
 */
@Slf4j
public class SelectionSort {

    private static int[] array = {8, 0, 9, 5, 7, 1, 2, 3, 5};

    /**
     * 选择排序:从小到大
     *
     * @param data
     */
    public static void selection(int[] data) {
        log.info("原始数据:{}", data);
        // 从小到大排序
        // 每个数都要和其他的数据比较一遍
        for (int j = 0; j < data.length - 1; j++) {
            // 第一次从待排序的数据元素中选出最小（或最大）的一个元素，存放在序列的起始位置
            for (int i = j + 1; i < data.length; i++) {
                // 此处的data[j]: i和j位置的值在比大小后会交换. 使用交换后的值继续和i位置的值比大小
                // 从大到小
//                if (data[j] < data[i]){
                // 从小到大
                if (data[j] > data[i]) {
                    SortUtil.swap(data, j, i);
                }
            }
        }
        log.info("排序后数据:{}", data);
    }

    /**
     * 选择排序: 每次排序找出最小值的位置后, 再交换值.
     * @param data
     */
    public static void selection1(int[] data) {
        log.info("原始数据:{}", data);
        // 从小到大排序
        // 每个数都要和其他的数据比较一遍
        for (int j = 0; j < data.length - 1; j++) {
            int minPos = j;
            // 第一次从待排序的数据元素中选出最小（或最大）的一个元素，存放在序列的起始位置
            for (int i = j + 1; i < data.length; i++) {
                minPos = data[minPos] > data[i] ? i : minPos;
            }
            SortUtil.swap(data, j, minPos);
        }
        log.info("排序后数据:{}", data);
    }

    /**
     * 使用lambda对int数组选择排序
     *
     * @param data
     */
    public static void selection2(int[] data) {
        IntStream stream = Arrays.stream(data).sorted();
        stream.forEach(System.out::print);
        // 集合的静态方法
        List<Integer> numbers = Arrays.asList(3, 5, 1, 2, 6);
        numbers.sort(Comparator.naturalOrder());
    }

    public static void main(String[] args) {
//        selection(array);
        selection1(array);
//        selection2(array);
    }


}
