 package com.autumn.mockito.project;

 import com.autumn.demo.javabase.JavaBaseApplication;
 import com.autumn.mockito.project.impl.LoginServiceImpl;
 import com.autumn.mockito.project.impl.UserServiceImpl;
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.mockito.Mockito;
 import org.springframework.boot.test.context.SpringBootTest;
 import org.springframework.test.context.junit4.SpringRunner;
 import org.springframework.test.util.ReflectionTestUtils;

 import static org.mockito.Mockito.mock;

 /**
  * @author xql132@zcsmart.com
  * @date 2021/1/21
  * @time 17:42
  * @description Mockito测试: 2. 绕过限制
  *
  */
 @SpringBootTest(classes = {JavaBaseApplication.class})
 @RunWith(SpringRunner.class)
 public class LoginService2Test {


     /**
      * 绕过限制
      * 1. 不用去掉Autowired (去掉也没有影响)
      * 2. 不用生成构造器或者setter注入
      */
     @Test
     public void login() {
         UserService userService = mock(UserServiceImpl.class);
         Mockito.when(userService.getUserName()).thenReturn("青青");
         LoginService loginService = new LoginServiceImpl();
         // 目标类, 字段名, 要注入的对象
         ReflectionTestUtils.setField(loginService, "userService", userService);
         loginService.login();
     }

 }