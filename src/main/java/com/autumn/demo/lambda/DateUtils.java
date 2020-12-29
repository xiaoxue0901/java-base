package com.autumn.demo.lambda;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author xql132@zcsmart.com
 * @date 2019/1/15 11:57
 * @description
 */
@Slf4j
public class DateUtils {
    public static void oldDate() {
        // 可变对象 java.util.Date
        Date date = new Date();
        log.info("可变对象java.util.Date : {}", date);

        date.setYear(date.getYear() + 1);
        log.info("date对象增加1年后, 变化为: date = {}", date);

        // 不可变对象: 实例化成a后就不可变
        BigDecimal a = new BigDecimal("100");
        log.info("BigDecimal是不可变对象, 实例化 a = {}", a);
        BigDecimal b = a.add(new BigDecimal("200"));
        log.info("a增加100后不改变, a = {}, 需用另一个变量接增加200后的值, b = {}", a, b);
    }

    public static void newDate() {
        // 不可变对象
        LocalDate date = LocalDate.now();
        log.info("实例化日期java.time.LocalDate : {}", date);
        LocalDate date2 = date.plusMonths(1);
        log.info("实例化日期java.time.LocalDate, 增加一个月后不变 : {}", date);
        log.info("增加1个月, 用date2 : {}", date2);

        LocalDateTime dateTime = LocalDateTime.now();
        log.info("实例化java.time.LocalDate : {}", dateTime);

    }

    public static String getnow() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHH"));
    }

    public static void main(String[] args) {
//        oldDate();
//        newDate();
        log.info("日期:{}", new Date());
        log.info("日期2:{}", LocalDateTime.now());
        log.info("时间:{}", getnow());
    }
}
