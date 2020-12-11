package com.autumn.springdemo.restTemplate.wx;

import com.alibaba.fastjson.annotation.JSONField;
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
public class AccessToken extends WxError {
    @JSONField(name = "access_token")
    private String accessToken;
    @JSONField(name = "expires_in")
    private int expiresIn;
}
