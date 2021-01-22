package com.autumn.mockito.project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/22
 * @time 10:41
 * @description
 */
@RestController
@Slf4j
@RequestMapping("/web")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(@RequestParam String name) {
        log.info("登陆开始:{}", name);
        return userService.getUserName();
    }
}
