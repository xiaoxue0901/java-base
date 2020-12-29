package com.autumn.demo.java8.chap5;

import com.autumn.demo.java8.chap4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xql132@zcsmart.com
 * @date 2019/12/25 19:40
 * @description
 */
public class Filtering {
    public static void main(String[] args) {
        // 用谓词筛选
        List<Dish> vegetarianMenu = Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());

        vegetarianMenu.forEach(System.out::println);
        System.out.println("---------------------------");
        // 排重
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 2, 6, 3, 3, 1);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
        System.out.println("---------------------------");
        // 截短流
        List<Dish> limit = Dish.menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());

        limit.forEach(System.out::println);
        System.out.println("---------------------------");
        // 跳过元素
        List<Dish> skip = Dish.menu.stream()
                .filter(d->d.getCalories()>400)
                .skip(2)
                .collect(Collectors.toList());
        skip.forEach(System.out::println);
        System.out.println("---------------------------");
    }
}
