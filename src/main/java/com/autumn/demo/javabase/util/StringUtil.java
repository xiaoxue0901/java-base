package com.autumn.demo.javabase.util;

/**
 * @author xql132@zcsmart.com
 * @date 2019/2/1 15:18
 * @description
 */
public class StringUtil {
    public static String getStr(String date) {
//        String newD = date.replaceAll("[[\\s-:punct:]]","");
        String newD = date.replaceAll("[[\\s-:punct:]]","");
        System.out.println("去除后的字符串为:{}"+newD + " ,长度为:" + newD.length());
        return newD;
    }

    public static void main(String[] args) {
        getStr("2019-01-31 12:23:54");
    }
}
