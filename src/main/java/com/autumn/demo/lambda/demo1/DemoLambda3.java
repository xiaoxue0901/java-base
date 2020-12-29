package com.autumn.demo.lambda.demo1;

import lombok.extern.slf4j.Slf4j;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author xql132@zcsmart.com
 * @date 2019/1/16 19:46
 * @description 方法的引用
 */
@Slf4j
public class DemoLambda3 {
    public static void main(String[] args) {

    }

    private static String get() {
        log.info("静态方法引用");
        return "study";
    }

    private static String con(Integer size) {
        log.info("静态方法引用输入显示: {}", size);
        return "b";
    }

    private static String toUpper(String a) {
        return a.toUpperCase();
    }

    private static Integer getLength(String len1, String len2) {
        return len1.length() + len2.length();
    }

    /**
     * 静态方法引用
     */
    public static void demoSupplier() {
        Supplier<String> supplier = () -> "输出";
        Supplier<String> supplier1 = () -> get();
        // 静态方法的引用:只有输出
        Supplier<String> supplier2 = DemoLambda3::get;

        Consumer<Integer> consumer = (size) -> {
        };
        Consumer<Integer> consumer1 = (size) -> DemoLambda3.con(size);
        // 静态方法的引用:只有输入
        Consumer<Integer> consumer2 = DemoLambda3::con;

        Function<String, String> function = (a) -> {
            return toUpper(a);
        };
        Function<String, String> function2 = DemoLambda3::toUpper;

        BiFunction<String, String, Integer> biFunction = (a, b) -> {
            return getLength(a, b);
        };
        BiFunction<String, String, Integer> biFunction2 = (a, b) -> getLength(a, b);
        BiFunction<String, String, Integer> biFunction3 = DemoLambda3::getLength;

    }
}
