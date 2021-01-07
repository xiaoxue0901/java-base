package com.autumn.demo.javabase.inter.callback;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/30
 * @time 16:20
 * @description
 */
public class TaskTest {

    @Test
    public void task() {
        MyCallBack callBack = new MyCallBack() {
            @Override
            public void callBack(Object object) {
                System.out.println("特定操作---");
            }
        };
        // 实例任务
        Task task = new Task();
        // 执行任务
        task.task(callBack);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}