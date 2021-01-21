package com.autumn.demo.javabase.thread;



import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/20
 * @time 11:13 下午
 * @description
 */
@Slf4j
public class OnlyMain {
    public static void main(String[] args) {
        // 虚拟机线程管理的接口
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 当前程序所有线程
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo info: threadInfos){
           log.info("{}, :::{}", info.getThreadId(), info.getThreadName());
        }

    }
}
