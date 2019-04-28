package com.autumn.demo.javabase.ybtranstb;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author xql132@zcsmart.com
 * @date 2019/4/18 15:01
 * @description
 */
@Data
@Slf4j
public class MessageReceivedFuture<RESPONSE> {
    // 成员变量: 监听器
    private NotifyListener notifyListener;
    // 成员变量: 用于等待done或cancel, 设置闭锁要等待的线程数量为1
    private final CountDownLatch latch = new CountDownLatch(1);
    // 成员变量: 处理中
    private AtomicBoolean isProcessed = new AtomicBoolean(false);
    // 成员变量: 是否结束
    private boolean isDone = false;
    // 成员变量: 是否取消
    private boolean isCancel = false;
    // 成员变量:
    private volatile RESPONSE msg = null;
    // 成员变量
    private Throwable cause = null;


    /***********以下是成员方法********************************/
    /**
     * 获取是否结束结果: true: 结束; false: 未结束
     * @return
     */
    public boolean isDone() { return isDone;}

    /**
     * 获取是否正在处理: true-处理中;false-处理完毕
     * @return
     */
    public AtomicBoolean getIsProcessed() {return isProcessed;}

    /**
     * 获取是否已经取消
     * @return
     */
    public boolean isCancel() {return isCancel;}


    /**
     * 通过捕获异常的方式取消任务
     * 1. 结束进程
     * 2. 让等待的线程恢复执行任务
     * @param cause
     */
    public void cancel(Throwable cause) {
        // getAndSet()获取isProcessed之前的值, 设置isProcessed为true.
        // 如果isProcessed之前的值为true, 则返回; false: 继续下面的操作, 并把isProcessed设置为true.
        if (isProcessed.getAndSet(true)) {
            return;
        }
        // 设置为取消
        isCancel = true;
        this.cause = cause;
        // 构造函数中初始haul的count值减1, count=0, 主线程可以通过await()恢复自己的任务.
        latch.countDown();
    }

    /**
     * 正常终止任务
     * @param msg
     */
    public void done(RESPONSE msg) {
        //isProcessed为true, 返回; isProcessed为false, 设置为true后继续执行
        if (isProcessed.getAndSet(true)) {
            return;
        }
        // 设置响应报文
        this.msg = msg;
        // 设置为结束
        isDone = true;
        // 主线程可以通过await()恢复自己的任务
        latch.countDown();
    }

    /**
     * 获取处理完成后的响应结果. 如果消息未处理完成则会一直等待
     * @return
     * @throws TimeoutException
     */
    public RESPONSE get() throws TimeoutException {
        try {
            // 同步等待另一个线程执行结果. 如果count=0, 则恢复任务.
            latch.await();
        } catch (InterruptedException e) {
            log.error("异常信息:", e);
            cause = e;
        }

        if (null != msg) {
            return msg;
        } else {
            throw new TimeoutException("messageReceiveFuture timeout");
        }
    }

    /**
     * 在指定时间内获取处理完成的消息, 超时后不继续等待.
     * @param time
     * @param unit
     * @return
     * @throws TimeoutException
     */
    public RESPONSE get(int time, TimeUnit unit) throws TimeoutException {
        try {
            // 同步等待另一个线程执行结果. 如果count=0, 则恢复任务; 或者等待指定的时间time, 一样返回
            latch.await(time, unit);
        } catch (InterruptedException e) {
            log.error("异常信息:", e);
            cause = e;
        }

        if (null != msg) {
            return msg;
        } else {
            throw new TimeoutException("messageReceiveFuture timeout");
        }
    }

}
