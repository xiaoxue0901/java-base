package com.autumn.demo.lambda.stream;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author xql132@zcsmart.com
 * @date 2019/1/21 16:19
 * @description 创建流的方式
 */
public class DemoStream {

    public static void main(String[] args) {
//        generateArray();
//        generateList();
//        generate();
//        iterater();
        genStream();
    }

    /**
     * 数组转换为stream
     */
    public static void generateArray() {
        String[] array = {"a", "b", "c"};
        Stream<String> stringStream = Stream.of(array);
        stringStream.forEach((x)-> System.out.println(x));

    }

    /**
     * 集合转换为stream
     */
    public static void generateList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        Stream<String> listStream = list.stream();
        listStream.forEach((x)-> System.out.println(x));
    }

    /**
     * Stream.generate
     */
    public static void generate() {
        Stream<Integer> stream = Stream.generate(() -> 1);

        stream.limit(10).forEach((x)-> System.out.println(x));
    }

    /**
     * stream.iterater产生stream
     */
    public static void iterater() {
        Stream<String> stream = Stream.iterate("hello", (a) -> a+"b");
        stream.limit(5).forEach((x)-> System.out.println(x));

    }

    /**
     * 其他方式产生stream
     */
    public static void genStream() {
        String str = "abcdefgh";
        IntStream stream = str.chars();
        stream.limit(3).forEach((x)-> System.out.println(x));
    }


    public static void test() {
        // paraller(): 并行流. sequential:
        Optional<Integer> max = Stream.iterate(1, x->x+1).limit(200).peek(x->{
            System.out.println("");
        }).parallel().sequential().max(Integer::compare);
    }

}
