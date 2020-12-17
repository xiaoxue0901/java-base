package com.autumn.springdemo.restTemplate.wx;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/7
 * @time 17:04
 * @description
 */
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class WxResp extends WxError {

    String ticket;
    int expiresIn;
}
