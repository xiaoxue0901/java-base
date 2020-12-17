package com.autumn.demo.javabase.java8.chap4;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xql132@zcsmart.com
 * @date 2019/12/25 16:18
 * @description
 */
public class StreamUse {

    public static List<String> getHighCaloricDishsNameInJava8(List<Dish> menu) {
        List<String> lowCaloricDishName = menu.stream()
                .filter(d -> d.getCalories() >300)
                .map(Dish::getName)
                .distinct()
                .limit(3)
                .collect(Collectors.toList());
        return lowCaloricDishName;
    }
}
