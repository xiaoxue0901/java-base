package com.autumn.demo.javabase.lambda.demo1;

import java.util.function.Supplier;

/**
 * @author xql132@zcsmart.com
 * @date 2019/1/17 19:49
 * @description 构造方法引用: 如果函数式接口的实现恰好可以通过调用一个类的构造方法来实现, 那么就可以使用构造方法引用
 */
public class DemoLambda6 {

    public void testConst() {
        // 全写
        Supplier<Person> supplier = () -> {
            return new Person();
        };

        // 构造方法引用写法
        Supplier<Person> supplier1 = Person::new;
    }
}
