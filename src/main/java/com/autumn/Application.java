package com.autumn;

import com.autumn.springretry.service.RetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;

/**
 * @author xql132@zcsmart.com
 * @date 2019/4/3 11:24
 * @description
 */
@SpringBootApplication
@EnableRetry
@EnableAsync
@Slf4j
public class Application {
    @Autowired
    private RetryService service;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }



//    @Bean(name = "threadPoolTaskExecutor")
//    public Executor threadPoolTaskExecutor() {
//        return new ThreadPoolTaskExecutor();
//    }

    @PostConstruct
    public void exe() {
        log.info("开始测试");
        service.retry();
        log.info("结束测试");
    }


}

