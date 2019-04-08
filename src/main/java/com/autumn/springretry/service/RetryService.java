package com.autumn.springretry.service;

/**
 * @author xql132@zcsmart.com
 * @date 2019/4/3 18:47
 * @description
 */
public interface RetryService {

    void call(String result) throws NullPointerException;

    String retry(String b);

    String recover(RetryException e, String b);
}
