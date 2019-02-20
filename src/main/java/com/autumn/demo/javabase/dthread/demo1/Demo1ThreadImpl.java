package com.autumn.demo.javabase.dthread.demo1;

/**
 * @author xql132@zcsmart.com
 * @date 2019/1/31 11:35
 * @description 实现多线程编程的2种方式：1. 继承Thread类, 2. 实现Runnable接口。
 */
public class Demo1ThreadImpl extends Thread {
    @Override
    public void run() {
        System.out.println("child thread 实现多线程编程的方式1: 继承Thread类");

        // 线程的随机性
        for (int i = 0; i < 10; i++) {
            int time = (int) (Math.random() * 1000);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("child thread :: " + Thread.currentThread().getName());
        }
    }
}

class Demo2RunnableImpl implements Runnable {
    @Override
    public void run() {
        System.out.println("实现多线程编程的方式2: 实现Runnable接口");
    }
}



class TestThread {
    public static void main(String[] args) {
        // 方式1
        Demo1ThreadImpl thread = new Demo1ThreadImpl();
        thread.start();
        System.out.println("main线程运行结束1");

        // 方式2
        Runnable threadImpl = new Demo2RunnableImpl();
//        threadImpl.run(); // 如果只调用run方法,不调用start. 说明是由main主线程来调用run方法,必须等run中的代码执行完毕才能执行下面的代码
        System.out.println("main线程运行结束2");

        // 方式2
        Thread thread1 = new Thread(threadImpl, "runnableThread");
        thread1.start(); //此线程对象交给"线程规划器"来处理.具有异步执行的效果.

    }
}

class TestThreadRandom {
    public static void main(String[] args) {
        Demo1ThreadImpl thread = new Demo1ThreadImpl();
        // 验证线程的随机性
        thread.setName("extendThread");
//        thread.run(); // 如果只调用run方法,不调用start. 说明是由main主线程来调用run方法,必须等run中的代码执行完毕才能执行下面的代码
        thread.start(); // 此线程对象交给"线程规划器"来处理.具有异步执行的效果.
        for (int i = 0; i < 10; i++) {
            int time = (int) (Math.random() * 1000);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main thread :: " + Thread.currentThread().getName());
        }
    }

}
