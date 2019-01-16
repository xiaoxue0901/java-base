package com.autumn.demo.javabase.temp;

import java.text.DecimalFormat;

/**
 * @author xql132@zcsmart.com
 * @date 2018/12/10 14:56
 * @description
 */

public class StudentTest {
    public int evaluate(String expression) {
        int sum = 0;
        for (String summand: expression.split("\\+"))
            sum += Integer.valueOf(summand);
        return sum;
    }

    public static void main(String[] args) {
        String password = "111111";
        StringBuffer pwdSb = new StringBuffer();
        pwdSb.append(new DecimalFormat("00").format(password.length())).append(password);
        System.out.println(pwdSb.toString());
        if (pwdSb.length() == 8) {
            pwdSb.append("FFFFFFFF");
        } else {
            int x = 16 - pwdSb.length();
            for (int i = 0; i < x; i++) {
                pwdSb.append("F");
            }
        }
        System.out.println(pwdSb.toString());
        System.out.println("9999992220000000019".substring(6,18));
    }
}
