package com.autumn.springdemo.restTemplate.wx;

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
public class WxResp {
    String errcode;
    String errmsg;
    String ticket;
    String expiresIn;
}
