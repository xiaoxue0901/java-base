package com.autumn.demo.javabase.dthread.demo1;

/**
 * @author xql132@zcsmart.com
 * @date 2019/1/31 17:12
 * @description 实例变量与线程安全
 */
public class Demo2SafeThread {
    public static void main(String[] args) {
        // 2. 共享实例变量
        System.out.println("==============2. 共享实例变量===============");
        ShareCountNum shareCountNum = new ShareCountNum();
        Thread sa = new Thread(shareCountNum, "sa");
        Thread sb = new Thread(shareCountNum, "sb");
        Thread sc = new Thread(shareCountNum, "sc");
        sa.start();
        sb.start();
        sc.start();

        System.out.println("==============1. 不共享实例变量===============");
        // 1. 不共享实例变量
        CountNum a = new CountNum("a");
        CountNum b = new CountNum("b");
        CountNum c = new CountNum("c");
        a.start();
        b.start();
        c.start();

    }

}

/**
 * 1. 不共享实例变量
 */
class CountNum extends Thread {
    private int count = 5;
    public CountNum(String name) {
        this.setName(name);
    }

    @Override
    public void run() {
        super.run();
        while (count > 0) {
            count--;
            System.out.println("由" + this.currentThread().getName() + "计算, count = " + count);
        }
    }
}

/**
 * 2. 共享实例变量
 */
class ShareCountNum extends Thread{
    private int count =5;

    @Override
    public void run() {
        // 不加锁
//        while (count > 0) {
//            count--;
//            System.out.println("由" + this.currentThread().getName() + "计算, count = " + count);
//        }

        // 加锁
        getCount();
    }

    /**
     * 加synchronized处理非线程安全问题
     * 用上面的方法测试, sa一直把持着锁.
     * @return
     */
    public synchronized int getCount() {
        while (count > 0) {
            count--;
            System.out.println("由" + this.currentThread().getName() + "计算, count = " + count);
        }
        return count;
    }

    public void get2Count() {
        // count--是执行完毕后由system.out.println打印的
        System.out.println("count值为:"+count--);
    }
}




