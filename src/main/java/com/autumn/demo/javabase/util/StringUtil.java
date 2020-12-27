package com.autumn.demo.javabase.util;

/**
 * @author xql132@zcsmart.com
 * @date 2019/2/1 15:18
 * @description
 */
public class StringUtil {
    public static String getStr(String date) {
        String newD = date.replaceAll("[[\\s-:punct:]]","");
        System.out.println("去除后的字符串为:{}"+newD + " ,长度为:" + newD.length());
        return newD;
    }

    // org.apache.commons.lang.StringUtils 类提供了 String 的常用操作,最为常用的判空有如下两种 isEmpty(String str) 和 isBlank(String str)。

    /**
     * str为null或者是空字符串, 都返回true; 否则返回false
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {

        return str == null || str.length() == 0;
    }

    /**
     * 对比jdk1.6 的isEmpty(): value.length == 0;
     *  为判断str是否为null.
     * @param str
     * @return
     */
    public boolean isEmpty_JDK(String str) {
        return str.length() == 0;
    }

    /**
     * 判断str非空. 非null和非空字符串
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断str是否为空字符串
     * 1. str 为null 或 为空字符串, 返回true
     * 2. str 中有一个字符不为空格, 返回false
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            // 遍历每一个字符: 只要有一个字符非空格, 则此字符串is not blank
            for (int i = 0; i < strLen; i++) {
                // 取出位置i所在的字符str.charAt(i), 判断字符是否为空格Character.isWhitespace()
                if (!Character.isWhitespace(str.charAt(i))) {
                    // 如果位置i所在的字符为空格. 继续判断下一个字符; 如果不是空格, 则返回false, 结束循环
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    /**
     * 判断str是否为非空字符串
     * 1. 为空字符串, 返回false
     * 2. 为非空字符串, 返回true
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static void main(String[] args) {
        getStr("2019-01-31 12:23:54");
    }
}
