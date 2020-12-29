package com.autumn.demo.java8.chap6;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author xql132@zcsmart.com
 * @date 2019/12/30 11:28
 * @description
 */
public class ToListCollector<T, U, R> {
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    public BiConsumer<List<T>, T> accumulator() {
        return (list, item) -> list.add(item);
    }

    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

//    public Set<Collector.Characteristics> characteristics() {
//        return  Collections.unmodifiableSet(EnumSet.of(
//                IDENTITY_FINISH, CONCURRENT));
//    }
}
