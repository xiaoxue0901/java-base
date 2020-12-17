package com.autumn.demo.javabase.java8.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author xql132@zcsmart.com
 * @date 2019/12/19 20:38
 * @description
 */
public class Sorting {

    public static void main(String[] args) {
        // 1
        List<Apple> inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red")));

        // 传递代码: sort的行为被参数化: 传递给它的排序策略不同, 其行为也会不同.
        inventory.sort(new AppleComparator());

        // 使用匿名类
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        // 使用lambda表达式
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

        // 使用方法引用
        Comparator<Apple> comparator = Comparator.comparing(a -> a.getWeight());
        inventory.sort(Comparator.comparing(Apple::getWeight));

        // 逆序: reverse
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());
        // 比较器链: thenComparing
        inventory.sort(Comparator.comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));


        // 谓词
        Predicate<Apple> redApple = a -> "red".equals(a.getColor());

        Predicate<Apple> notRedApple = redApple.negate();

        Predicate<Apple> redAndHeavyApple = redApple.and(a -> a.getWeight() > 150);

        Predicate<Apple> greenOrLintApple = notRedApple.or(a -> a.getWeight() < 110);

        //复合函数
        Function<Integer, Integer> f = x -> x+1;
        Function<Integer, Integer> g = x -> x*2;
        Function<Integer, Integer> h1 = f.andThen(g);
        int x = 3;
        int result1 = h1.apply(x);

        Function<Integer, Integer> h2 = f.compose(g);
        int result2 = h2.apply(x);
        System.out.println("result1 = "+ result1);
        System.out.println("result2 = "+ result2);


    }

    public static class Apple {
        private Integer weight = 0;
        private String color = "";

        public Apple(Integer weight, String color) {
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

        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }

    static class AppleComparator implements Comparator<Apple> {
        @Override
        public int compare(Apple o1, Apple o2) {
            return o1.getWeight().compareTo(o2.getWeight());
        }
    }
}
