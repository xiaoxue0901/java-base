package com.autumn.demo.javabase.thread.condition;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/1
 * @time 10:12 下午
 * @description
 */
@Data
@Slf4j
public class ExpressCond {
    public static final String CITY = "ShangHai";
    private int km;
    private String site;

    private Lock kmLock = new ReentrantLock();
    private Lock siteLock = new ReentrantLock();

    private Condition kmCond = kmLock.newCondition();
    private Condition siteCond = siteLock.newCondition();
    public ExpressCond() {
    }

    public ExpressCond(int km, String site) {
        this.km = km;
        this.site = site;
    }

    /**
     * 变化公里数, 然后通知处于wait()状态并需要处理公里数的线程进行业务处理
     */
    public  void changeKm(){
        kmLock.lock();
        try {
            this.km = 101;
            kmCond.signal();
        } finally {
            kmLock.unlock();
        }
    }

    /**
     * 变化城市, 然后通知处于wait()状态并需要处理地点的线程进行业务处理
     */
    public  void changeSite() {
        siteLock.lock();
        try {
            this.site = "BeiJing";
            siteCond.signal();
        }finally {
            siteLock.unlock();
        }
    }

    public  void waitKm(){
        kmLock.lock();
        try {
            while (this.km <=100) {
                try {
                    kmCond.await();
                    log.info("check km thread : {} is be notifed ", Thread.currentThread().getId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("the km is:{}, I will change db", this.km);
        } finally {
            kmLock.unlock();
        }

    }

    public  void waitSite() {
        siteLock.lock();
        try {
            while (CITY.equals(this.site)) {
                try {
                    siteCond.await();
                    log.info("check site thread : {} is be notifed ", Thread.currentThread().getId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                }
            }
            log.info("the site is:{}, I will change db", this.site);
        } finally {
            siteLock.unlock();
        }

    }
}
