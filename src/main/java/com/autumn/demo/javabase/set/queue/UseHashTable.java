package com.autumn.demo.javabase.set.queue;

import com.alibaba.fastjson.JSON;
import com.autumn.demo.javabase.bean.Item;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/5
 * @time 14:39
 * @description 散列集: 散列表使用链表数组实现
 */
@Slf4j
public class UseHashTable {

    /**
     * HashSet: 无序集合
     */
    public static void useHashSet() {
        // 构造空散列表
        HashSet<String> hashSet = new HashSet<String>();
        hashSet.add("唐");
        hashSet.add("宋");
        hashSet.add("元");
        hashSet.add("明");
        // 构造一个散列集
        HashSet<String> set = new HashSet<>(hashSet);
        // 构造一个空的具有指定容量(桶数)的散列集
        HashSet initSet = new HashSet(8);
        // 构造一个具有指定容量和装填因子的空散列值
        HashSet initLoadSet = new HashSet(8, 0.75f);
    }

    public static void useTreeSet() {
        // TreeSet:有序集合.
        SortedSet<String> sorter = new TreeSet<>();
        // 以任意顺序插入到集合中
        sorter.add("Bob");
        sorter.add("Amy");
        sorter.add("Carl");
        // foreach: 语法糖, 底层调用iterator实现
        for (String s : sorter) {
            // 集合的值自动的按照排序后的顺序呈现: 排序用红黑树实现
            log.info("{}", s);
        }
        // 构造树集
        SortedSet<String> treeSet = new TreeSet<>(sorter);
    }

    /**
     * TreeSet自定义排序
     */
    public static void useTreeSetSort() {
        SortedSet<Item> parts = new TreeSet<>();
        parts.add(new Item("Toaster", 1234));
        parts.add(new Item("Widget", 4562));
        parts.add(new Item("Modem", 9945));
        // 自定义比较
        SortedSet<Item> sortByDesc = new TreeSet<>((o1,o2) ->{
            String despA = o1.getName();
            String despB = o2.getName();
            // return despA.compareTo(despB);
            return o1.compareTo(o2);
        });
        sortByDesc.addAll(parts);
        log.info("排序TreeSet:{}", JSON.toJSONString(sortByDesc));
        log.info("最小:{}", parts.first().getName());
        log.info("最打:{}", parts.last().getName());
        // 返回比较器
         Comparator comparator = sortByDesc.comparator();
         // NavigableSet
        TreeSet<Item> navigSet = new TreeSet<>(parts);
        Item lily = new Item("lily", 8888);
        // 大于lily的最小元素
       Item higher = navigSet.higher(lily);
       // 小于lily的最大元素
        Item lower = navigSet.lower(lily);
        // 大于等于lily的最小元素
        Item ceil = navigSet.ceiling(lily);
        // 小于于等于lily的最大元素
        Item floor = navigSet.floor(lily);
        // 删除并返回集合中最小元素
        Item max = navigSet.pollFirst();
        //删除返回集合中最大元素
        Item min = navigSet.pollLast();
        log.info("higher:{}, lower:{}, ceil:{}, floor:{}, max:{}, min:{}", higher.getName(), lower.getName(), ceil.getName(), floor.getName(), max.getName(), min.getName());
        // 递减迭代器
        Iterator<Item> iterator = navigSet.descendingIterator();
        while (iterator.hasNext()) {
            log.info("元素:{}", iterator.next().getName());
        }


    }
}
