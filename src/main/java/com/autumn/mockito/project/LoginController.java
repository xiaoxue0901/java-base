package com.autumn.mockito.project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/22
 * @time 10:41
 * @description
 */
@Controller
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/web/login")
    public String login(String name) {
        log.info("登陆开始:{}", name);
        return userService.getUserName();
    }
}
