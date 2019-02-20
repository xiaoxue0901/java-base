package com.autumn.demo.javabase.dthread.demo1;

/**
 * @author xql132@zcsmart.com
 * @date 2019/1/31 17:58
 * @description 线程方法
 * 1. currentThread(): 返回代码段正在被哪个线程调用的信息.
 * 2. isAlive(): 判断当前的线程是否处于活动状态. 活动状态是:线程已经启动尚未终止. 线程处于正在运行或准备开始运行的状态, 认为线程是存活的.
 * 3. sleep(): 在指定的毫秒数内让当前"正在执行的线程"休眠(暂停执行). "正在执行的线程"指的是this.currentThread()返回的线程
 * 4. getId(): 取得线程的唯一标识.
 */
public class Demo3MethodThread {

    public static void main(String[] args) {
        System.out.println("===========测试getId()==================");
        Thread runThread = Thread.currentThread();
        System.out.println("runThread.getName() + " + runThread.getId());

        System.out.println("===========测试isAlive==================");
        ThreadMethod thread_isalive = new ThreadMethod();
        Thread thread = new Thread(thread_isalive);
        System.out.println("main begin thread isAlive ::"+ thread.isAlive());
        thread.setDaemon(true); // 标记此线程为守护线程
        thread.setName("A");
        thread.isDaemon(); // 测试当前线程是否是守护线程.
//        thread.start();
        thread.run();
        System.out.println("线程的唯一标识: " + thread.getId());
        System.out.println("main end thread isAlive ::"+ thread.isAlive());

//        System.out.println("===========测试currentThread()==================");
//        ThreadMethod thread_currentThread = new ThreadMethod();
//        System.out.println("begin=="+thread_currentThread.isAlive());
////        thread_currentThread.start();
//        thread_currentThread.run();
//        // 活动状态是:线程已经启动尚未终止. 线程处于正在运行或准备开始运行的状态, 认为线程是存活的.
//        System.out.println("end=="+thread_currentThread.isAlive());
    }


}

class ThreadMethod extends Thread {

    public ThreadMethod() {
        System.out.println("构造方法打印: "+ Thread.currentThread().getName()+"存活状态: "+Thread.currentThread().isAlive());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("构造方法this.getName(): "+ this.getName()+"存活状态: "+this.isAlive());
    }
    @Override
    public void run() {
        System.out.println("run方法的打印: "+ Thread.currentThread().getName()+"存活状态: "+Thread.currentThread().isAlive());
        System.out.println("run() this.getName(): "+ this.getName()+"存活状态: "+this.isAlive());
    }
}