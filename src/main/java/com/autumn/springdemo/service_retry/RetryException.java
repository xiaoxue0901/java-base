package com.autumn.springdemo.service_retry;

import lombok.Builder;
import lombok.Getter;

/**
 * @author xql132@zcsmart.com
 * @date 2019/4/3 18:43
 * @description 异常类
 */
@Builder
@Getter
public class RetryException extends RuntimeException {
    private String code;
    private String message;
    private String obj;
}
