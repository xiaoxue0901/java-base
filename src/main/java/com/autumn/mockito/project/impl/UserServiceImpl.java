package com.autumn.mockito.project.impl;

import com.autumn.mockito.project.UserService;
import org.springframework.stereotype.Service;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/21
 * @time 17:30
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public String getUserName() {
        return "默默";
    }
}
