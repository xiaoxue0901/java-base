package com.autumn.demo.javabase.java8.chap6;

import com.autumn.demo.javabase.java8.chap4.Dish;

import java.util.*;

import static java.util.stream.Collectors.*;

/**
 * @author xql132@zcsmart.com
 * @date 2019/12/30 10:19
 * @description
 */
public class Partitioning {
    public static void main(String[] args) {
        System.out.println("Dishes partitioned by vegetarian: " + partitioningByVegeterian());
        System.out.println("Vegetarian Dishes by type: " + vegetarianDishByType());
        System.out.println("Most caloric dishes by vegetarian: " + mostCaloricPartitionByVegetation());
    }

    private static Map<Boolean, List<Dish>> partitioningByVegeterian() {
        return Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian));
    }

    private static Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishByType() {
        return Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
    }

    private static Object mostCaloricPartitionByVegetation() {
        return Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian, collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
    }


}
