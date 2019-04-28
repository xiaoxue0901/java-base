package com.autumn.springdemo.service_retry;

/**
 * @author xql132@zcsmart.com
 * @date 2019/4/3 18:47
 * @description
 */
public interface RetryService {

    /*重试方法1: 无返回值*/
    void call(String result) throws NullPointerException;

    /*重试方法2: 有返回值*/
    String retry(String b);

    /*兜底方法*/
    String recover(RetryException e, String b);
}
