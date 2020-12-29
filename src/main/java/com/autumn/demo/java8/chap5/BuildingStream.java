package com.autumn.demo.java8.chap5;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

/**
 * @author xql132@zcsmart.com
 * @date 2019/12/27 16:49
 * @description
 */
public class BuildingStream {
    public static void main(String[] args) {
        // 由值构建流
        // 空流
        Stream<String> emptyStream = Stream.of();
        Stream<String> strStream = Stream.of("Java 8", "Lambdas", "in", "action");
        strStream.map(String::toUpperCase).collect(Collectors.toList());

        // 由数组创建流
        String[] array = new String[]{"Java 8", "Lambdas", "in", "action"};
        Stream<String> arrayStream = Arrays.stream(array);
        System.out.println(arrayStream.collect(joining()));

        // 由文件生成流
        try {
            Stream<String> lines = Files.lines(Paths.get("D:\\workspace\\github\\java-base\\src\\main\\resources\\data.txt"), Charset.defaultCharset());

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 由函数生成流
        // 迭代
        Stream<Integer> stream = Stream.iterate(0, n -> n +2)
                .limit(10);
        stream.forEach(System.out::println);

        // 斐波那契元组序列
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .map(t -> t[0])
                .forEach(System.out::println);


        // 生成
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
        Stream.generate(() -> 2).limit(10).forEach(System.out::println);



    }
}
