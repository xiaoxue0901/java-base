package com.autumn.springdemo.restTemplate.core;

import com.alibaba.fastjson.JSON;
import com.autumn.springdemo.restTemplate.wx.AccessToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/7
 * @time 17:40
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HttpUtilTest {
    @Autowired
    HttpUtil httpUtil;

    @Test
    public void getAccessToken() {
        //wx7086d3fb459dbb09
        //{"errcode":40013,"errmsg":"invalid appid rid: 5fd32312-2ca2deab-22e7ab03"}
        String accessToken = httpUtil.getAccessToken("wx7086d3fb459dbb09", "fe14a5b4857e583b443917eba3fc8d4e");
        log.info("accesToken:{}", accessToken);
    }

    @Test
    public void getWxTicket() {
    }

    @Test
    public void getWxSignature() {
        httpUtil.getWxSignature("https://mallwebapp.zcsweb.vpos.xin/demo.html", "wxf71ffb21c797705b", "c1a793c80eccc53ff4c5188dfa702d47");
    }

    @Test
    public void doGet2() {
        httpUtil.doGet2();
    }

    @Test
    public void gsonConvert() {
        String json = "{\"access_token\":\"40_eIivqpicg9VYeuYycBGeq3SofKwRwCG8X7LIe4hOOsoMpQycS_R3zq8gEl1We4HUDJhAB2AkAgeLIkeUPxMPxNRzVqO4GcvT5wGXRaLu8F_qo6hZyHtLq8HHZR0DEA-yAOwFXCGGRbLXn1MhODCdABAVKJ\",\"expires_in\":7200}";
        AccessToken token = JSON.parseObject(json, AccessToken.class);
        log.info("响应:{}, ::::{}", token.getAccessToken(), token.getExpiresIn());
    }
}