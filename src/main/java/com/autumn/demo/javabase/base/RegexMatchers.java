package com.autumn.demo.javabase.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xql132@zcsmart.com
 * @date 2018/11/13 12:50
 * @description
 */
public class RegexMatchers {
    public static void main(String args[]) {
        String str = "0.993";
//        String pattern = "^[0](.[0-9]{2}$)";
        String pattern = "^[0](.[0-9]{2}$)|[1](.[0]{2}$)";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());
    }
}
