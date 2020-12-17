package com.autumn.demo.javabase.base.chapter3;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xql132@zcsmart.com
 * @date 2019/10/16 18:15
 * @description 运算符
 */
@Slf4j
public class C5Operator {

    /**
     * 自增运算符:后缀 ++
     *
     * @param i
     * @return 返回变量原来的值, 再加1. (先参与运算, 再加1)
     * 表达式将返回变量的旧值, 在此时刻, 内存将同时存储旧值和新值2个变量, 旧值是返回后就不被使用的临时变量. 但这个临时变量是不必要的, 亦即'拷贝旧值的动作'
     * 以及'占用的内存'都是不必要的.
     */
    public static int increaseOperator_suffix(int i) {
        // i =10; c = ++i;
        // i自增, i = 11.
        // 把i的值copy到临时变量temp, 此时临时变量 temp = 11
        // 把临时变量temp的值copy给c, 故c = 11

//        return i++; // 返回临时变量temp的值
        i++;
        // 返回自增后的值
        return i;
    }

    /**
     * 自增运算符:前缀 ++
     *
     * @param i
     * @return 先进行加1运算, 返回加1后的值
     * 优先使用前缀的理由: 避免不必要的拷贝和内存浪费
     */
    public static int increaseOperator_prefix(int i) {
        // i =10; c = i++;
        // 把i的值copy到临时变量 temp, temp = 10
        // 给i自增, i = 11. 但是临时变量temp的值还是10
        // 把临时变量temp的值copy给c, 故c = 10
        return ++i;
    }

    /**
     * 前后缀++的区别:
     */
    public static void compareIncreaseOperator() {
        int m = 7;
        int n = 7;
        // 前缀方式: m先加1再乘2
        int a = 2 * ++m;
        // 后缀方式: m先乘2赋值给b, n再加1
        int b = 2 * n++;
        log.info("a的值:{}, b的值:{}", a, b);
        log.info("m的值: {}, n的值: {}", m, n);

        // 加括号后, 结果一样 , 因为n++返回的是旧值
//        int c = 2*(++m);
//        int d = 2*(n++);
        int c = 2 * increaseOperator_prefix(m);
        int d = 2 * increaseOperator_suffix(n);
        log.info("c的值:{}, d的值:{}", c, d);
        log.info("m的值: {}, n的值: {}", m, n);

        log.info("后缀的值:{}", m++);
    }

    public static void main(String[] args) {
        compareIncreaseOperator();
        log.info("前缀:{}", increaseOperator_prefix(1));
        log.info("后缀:{}", increaseOperator_suffix(1));
    }
    // 自减运算符 --

}
