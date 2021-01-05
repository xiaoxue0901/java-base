package com.autumn.demo.javabase.set.queue;

import java.util.*;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/5
 * @time 17:30
 * @description
 */
public class UseQueue {

    public static void useQueue() {
        Queue queue = new LinkedList();
        // 添加元素
        queue.add("A");
        queue.offer("B");
        // 删除元素
        queue.remove();
        queue.poll();
        // 返回队列头部元素
        queue.element();
        queue.peek();

        // 创建双端队列
        Deque deque = new ArrayDeque(16);
        // 添加元素到双端队列的头部或尾部
        deque.addFirst("A");
        deque.addLast("Z");
        deque.offerFirst("B");
        deque.offerLast("Y");
        // 删除并返回队列头部的元素
        deque.removeFirst();
        deque.removeLast();
        deque.pollFirst();
        deque.pollLast();
        // 返回队列头部的元素
        deque.getFirst();
        deque.getLast();
        deque.peekFirst();
        deque.peekLast();

        // 优先级队列: 任务调度
        PriorityQueue priorityQueue = new PriorityQueue(8);
        // 总是删除剩余元素中优先级最小的那个元素
        priorityQueue.remove();
    }
}
