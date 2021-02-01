package com.autumn.demo.javabase.thread.wn;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/1
 * @time 10:12 下午
 * @description
 */
@Data
@Slf4j
public class Express {
    public static final String CITY = "ShangHai";
    private int km;
    private String site;

    public Express() {
    }

    public Express(int km, String site) {
        this.km = km;
        this.site = site;
    }

    /**
     * 变化公里数, 然后通知处于wait()状态并需要处理公里数的线程进行业务处理
     */
    public synchronized void changeKm(){
        this.km=101;
        notifyAll();
    }

    /**
     * 变化城市, 然后通知处于wait()状态并需要处理地点的线程进行业务处理
     */
    public synchronized void changeSite() {
        this.site = "BeiJing";
        notifyAll();
    }

    public synchronized void waitKm(){
        while (this.km <=100) {
            try {
                wait();
                log.info("check km thread : {} is be notifed ", Thread.currentThread().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("the km is:{}, I will change db", this.km);
    }

    public synchronized void waitSite() {
        while (CITY.equals(this.site)) {
            try {
                wait();
                log.info("check site thread : {} is be notifed ", Thread.currentThread().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("the site is:{}, I will change db", this.site);
    }
}
