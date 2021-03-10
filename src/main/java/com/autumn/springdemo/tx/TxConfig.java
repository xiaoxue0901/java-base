package com.autumn.springdemo.tx;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author xql132@zcsmart.com
 * @date 2021/3/10
 * @time 10:31
 * @description 定义事务管理器
 */
@Configuration
public class TxConfig {

    @Bean
    public DataSource dataSource() {
        return null;
    }

    @Bean
    private PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }
 }
