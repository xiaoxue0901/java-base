package com.autumn.demo.java8.chap5;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;

/**
 * @author xql132@zcsmart.com
 * @date 2019/12/27 10:36
 * @description
 */
public class PuttinIntoPractice {


    public static void main(String[] args) {
        // 4个交易员
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        // 交易员的交易信息
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
        transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .forEach(e -> System.out.println(JSON.toJSONString(e)));


        // Query 2: What are all the unique cities where the traders work?
        transactions.stream()
                .map(e -> e.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);


        // Query 3: Find all traders from Cambridge and sort them by name.
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(e -> e.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .forEach(System.out::println);

        // Query 4: Return a string of all traders’ names sorted alphabetically.
        String nameStr = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                // 按字母排序
                .sorted()
                .reduce("", (a, b) -> a.concat(b));
        System.out.println(nameStr);
        String nameStr2 = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                // 按字母排序
                .sorted()
                .collect(joining());
        System.out.println(nameStr2);

        // Query 5: Are there any trader based in Milan?
        transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(e -> e.getCity().equals("Milan"))
                .forEach(System.out::println);

        boolean milanBased =transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.println(milanBased);

        // Query 6: Update all transactions so that the traders from Milan are set to Cambridge.
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);


        // Query 7: What's the highest value in all the transactions?
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        // 8. 交易额最小的交易
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);

        transactions.stream()
                .map(Transaction::getValue)
                .reduce((t1, t2) -> t1 > t2? t1: t2);
        Optional<Transaction> minValue = transactions.stream()
                .min(comparing(Transaction::getValue));
    }




}
