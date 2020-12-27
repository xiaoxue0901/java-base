package com.autumn.demo.javabase.generic;

import com.alibaba.fastjson.JSON;
import com.autumn.demo.javabase.bean.Employee;
import com.autumn.demo.javabase.bean.FindUser;
import com.autumn.demo.javabase.bean.Head;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/25
 * @time 18:16
 * @description
 */
@Slf4j
public class SendUtilTest {
    private SendUtil sendUtil;

    @Before
    public void setUp() throws Exception {
        sendUtil = new SendUtil(new EmployeeFactory());
    }

    @Test
    public void send() {
        Head request = new Head();
        RespVO<Employee> respVO = sendUtil.send(request, Employee.class);
      log.info("respVo: {}", JSON.toJSONString(respVO));
    }

    @Test
    public void testSend() {
        Head head = (Head) sendUtil.send(Head.class);
        log.info("head: {}", head.hashCode());
    }

    @Test
    public void getReq() {
       FindUser user =  sendUtil.getReq(FindUser.class);
       log.info("user:{}", user.toString());
    }


}