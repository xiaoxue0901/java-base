package com.autumn.demo.javabase.set.queue;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/5
 * @time 14:25
 * @description LinkedList: 采用链表实现
 */
@Slf4j
public class UseLinkedList {

    public static void useList() {
        // List使用
        List<String> source = new LinkedList<>();
        source.add("嬴政");
        source.add("刘邦");
        List<String> pre = new LinkedList<>();
        pre.add("尧");
        pre.add("舜");
        pre.add("禹");
        source.addAll(pre);
        source.add(0, "周文王");
        ListIterator<String> iter = source.listIterator();
        while (iter.hasNext()) {
            // nextIndex(): 下次调用next()将返回的元素的索引.
            log.info("索引:{}, 值:{}",iter.nextIndex(), iter.next());
        }
        // 删除"周文王", 并返回要删除的值
        String name = source.remove(0);
        // 获取"嬴政"
        String yinz = source.get(0);
        // 设置值, 将刘邦换为项羽, 返回刘邦
        String liub = source.set(1, "项羽");
        log.info("remove:{}, get:{}, set:{}", name, yinz, liub);
        // 返回尧在列表中第一次出现的位置.
        int yaoIndex = source.indexOf("尧");
        // 返回尧在列表中最后一次出现的位置.
        int yaoLastIndex = source.lastIndexOf("尧");
        log.info("index:{}, lastIndex:{}", yaoIndex, yaoLastIndex);

        // LinkedList使用
        // 构造一个链表, 并将source集合中的元素添加到这个表中
        LinkedList<String> linkedList = new LinkedList<>(source);
        // 添加
        linkedList.addFirst("夏商");
        linkedList.addLast("清");
        // 获取
        log.info("第一个:{}, 最后一个:{}", linkedList.getFirst(), linkedList.getLast());
        // 删除
        linkedList.removeFirst();
        linkedList.removeLast();
        log.info("linkedList:{}", JSON.toJSONString(linkedList));
    }
}
