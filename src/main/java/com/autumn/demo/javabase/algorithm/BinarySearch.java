package com.autumn.demo.javabase.algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

/**
 * @author xql132@zcsmart.com
 * @date 2019/10/16 11:10
 * @description 二分查找(Binary Search)
 * 问题描述: 给定n个元素, 这些元素是有序的(假定为升序), 从中查找特定元素x.
 * 算法设计: 用一维数组S[]存储有序序列, 设变量low和high表示查找范围的下界和上界, middle表示查找范围的中间位置, x为特定的查找元素.
 */
@Slf4j
public class BinarySearch {

    /**
     * 二分查找(升序)
     *
     * @param s 给定的升序数组
     * @param x 要查找的元素
     * @return 返回 查找次数
     */
    public static int binarySearch_asc(int[] s, int x) {
        // 下界
        int low = 0;
        // 上界 (位置从0开始)
        int high = s.length - 1;
        // 查找次数
        int count = 0;

        // 前提 low<=high
        while (low <= high) {
            // 不能用count=+1
            count += 1;
            // 中间元素位置
            int middle = (low + high) / 2;
            log.debug("下界: {},上界: {}, 中间位置:{}, 中间元素:{}", low, high, middle, s[middle]);
            // 用x和中间元素s[middle]比较
            if (s[middle] == x) {
                // 结束
                log.info("二分查找结束");
                break;
            }
            // 默认升序
            if (s[middle] < x) {
                // 当中间元素的值小于x, 则在后半部分查找
                low = middle + 1;
            } else {
                // 当中间元素的值大于x (s[middle] > x), 则在前半部分查找
                high = middle - 1;
            }
        }
        return count;
    }

    /**
     * 二分查找(降序)
     *
     * @param s 给定的升序数组
     * @param x 要查找的元素
     * @return 返回 查找次数
     */
    public static int binarySearch_desc(int[] s, int x) {
        // 下界
        int low = 0;
        // 上界 (位置从0开始)
        int high = s.length - 1;
        // 查找次数
        int count = 0;

        // 前提 low<=high
        while (low <= high) {
            // 不能用count=+1
            count += 1;
            // 中间元素位置
            int middle = (low + high) / 2;
            log.debug("下界: {},上界: {}, 中间位置:{}, 中间元素:{}", low, high, middle, s[middle]);
            // 用x和中间元素s[middle]比较
            if (s[middle] == x) {
                // 结束
                log.info("二分查找结束");
                break;
            }
            // 默认降序(由大到小)
            if (s[middle] < x) {
                // 当中间元素的值小于x, 则在前半部分查找
                high = middle - 1;
            } else {
                // 当中间元素的值大于x (s[middle] > x), 则在后半部分查找
                low = middle + 1;
            }
        }
        return count;
    }

    private static int pick = 3;
    public static void main(String[] args) {
        int result = new BinarySearch().guessNumber(3);
        // System.out.println("猜数字:" + 2126753390*2);
        System.out.println("猜数字:" + result);
    }

    public int guessNumber(int n) {
        // flag=0, 说明pick=mid, 结束
        int low = 1;
        int high = n;
        while (low < high) {
            // 如果使用low+high/2取平均值会溢出
            int mid = high/2+low/2;
            // 比较大小
            int flag = guess(mid);
            if (flag == -1) {
                // 左边数组, 最大边界
                high = mid - 1;
            } else if (flag == 1) {
                // 右边位置, 最小边界
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return low;
    }

    /**
     * 获取猜测结果
     */
    private int guess(int gusNumber) {
        if (pick < gusNumber) {
            return -1;
        } else if (pick > gusNumber) {
            return 1;
        } else {
            return 0;
        }
    }

}
