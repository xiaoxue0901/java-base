package com.autumn;

import com.autumn.springdemo.async.ExeNoticeService;
import com.autumn.springdemo.service_retry.RetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * @author xql132@zcsmart.com
 * @date 2019/4/3 11:24
 * @description
 */
@SpringBootApplication
// @EnableAsync //在application或需要执行重试的类上使用@EnableAsync
@Slf4j
public class Application {
    @Autowired
    private RetryService service;
    @Autowired
    private ExeNoticeService noticeService;

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
        service.retry("测试异常处理");
        log.info("结束测试");
    }

    @PostConstruct
    public void exeAsyncTask() {
        log.info("主线程准备执行异步任务");
        noticeService.exeAsyncTask();
        log.info("主线程结束");
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

