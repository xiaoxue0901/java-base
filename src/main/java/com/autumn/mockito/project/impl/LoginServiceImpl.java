package com.autumn.mockito.project.impl;

import com.autumn.mockito.project.LoginService;
import com.autumn.mockito.project.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/21
 * @time 17:30
 * @description
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserService userService;

    @Override
    public String login() {
        String userName = userService.getUserName();
        log.info("{}登录成功", userName);
        return userName;
    }

     public LoginServiceImpl() {
     }

     /**
      * @param userService
      * @Autowired 改为构造器注入
      */
     public LoginServiceImpl(UserService userService) {
         this.userService = userService;
     }

     /**
      * @param userService
      * @Autowired 改为Setter注入
      */
     public void setUserService(UserService userService) {
         this.userService = userService;
     }

}
