package com.autumn.demo.javabase.yb.way1;

/**
 * @author xql132@zcsmart.com
 * @date 2018/9/21 11:40
 * @description
 */
public class Test {
    public static void main(String[] args) {
        // 调用异步任务
        new AsynTask().task(new MyCallBack() {
            // 实现回调方法
            @Override
            public void callBack(Object object) {
                System.out.println("异步回调处理: 值"+object);
            }
        });

        System.out.println("主线程等待异步输出");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
