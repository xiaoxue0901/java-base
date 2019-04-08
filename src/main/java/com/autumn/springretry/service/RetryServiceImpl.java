package com.autumn.springretry.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author xql132@zcsmart.com
 * @date 2019/4/3 18:47
 * @description
 */
@Service
@Slf4j
public class RetryServiceImpl implements RetryService {
    private int i = 0;

    @Override
    @Retryable(include = {RetryException.class},
            backoff = @Backoff(delayExpression = "#{${max.delay}}", maxDelay = 1000l, multiplierExpression = "#{${integerFiveBean}}"))
    public void call(String result) throws NullPointerException {
        log.info("============:{}", result);
        while (true) {
            RetryException retryException = RetryException.builder().code("9999").message("超时").build();
            throw retryException;
        }
    }

    @Override
    @Async
    @Retryable(include = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(delay = 1000l, maxDelay = 10*1000l, multiplier = 2))
    public String retry(String b) {
        log.info("测试retry====={}", b);
        if (i == 3) {
            return i++ +"";
        }
        i++;
        log.info("目前的次数是:{}", i);
        log.info("执行的操作");
        if (i != 0) {
            RetryException retryException = RetryException.builder().code("9999").message("超时").obj(b).build();
            throw retryException;
        }

        return "00";
    }

    @Override
    @Recover
    public String recover(RetryException e, String b) {
        log.error("要操作的内容为{}", e.getObj());
        log.info("异常:", e);
        log.info("**************************{}:", b);
        return "99";
    }
}
