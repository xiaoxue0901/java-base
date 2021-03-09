package com.autumn.demo.javabase.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author xql132@zcsmart.com
 * @date 2021/3/9
 * @time 13:11
 * @description
 */
public class DateUtils {

    /**
     * LocalDateTime转为Date
     * @return
     */
    public static Date localDateTimeToDate() {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = LocalDateTime.now().plusSeconds(1000L).atZone(zoneId);
        return Date.from(zdt.toInstant());

    }
}
