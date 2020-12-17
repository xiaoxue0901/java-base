package com.autumn.demo.javabase.java8.chap6;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

/**
 * @author xql132@zcsmart.com
 * @date 2019/12/30 10:43
 * @description
 */
public class PartitionPrimeNumbers {
    public static void main(String[] args) {
        System.out.println("Numbers partitioned in prime and non-prime: " + partitionPrimes(100));
        System.out.println("Numbers partitioned in prime and non-prime: " + partitionPrimesWithCustomCollector(100));
    }

    public static boolean isPrime(int condition) {
        return IntStream.rangeClosed(2, condition - 1)
                .limit((long) Math.floor(Math.sqrt((double)condition)) -1)
                .noneMatch(i -> condition % i ==0);
    }

    /**
     * 求质数1
     * @param n
     * @return
     */
    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
       return IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(PartitionPrimeNumbers::isPrime));
    }

    /**
     * 求质数2
     * @param n
     * @return
     */
    public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumbersCollector());
    }

    public static boolean isPrime2(List<Integer> primes, Integer candidate) {
        double candidateRoot = Math.sqrt((double) candidate);
        return takeWhile(primes, i -> i <= candidateRoot).stream().noneMatch(i -> candidate % i == 0);
    }

    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A iterm: list) {
            if (!p.test(iterm)) {
                return list.subList(0, i);
            }
            i ++;
        }
        return list;
    }

   public static class PrimeNumbersCollector implements Collector<Integer, Map<Boolean,List<Integer>>, Map<Boolean,List<Integer>>> {
        @Override
        public Supplier<Map<Boolean, List<Integer>>> supplier() {
            return ()->new HashMap<Boolean, List<Integer>>(){{
                put(true, new ArrayList<Integer>());
                put(false, new ArrayList<Integer>());}
            };
        }

        @Override
        public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer canditate) -> {
            acc.get(isPrime2(acc.get(true), canditate)).add(canditate);
        };
        }

        @Override
        public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
            return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
                map1.get(true).addAll(map2.get(true));
                map1.get(false).addAll(map2.get(false));
                return map1;
            } ;
        }

        @Override
        public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
        }
    }
}
