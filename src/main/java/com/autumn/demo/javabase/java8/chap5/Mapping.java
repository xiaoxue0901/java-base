package com.autumn.demo.javabase.java8.chap5;

import com.alibaba.fastjson.JSON;
import com.autumn.demo.javabase.java8.chap4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xql132@zcsmart.com
 * @date 2019/12/26 18:47
 * @description
 */
public class Mapping {

    public static void main(String[] args) {
        // map: 打印出每道菜的菜名
        List<String> dishNames = Dish.menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        dishNames.forEach(System.out::println);
        System.out.println("------------------------");

        // map : 打印出每个字符串的长度
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        words.stream()
                .map(String::length)
                .forEach(System.out::println);
        System.out.println("------------------------");

        // map: 每道菜的名称有多长
        Dish.menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .forEach(System.out::println);
        System.out.println("------------------------");
        // flatMap
        List<String> words2 = Arrays.asList("Hello", "world");
        List<String[]> r1 = words2.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());
        r1.forEach(e -> System.out.println(JSON.toJSONString(e)));
        System.out.println("------------------------");
        String[] arraysOfWords = {"Goodbye", "World"};
        Stream<String> streamOfWord = Arrays.stream(arraysOfWords);
        words2.stream()
                // ["H","e","l","l","o"] 和["w","o","r","l","d"]
                .map(word -> word.split(""))
                // 得到:Stream<Stream<String>>
                .map(Arrays::stream)
                .distinct()
                .forEach(e -> System.out.println(JSON.toJSONString(e)));
        System.out.println("------------------------");

        // 使用flatMap
        words2.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::println);

        // 习题1
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        squares.forEach(i -> System.out.print(i + ","));
        // 数字列表
        List<Integer> a = Arrays.asList(1, 2, 3);
        List<Integer> b = Arrays.asList(3, 4);
        List<int[]> pairs = a.stream().flatMap(
                i -> b.stream().map(j -> new int[]{i, j})
        ).collect(Collectors.toList());

        List<int[]> pairs2 = a.stream().flatMap(
                i -> b.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j})
        ).collect(Collectors.toList());


    }

}
