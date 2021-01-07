package com.autumn.demo.javabase.inter.callback;

/**
 * @author xql132@zcsmart.com
 * @date 2018/9/21 11:35
 * @description 特定事件
 */
public class Task {

    public void task(MyCallBack myCallBack) {
        Thread d = new Thread(() -> {
            System.out.println("开始执行主任务");
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
                // 特定事件为sum=78
                if (sum == 78) {
                    // 特定事件发生后采取的动作
                    myCallBack.callBack(sum);
                    System.out.println("执行完回调接口的操作, 再执行主任务");
                }
            }

        });
        d.start();
    }
}
