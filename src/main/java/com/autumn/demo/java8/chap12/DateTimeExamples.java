package com.autumn.demo.java8.chap12;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

/**
 * @author xql132@zcsmart.com
 * @date 2020/1/8 10:36
 * @description
 */
@Slf4j
public class DateTimeExamples {
    public static void main(String[] args) {
        useDateTimeFormatter();
//        useDuration();
//        useLocalDateTime();
//        useLocalDate();
//        useLocalTime();
    }

    private static void useDateTimeFormatter() {
        // 日期转为字符串
        LocalDate localDate = LocalDate.of(2020, 1,8);
        String s1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        String s2 = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        log.info("日期转为字符串:{}, {}", s1, s2);
        // 字符串转为日期
        LocalDate localDate1 = LocalDate.parse("20200108", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate localDate2 = LocalDate.parse("2020-01-08", DateTimeFormatter.ISO_LOCAL_DATE);
        log.info("字符串转为日期:{}, {}", localDate1, localDate2);

        // 按照某个模式创建DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.now();
        // 将日期按某种格式转换为对应的字符串
        String formattedDate = date.format(formatter);
        // 将某种格式的字符串转为日期.
        LocalDate date1 = LocalDate.parse(formattedDate, formatter);
        log.info("日期: {}, {}", formattedDate, date1);
    }

    private static void useDuration() {
        // Instant
        Instant instant = Instant.ofEpochSecond(3);
        Instant instant1 = Instant.ofEpochSecond(4, 1000000000);
        LocalTime time1 = LocalTime.of(10, 10, 59);
        LocalTime time2 = LocalTime.of(12, 56, 59);
        Duration duration = Duration.between(time2,time1);
        Duration duration1 = Duration.between(instant, instant1);

        Period period = Period.between(LocalDate.of(2019, 2, 12), LocalDate.of(2020, 1, 8));

        Duration duration2 = Duration.ofMinutes(3);

        Period period1 = Period.ofDays(1);

        log.info("间隔Period:{}, {}", period.getDays(), period1);
        log.info("间隔Duration:{}, {}", duration, duration2);

    }

    private static void useLocalDateTime() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        log.info("日期:{}, 时间:{}, 日期时间:{}", localDate, localTime, localDateTime);
        // 指定
        LocalDate date = LocalDate.of(2020, 1, 7);
        LocalTime time = LocalTime.of(10, 10, 59);
        LocalDateTime dt1 = LocalDateTime.of(2019, Month.DECEMBER, 31, 12,31,59);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(12, 12, 34);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);

        LocalDate date1 = dt1.toLocalDate();
        LocalTime time1 = dt1.toLocalTime();
        log.info("创建日期时间:{}", dt1);
        log.info("创建日期时间:{}", dt2);
        log.info("创建日期时间:{}", dt3);
        log.info("创建日期时间:{}", dt4);
        log.info("创建日期时间:{}", dt5);
        log.info("从LocalDateTime中获取日期:{}", date1);
        log.info("从LocalDateTime中获取时间:{}", time1);
    }

    /**
     * LocalDate使用
     */
    private static void useLocalDate() {
        LocalDate localDate = LocalDate.of(2019,3, 4);
        int year = localDate.getYear();
        Month month = localDate.getMonth();
        int day = localDate.getDayOfMonth();
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int len = localDate.lengthOfMonth();
        boolean leap = localDate.isLeapYear();
        log.info("年:{}", year);
        log.info("月, 英文:{}, 数字{}",month, month.getValue());
        log.info("当月的第{}天",day);
        log.info("星期{}", dayOfWeek);
        log.info("当月有多少天:{}", len);
        log.info("是否是闰年:{}", leap);
        log.info("当前的日期:{}", LocalDate.now());
        log.info("使用TemporalFeild读取LocalDate的值");
        log.info("年:{}", localDate.get(ChronoField.YEAR));
        log.info("月:{}", localDate.get(ChronoField.MONTH_OF_YEAR));
        log.info("日:{}", localDate.get(ChronoField.DAY_OF_MONTH));
        log.info("-------------我是可爱的分隔符-------------------");
        log.info("使用DateTimeFormatter, 日期:{}", LocalDate.parse("2019-12-31"));
    }

    /**
     * LocalTime使用
     */
    private static void useLocalTime() {
        LocalTime time = LocalTime.of(10, 52, 59);
        log.info("时:{}", time.getHour());
        log.info("分:{}", time.getMinute());
        log.info("秒:{}", time.getSecond());
        log.info("时:{}", time.get(ChronoField.HOUR_OF_DAY));
        log.info("分:{}", time.get(ChronoField.MINUTE_OF_HOUR));
        log.info("秒:{}", time.get(ChronoField.SECOND_OF_MINUTE));
        log.info("-------------我是可爱的分隔符-------------------");
        try {
            log.info("使用DateTimeFormatter, 时间:{}", LocalTime.parse("12:30:59"));
        } catch (DateTimeParseException e) {
            log.error("提供的时间格式不正确", e);
        }


    }
}
