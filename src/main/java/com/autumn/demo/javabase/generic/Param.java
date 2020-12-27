package com.autumn.demo.javabase.generic;

import com.autumn.demo.javabase.bean.Employee;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/25
 * @time 16:09
 * @description
 */
@Slf4j
public class Param {

    /**
     * 形参说明: 此处的a, b是形参
     *
     * @param a
     * @param b
     */
    public void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
        log.info("a:{}, b:{}", a, b);
    }

    /**
     * 实参说明
     */
    public void useSwap() {
        // 4, 9是实际的参数值, 叫实参
        swap(4, 9);
    }

    /**
     * 传值调用
     */
    public void valuePass() {
        int i = 10;
        int j = 5;
        log.info("i: {}, j:{}", i, j);
        swap(i, j);
        log.info("i:{}, j:{}", i, j);
    }


    /**
     * 传引用调用
     */
    public void callByReference() {
        Data data = new Data();
        log.info("data.i:{}, data.j:{}", data.i, data.j);
        swapRef(data);
        log.info("data.i:{}, data.j:{}", data.i, data.j);
    }

    public void swapRef(Data data) {
        int temp = data.i;
        data.i = data.j;
        data.j = temp;
        log.info("i:{}, j:{}", data.i, data.j);
    }

    class Data {
        int i = 10;
        int j = 5;
    }
}
