package com.autumn.springdemo.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author xql132@zcsmart.com
 * @date 2019/4/28 20:43
 * @description
 */
@Service
public class ExeNoticeService {

    @Async
    public void exeAsyncTask() {
        System.out.println("执行异步任务");
    }
}
