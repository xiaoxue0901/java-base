package com.autumn.mockito.project;

import com.autumn.demo.javabase.JavaBaseApplication;
import com.autumn.mockito.project.impl.LoginServiceImpl;
import com.autumn.mockito.project.impl.UserServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/21
 * @time 17:42
 * @description Mockito测试: 4. 使用SpringBoot MockBean
 *
 */
@SpringBootTest(classes = {JavaBaseApplication.class})
@RunWith(SpringRunner.class)
public class LoginService4Test {

    /**
     * 对应于实现代码中的每个@Autowired字段，测试中可以用一个@Mock声明mock对象
     * ，并用@InjectMocks标示需要注入的对象。
     */
    @MockBean
    UserService userService;
    @Autowired
    LoginService loginService;

    /**
     * 绕过限制
     * 1. 不用去掉Autowired (去掉也没有影响)
     * 2. 不用生成构造器或者setter注入
     */
    @Test
    public void login() {
        Mockito.when(userService.getUserName()).thenReturn("晶晶");
        assertEquals("晶晶", loginService.login());
    }

}