package com.autumn.demo.lambda.demo1;

import lombok.extern.slf4j.Slf4j;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author xql132@zcsmart.com
 * @date 2019/1/17 17:33
 * @description 对象方法引用: 抽象方法的第一个参数类型刚好是实例方法的类型, 抽象方法剩余的参数恰好可以当做实例方法的参数. 如果函数式接口的实现能由上面说的实例方法调用来实现的话, 那么就可以使用对象方法引用
 */
@Slf4j
public class DemoLamda5 {

    public String getA(String b) {
        return b;
    }

    public String getB() {
        return "test";
    }

    /**
     * 注意不要用函数式接口的泛型参数来对比参数:
     * 比如函数式接口: BiFunction<DemoLamda5, String, String>
     *  要对比它的抽象方法: R apply(T t, U u);
     *  第一个参数T, 要是实例的类型DemoLamda5
     *  第二个参数U, 要是实例方法getA(String b)的参数b类型一致
     *  R是出参, 不是抽象方法的入参.
     */
    public void test1() {

        BiConsumer<DemoLamda5, String> consumer = (DemoLamda5 a, String b) -> new DemoLamda5().getA(b);
        // 对象方法调用.
        BiConsumer<DemoLamda5, String> consumer2 = DemoLamda5::getA;

        Function<DemoLamda5, String> function1 = DemoLamda5::getB;

        BiFunction<DemoLamda5, String, String> biFunction = (a, b) -> new DemoLamda5().getA(b);
        BiFunction<DemoLamda5, String, String> biFunctio2n = DemoLamda5::getA;
    }
}
