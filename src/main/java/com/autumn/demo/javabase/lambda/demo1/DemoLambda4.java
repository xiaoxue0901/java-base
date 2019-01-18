package com.autumn.demo.javabase.lambda.demo1;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

/**
 * @author xql132@zcsmart.com
 * @date 2019/1/17 13:07
 * @description 实例方法引用
 */
@Slf4j
public class DemoLambda4 {

    public static void main(String[] args) {
        DemoLambda4 demo = new DemoLambda4();
        Supplier<String> supplier = () -> demo.put();

        Supplier<String> supplier2 = demo::put;
        log.info("Supplier结果: {}", supplier.get());
    }


    public String put() {
        return "hello";
    }


}
