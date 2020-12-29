package com.autumn.demo.java8.chap2;

import java.util.Arrays;
import java.util.List;

/**
 * @author xql132@zcsmart.com
 * @date 2019/12/18 15:46
 * @description
 */
public class PrettyPrintApple {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(153, "green"), new Apple(90, "red"), new Apple(160, "red"));
        prettyPrintApple(inventory, new AppleFancyFormatter());
        prettyPrintApple(inventory, new AppleSimpleFormatter());
    }

    /**
     * 打印苹果方法
     * @param inventory
     * @param appleFormat
     */
    public static void prettyPrintApple(List<Apple> inventory, AppleFormat appleFormat) {
        for (Apple apple : inventory) {
            System.out.println(appleFormat.print(apple));
        }
    }

    /**
     * 定义接口: 对打印方式建模
     */
    interface AppleFormat {
        String print(Apple apple);
    }

    /**
     * 打印方式一
     */
    static class AppleFancyFormatter implements AppleFormat {
        @Override
        public String print(Apple apple) {
            String characteristic = apple.getWeight() > 150 ? "heavy" :
                    "light";
            return "A " + characteristic +
                    " " + apple.getColor() +" apple";
        }
    }

    /**
     * 打印方式一
     */
    static class AppleSimpleFormatter implements AppleFormat {
        @Override
        public String print(Apple apple) {
            return "An apple of " + apple.getWeight() + "g";
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
