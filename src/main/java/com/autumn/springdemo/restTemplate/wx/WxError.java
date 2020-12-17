package com.autumn.springdemo.restTemplate.wx;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/11
 * @time 15:38
 * @description 微信响应信息
 */
@Setter
@Getter
public class WxError {
    int errcode;
    String errmsg;
}
