package com.autumn.springdemo.restTemplate.core;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

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
        String accessToken = httpUtil.getAccessToken("wx7086d3fb459dbb09", "fe14a5b4857e583b443917eba3fc8d4e");
        log.info("accesToken:{}", accessToken);
    }

    @Test
    public void getWxTicket() {
    }

    @Test
    public void getWxSignature() {
        httpUtil.getWxSignature("http://xihiia.natappfree.cc/wxsdk/demo.html", "wx7086d3fb459dbb09", "fe14a5b4857e583b443917eba3fc8d4e");
    }

    @Test
    public void doGet2() {
        httpUtil.doGet2();
    }
}