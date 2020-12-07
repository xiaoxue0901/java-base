package com.autumn.springdemo.restTemplate.core;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/7
 * @time 17:29
 * @description
 */
@Getter
@Setter
public class AccessToken {
    private String access_token;
    private int expires_in;
}
