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
    @Async
    @Retryable(include = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(delay = 3000l, maxDelay = 60*1000l, multiplier = 2))
    public String retry() {
        log.info("测试retry");
        if (i == 3) {
            return i++ +"";
        }
        i++;
        log.info("目前的次数是:{}", i);
        log.info("执行的操作");
        if (i != 0) {
            RetryException retryException = RetryException.builder().code("9999").message("超时").build();
            throw retryException;
        }

        return "00";
    }

    @Override
    @Async
    @Recover
    public String recover(RetryException e) {
        log.info("异常:", e);
        return "99";
    }
}
