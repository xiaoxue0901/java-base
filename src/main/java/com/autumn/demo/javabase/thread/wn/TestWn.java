package com.autumn.demo.javabase.thread.wn;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/1
 * @time 10:22 下午
 * @description 测试wait, notify/notifyAll
 */
public class TestWn {

    private static Express express = new Express(0, Express.CITY);

    private static class CheckKm extends Thread {
        @Override
        public void run() {
            express.waitKm();
        }
    }

    private static class CheckSite extends Thread {
        @Override
        public void run() {
            express.waitSite();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i< 3; i++) {
            new CheckSite().start();
        }

        for (int i = 0; i< 3; i++) {
            new CheckKm().start();
        }

        Thread.sleep(1000);
        express.changeKm();
    }

}
