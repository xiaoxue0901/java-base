package com.autumn.demo.javabase.java8.chap2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xql132@zcsmart.com
 * @date 2019/12/18 13:07
 * @description
 */
public class FilteringApples {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(153, "green"), new Apple(90, "red"), new Apple(160, "red"));
        // 筛选绿苹果
        List<Apple> greenApple = filterGreenApple(inventory);
        System.out.println(greenApple);

        // 提取出颜色, 筛选某种颜色的苹果
        List<Apple> readApples = filterAppleByColor(inventory, "red");
        System.out.println(readApples);

        //筛选超过多少重量的苹果
        List<Apple> heavyApples = filterAppleByWeight(inventory, 90);
        System.out.println(heavyApples);

        // 将能想到的每个属性做筛选: 代码笨拙且含义不清
        List<Apple> mutiApples = filterApples(inventory, "red", 150, false);
        System.out.println(mutiApples);

        //挑选红色并重的苹果
        List<Apple> redAndHeavyApple = filterApple(inventory, new AppleRedAndHeavyPredicate());

    }

    /**
     * 需求1: 筛选绿苹果
     * @param inventory
     * @return
     */
    public static List<Apple> filterGreenApple(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 升级版: 提取出颜色, 筛选某种颜色的苹果
     * @param inventory
     * @param color
     * @return
     */
    public static List<Apple> filterAppleByColor(List<Apple> inventory, String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (color.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 需求2: 筛选超过多少重量的苹果
     * @param inventory
     * @param weight
     * @return
     */
    public static List<Apple> filterAppleByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }


    /**
     * 将能想到的每个属性做筛选.
     * @param inventory
     * @param color
     * @param weight
     * @param flag
     * @return
     */
    public static List<Apple> filterApples(List<Apple> inventory,String color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ((flag && apple.getColor().equals(color)) || (!flag && apple.getWeight() > weight)){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 根据抽象条件筛选
     * 1. filterApple方法接收ApplePredicate对象, 对Apple做条件测试. 这就是行为参数化.
     * 2.行为参数化: 让方法接收多种行为(或战略)作为参数, 并在内部使用, 来完成不同的行为
     * 3. filterApple方法的行为取决于ApplePredicate对象传递的代码.
     * @param inventory
     * @param p
     * @return
     */
    public static List<Apple> filterApple(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 定义接口对选择标准建模 --> 定义一族算法: 称为"策略"
     *
     */
    public interface ApplePredicate {
        /**
         * 选择标准
         * @param apple
         * @return
         */
        boolean test(Apple apple);
    }

    /**
     * 策略: 挑选超重的苹果
     */
    static class AppleHeavyWeightPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    /**
     * 策略: 挑选绿色的苹果
     */
    static class AppleGreenWeightPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return "green".equals(apple.getColor());
        }
    }

    /**
     * 策略: 挑选红色并重的苹果
     */
    static class AppleRedAndHeavyPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return "red".equals(apple.getColor()) && apple.getWeight()> 150;
        }
    }

    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color){
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "weight=" + weight +
                    ", color='" + color + '\'' +
                    '}';
        }
    }
}
