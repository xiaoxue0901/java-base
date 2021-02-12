package com.autumn.demo.javabase.thread.forkjoin;

import java.util.Random;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/11
 * @time 8:40 上午
 * @description 产生一个整形数组
 */
public class MakeArray {
    /** 数组长度*/
    public static final int ARRAY_LENTH = 4000;

    public static int[] makeArray(){
        // 随机数生成器
        Random random = new Random();
        int[] result = new int[ARRAY_LENTH];
        for (int i = 0; i<ARRAY_LENTH; i++) {
            result[i] = random.nextInt(ARRAY_LENTH*3);
        }
        return result;
    }
}
