package com.autumn.demo.javabase.algorithm;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

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
        int sum = 0;
        List<Integer> result = new ArrayList<>(nums.length);
        List<Integer> other = new ArrayList<>(nums.length);
        // 1. 找出被3整除的数据
        for (int num : nums) {
            if (num % 3 == 0) {
                result.add(num);
                sum = sum + num;
            } else {
                other.add(num);
            }
        }
        // 不被3整除的数据和
        int tempSum = other.stream().mapToInt(e -> e).sum();
        int find = 0;
        // 求余数
        int residue = tempSum % 3;
        if (residue == 0) {
            sum = tempSum + sum;
        } else if (residue == 1) {
            // 余数==2
            find = find(other, 1);
            System.out.println("查出多余的值:"+find);
            sum = tempSum - find + sum;

        } else if (residue == 2) {
            // 余数==2
            find = find(other, 2);
            System.out.println("要查找的值:{}"+find);
            sum = tempSum - find + sum;
        }
        return sum;
    }

    private static int find(List<Integer> other, int residue) {
        System.out.println("要查找的值:{}"+residue);
        System.out.println("范围:{}"+ JSON.toJSONString(other));
        // 查找find
        for (Integer i : other) {
            if (residue == i.intValue()) {
                return residue;
            }
        }
        return find(other, residue+3);
    }

    public static void main(String[] args) {
        int res = get(new int[]{2,6,2,2,7});
        System.out.println("结果"+res);
    }
}
