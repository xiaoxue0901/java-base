 package com.autumn.mockito.project;

 import com.autumn.demo.javabase.JavaBaseApplication;
 import com.autumn.mockito.project.impl.LoginServiceImpl;
 import com.autumn.mockito.project.impl.UserServiceImpl;
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.mockito.Mockito;
 import org.springframework.boot.test.context.SpringBootTest;
 import org.springframework.test.context.junit4.SpringRunner;

 import static org.junit.Assert.*;
 import static org.mockito.Mockito.mock;

 /**
  * @author xql132@zcsmart.com
  * @date 2021/1/21
  * @time 17:42
  * @description Mockito测试: 1. 修改实现
  * 把字段注入(@Autowire private UserService userService;) 改为构造函数注入或者属性注入
  * https://www.jianshu.com/p/c68ee5d08fdd
  * https://cloud.tencent.com/developer/article/1516985
  */
 @SpringBootTest(classes = {JavaBaseApplication.class})
 @RunWith(SpringRunner.class)
 public class LoginServiceTest {

     /**
      * 构造方法注入
      */
     @Test
     public void login_construct() {
         UserService userService = mock(UserServiceImpl.class);
         Mockito.when(userService.getUserName()).thenReturn("lily");
         // 构造方法注入
         LoginService loginService = new LoginServiceImpl(userService);
         loginService.login();
     }

     /**
      * setter方法注入
      */
     @Test
     public void login_setter() {
         UserService userService = mock(UserServiceImpl.class);
         Mockito.when(userService.getUserName()).thenReturn("tom");
         // setter方法注入
         LoginServiceImpl loginService = new LoginServiceImpl();
         loginService.setUserService(userService);
         loginService.login();

     }
 }