package com.autumn.demo.algorithm.sort;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;


/**
 * @author xql132@zcsmart.com
 * @date 2019/2/27 14:33
 * @description 插入排序: 将一个数据插入到一个有序数据中.得到一个新的,个数加一的有序数据
 * 1. 由N-1趟排序组成.(从第二个数开始排序)
 * 2. 从p=1到p=N-1趟之前, 插入排序保证从位置0到位置p-1上的元素为已排序状态.(位置从0开始, 趟数从1开始,因为一个数不排序)
 * 例如: N=10, p=3时, 是从位置0到位置2的元素是已排序状态.排完序后, 则位置0到位置3的元素是已排序状态.
 * 3. 第1趟, 位置1的数据和位置1的数据比较;
 * 第2趟, 位置2的数据和位置1,位置0的数据比较;
 * 第3趟, 位置3的数据和位置2,1,0的数据比较;...
 * 找到比他小的数据, 排在他之后.
 */
@Slf4j
public class InsertionSort {
    /**
     * 插入
     *
     * @param a
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a) {
        int j;
        // 从第二个数开始排序. p代表一共需要排几次: p = a.length-1
        for (int p = 1; p < a.length; p++) {
            // tmp 代表要插入的数据
            AnyType tmp = a[p];
            // 要插入的数据和有序数据中的数据比较,
            for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
            log.info("第{}趟, 数据为:{}",p, JSONObject.toJSON(a));
        }

    }

    public static void main(String[] args) {
//        String[] a = {"34","8","64","51","32","21"};
        Integer[] a = {34,8,64,51,32,21};
        insertionSort(a);
    }
}
