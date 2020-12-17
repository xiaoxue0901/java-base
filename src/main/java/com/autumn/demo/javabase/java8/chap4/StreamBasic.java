package com.autumn.demo.javabase.java8.chap4;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xql132@zcsmart.com
 * @date 2019/12/25 15:09
 * @description
 */
@Slf4j
public class StreamBasic {

    public static void main(String[] args) {
        // java7排序
        getLowCaloricDishsNameInJava7(Dish.menu).forEach(System.out::println);
        log.info("----------------------");
        // Java8
        getLowCaloricDishsNameInJava8(Dish.menu).forEach(System.out::println);
        log.info("----------------------");
        // java8并行
        getLowCaloricDishsNameInJava8_paral(Dish.menu).forEach(System.out::println);
    }

    /**
     * 使用Java7: 返回低热量的菜肴名称, 并按照卡路里进行排序
     *
     * @param dishes
     * @return
     */
    public static List<String> getLowCaloricDishsNameInJava7(List<Dish> dishes) {
        // 找出低卡路里的菜
        List<Dish> lowCaloricDishs = new ArrayList<>();
        for (Dish d : dishes) {
            if (d.getCalories() < 400) {
                lowCaloricDishs.add(d);
            }
        }
        // 用匿名类对菜排序
        Collections.sort(lowCaloricDishs, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });
        // 菜名列表
        List<String> lowCaloricDishName = new ArrayList<>();
        for (Dish d : lowCaloricDishs) {
            lowCaloricDishName.add(d.getName());
            log.info("名称:{}, 热量:{}", d.getName(), d.getCalories());
        }
        return lowCaloricDishName;
    }

    /**
     * 使用Java8
     *
     * @param menu
     * @return
     */
    public static List<String> getLowCaloricDishsNameInJava8(List<Dish> menu) {
        List<String> lowCaloricDishName = menu.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
        return lowCaloricDishName;
    }

    /**
     * 多核架构并行执行
     * @param menu
     * @return
     */
    public static List<String> getLowCaloricDishsNameInJava8_paral(List<Dish> menu) {
        List<String> lowCaloricDishName = menu.parallelStream()
                .filter(d -> d.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
        return lowCaloricDishName;
    }
}
