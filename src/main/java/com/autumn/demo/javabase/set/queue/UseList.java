package com.autumn.demo.javabase.set.queue;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/4
 * @time 11:11
 * @description List: 有序集合; LinkedList: 链表
 */
@Slf4j
public class UseList {

    /**
     * Iterator的使用
     */
    public static void useIterator() {
        List<String> staff = new LinkedList<>();
        // LinkedList.add():将对象添加到链表的尾部
        staff.add("Amy");
        staff.add("Bob");
        staff.add("Carl");
        // iterator(): 获取迭代器Iterator
        Iterator<String> iterator = staff.iterator();
        if (iterator.hasNext()) {
            // 读取第一个元素:Amy
            String first = iterator.next();
            // 读取第二个元素:Bob
            String second = iterator.next();
            // 读取Carl
            String third = iterator.next();
            // 删除Carl: 删除的是上一次调用next()返回的数据
            iterator.remove();
            log.info("first:{}, second:{}", first, second);
        }
        log.info("staff:{}", JSON.toJSONString(staff));
    }

    /**
     * ListIterator迭代器
     */
    public static void useListIterator() {
        List<String> staff = new LinkedList<>();
        // LinkedList.add():将对象添加到链表的尾部
        staff.add("Amy");
        staff.add("Bob");
        staff.add("Carl");
        // listIterator(): 获取迭代器ListIterator
        ListIterator<String> iterator = staff.listIterator();
        // iterator.add(): 添加的元素位置依赖于迭代器的位置
        iterator.add("Juliet0");
        // skip past first element
        String first = iterator.next();
        // 在Amy后面添加Juliet
        iterator.add("Juliet");
        iterator.add("Juliet2");
        // 取出Bob
        String second = iterator.next();
        log.info("first:{}, second:{}", first, second);
        log.info("staff:{}", JSON.toJSONString(staff));
        // 取出
        String third = iterator.next();
        // 用Carl2取代Carl: 用新值取代上一次next()获取的值
        iterator.set("Carl2");
        if (!iterator.hasNext()){
            // 在尾部添加元素
            iterator.add("last");
        }
        log.info("staff2:{}", JSON.toJSONString(staff));
        // 重新对staff迭代
        ListIterator<String> iterator2 = staff.listIterator();
        log.info("first:{}", iterator2.next());
        // 反向迭代列表
        if (iterator2.hasPrevious()) {
            // 返回前一个对象: last
            String pre = iterator.previous();
            log.info("index:{}, pre:{}",iterator.previousIndex(), pre);
        }
        log.info("staff3:{}", JSON.toJSONString(staff));
    }



}
