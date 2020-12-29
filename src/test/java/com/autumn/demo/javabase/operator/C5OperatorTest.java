package com.autumn.demo.javabase.operator;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author xql132@zcsmart.com
 * @date 2019/10/16 19:46
 * @description
 */
@Slf4j
public class C5OperatorTest {

    @Test
    public void testIncrementOperater() {
        int a = 10;

        // b = 2*a++ = 2*temp
        int b = 2*a++; // 执行后 b = 20, a = 11

        int c = a++;
        // 把a的值copy到临时变量 temp, temp = 10
        // 给a自增, a = 11. 但是临时变量temp的值还是10
        // 把临时变量temp的值copy给c, 故c = 10

        // a++ 打印后是11, b = 20
        log.info("执行后结果: a++ = {}, b= {}, c = {}, a={}", a++, b, c, a);
        C5Operator.compareIncreaseOperator();
    }
}