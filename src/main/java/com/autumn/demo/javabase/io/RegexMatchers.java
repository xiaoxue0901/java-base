package com.autumn.demo.javabase.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xql132@zcsmart.com
 * @date 2018/11/13 12:50
 * @description 正则表达式
 */
public class RegexMatchers {
    static String patternSt = "^[0](.[0-9]{2}$)|[1](.[0]{2}$)";

    private static final Pattern pattern = Pattern.compile(patternSt);


    public static void test(String a) {
        // String pattern = "^[0](.[0-9]{2}$)";
        Matcher matcher = pattern.matcher(a);
        System.out.println(matcher.matches());
        System.out.println("调用静态方法");
    }

}
