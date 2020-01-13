package com.autumn.demo.javabase.java8.chap5;

import com.alibaba.fastjson.JSON;
import com.autumn.demo.javabase.java8.chap4.Dish;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author xql132@zcsmart.com
 * @date 2019/12/27 14:44
 * @description
 */
public class NumericStreams {
    public static void main(String[] args) {
        // 求和
       int calories = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

       // 将Stream转换为IntStream
        IntStream intStream = Dish.menu.stream()
                .mapToInt(Dish::getCalories);
        // 将数值流转换为Stream
//        Stream<Integer> stream = intStream.boxed();
        // 最大值
        OptionalInt maxOp = intStream.max();
        int max = maxOp.orElse(1);

        // 数值范围
        IntStream stream1 = IntStream.range(1, 100);
        System.out.println("最大值:{}"+stream1.max().getAsInt());
        LongStream stream2 = LongStream.rangeClosed(1, 100);
        System.out.println("最大值:{}"+stream2.max().getAsLong());


        // 数值流应用: 勾股数
        // 创建一个勾股数流
        // 1. 勾股数: a*a + b*b = c*c
        // 2. 定义一个三元数:
        Stream<int[]> pythagorenTriples = IntStream.range(1, 100)
                // 装箱:Stream<Integer>: mapToObj(IntFunction<? extends U> mapper)需要泛型U.
                .boxed()
                // 不使用flatMap, 产生的是n个Stream<int[]>, 使用后就是一个Stream<int[]>
                .flatMap(a -> IntStream.range(a, 100).filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                        // map为流中的每个元素返回int[]. 但是IntStream的map只能为流的每个元素返回另一个int.
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a*a + b*b)})
                );

        pythagorenTriples.limit(5)
                .forEach(t -> System.out.println(JSON.toJSONString(t)));

    }
}
