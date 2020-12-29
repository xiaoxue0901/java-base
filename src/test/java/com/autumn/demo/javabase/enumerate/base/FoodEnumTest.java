package com.autumn.demo.javabase.enumerate.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/28
 * @time 17:07
 * @description
 */
@Slf4j
public class FoodEnumTest {

    @Test
    public void values() {
        // values(): 返回包含全部枚举值的数组
        FoodEnum[] foodEnums = FoodEnum.values();
        for (FoodEnum food : foodEnums) {
            // toString(): 返回枚举常量名(逆方法:valueOf); ordinal(): enum声明中枚举常量的值
            log.info("food name:{} , ordinal:{}", food.toString(), food.ordinal());
        }

    }

    @Test
    public void valueOf() {
        // 返回指定名字, 给定类的枚举常量
        FoodEnum foodEnum = FoodEnum.valueOf("RICE");
        FoodEnum foodEnum1 = Enum.valueOf(FoodEnum.class, "RICE");
        log.info("FoodEnum.valueOf() :{}" , foodEnum.toString());
        log.info("Enum.valueOf() :{}" , foodEnum1.toString());

    }

    @Test
    public void ordinal() {
        // enum中枚举常量的位置, 从0计数
        log.info("RICE ordinal: {}", FoodEnum.RICE.ordinal());
    }

    @Test
    public void compareTo() {
        int res = FoodEnum.RICE.compareTo(FoodEnum.RICE);
        log.info("res (-1: 枚举常量RICE出现在'肉'之前; 0: RICE==other; 正值: RICE的ordinal>肉的ordinal):{}", res);
    }
}