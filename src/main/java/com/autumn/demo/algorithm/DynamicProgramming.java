package com.autumn.demo.algorithm;

import com.alibaba.fastjson.JSON;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xql132@zcsmart.com
 * @date 2020/10/28
 * @time 17:49
 * @description 动态规划
 * 给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。
 */
public class DynamicProgramming {
    // 给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。
    public static int get(int[] nums) {
        // 排序
        Arrays.sort(nums);
        // 余数为1的集合
        List<Integer> residueOne = new ArrayList<>();
        // 余数为2的集合
        List<Integer> residueTwo = new ArrayList<>();
        // 所有的数相加
        int sum = Arrays.stream(nums).sum();
        // 刚好能被整除, 直接返回所有数据
        int residue = sum % 3;
        if (residue == 0) {
            return sum;
        }
        // 1. 分别找出被3整除余数为1和余数为2的数据
        for (int num : nums) {
            int res = num % 3;
            if (res == 1) {
                residueOne.add(num);
            } else if (res == 2) {
                residueTwo.add(num);
            }
        }
        // 根据余数删除数据
        if (residue == 1) {
            if (residueTwo.size() >= 2) {
                // 余数为1: 比较(减去residueOne中的一个数)和(减去residueTwo中的2个数) 哪个结果更大, 取数据大的值
                sum = Math.max(sum - residueOne.get(0), sum - residueTwo.get(0) - residueTwo.get(1));
            } else {
                sum = sum - residueOne.get(0);
            }
        } else if (residue == 2) {
            if (residueOne.size() >= 2) {
                // 余数为2: 比较(减去residueOne中的2个数)和(减去residueTwo中的1个数) 哪个结果更大, 取数据大的值
                sum = Math.max(sum - residueOne.get(0) - residueOne.get(1), sum - residueTwo.get(0));
            } else {
                sum = sum - residueTwo.get(0);
            }
        }
        return sum;
    }

    private static int find(List<Integer> other, int residue) {
        System.out.println("要查找的值:{}" + residue);
        System.out.println("范围:{}" + JSON.toJSONString(other));
        // 查找find
        for (Integer i : other) {
            if (residue == i.intValue()) {
                return residue;
            }
        }
        return find(other, residue + 3);
    }

    public static void main(String[] args) {
        // int res = get(new int[]{2, 6, 2, 2, 7});
        // System.out.println("结果" + res);
        String a = "张三";
        System.out.println(new String(a.getBytes(StandardCharsets.UTF_8)));
    }
}
