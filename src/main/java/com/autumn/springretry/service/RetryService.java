package com.autumn.springretry.service;

/**
 * @author xql132@zcsmart.com
 * @date 2019/4/3 18:47
 * @description
 */
public interface RetryService {

    String retry();

    String recover(RetryException e);
}
