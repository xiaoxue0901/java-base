package com.autumn.demo.javabase.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xql132@zcsmart.com
 * @date 2019/2/25 14:06
 * @description 快速排序
 * 对集合中的数据排序, 从小到大的顺序
 */
@Slf4j
public class QuickSort {


    /**
     * 获取基准值=选最左端,最右端和中间位置的三个元素的中值作为基准.
     *
     * @param seq
     * @return
     */
    private int getPovit(int seq[]) {
        // 最左端的值
        int start = seq[0];
        // 最右端的值
        int end = seq[seq.length - 1];
        // 中间位置的值
        int middle = seq[(seq.length - 1) / 2];
        log.info("中间位置是:{}, 其值为:{}", (seq.length - 1) / 2, middle);
        log.info("3个数的值为:{},-{}-{}", start, middle, end);
        // 对三个数由小到大排序. 获取中值. 只有3个值, 枚举就结束了
        return getMidValue(start, middle, end);
    }

    /**
     * 3数取中值
     * 情形1. 3个值不等, 假设a>b>c, 则(b-a)*(b-c)<0; 假设b>a>c, 则(a-b)*(a-c)<0; 假设a>c>b或b>c>a, 则 (c-a)*(c-b)<0;
     * 情形2. 2个值相等, 则(b-a)*(b-c)==0,无论a=b还是c=b, b一定是等值的其中一个.
     * 情形3. 3个值相等. 则任意值都可以.
     * @param a
     * @param b
     * @param c
     * @return
     */
    private static int getMidValue(int a, int b, int c) {
//        if ((b - a) * (a - c) >= 0) { return a; }
//        else if ((a - b) * (b - c) >= 0) { return b; }
//        else { return c; }

        if ((a-b)*(a-c)<=0) {
            return a;
        } else if ((b-a)*(b-c)<=0) {
            return b;
        } else {
            return c;
        }

    }

    /**
     * 快速排序
     * @param seq
     */
    public void quickSort1(int[] seq) {
        //0. 以数据的第一个元素作为基准. 赋值给key.
        int key = seq[0];
        //1.设置2个变量i, j. 排序开始的时候: i=0;j=n-1
        int j = seq.length - 1;
        int s = seq[0];
        // 从右向左, 找比基准小的数据, 找到后与基准交换位置
        for (;j>0; j--) {
            while (seq[j]< key) {


            }


        }



    }

    /**
     * 值交换
     * @param data
     * @param i
     * @param j
     */
    public void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void main(String[] args) {
        int[] seq = {1,9,10,3,8,7,6,2,4};
        QuickSort quickSort = new QuickSort();
        int pivot = quickSort.getPovit(seq);
        log.info("基准值为:{}", pivot);
    }


}
