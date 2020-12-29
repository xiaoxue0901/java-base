package com.autumn.demo.javabase.reflection;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/29
 * @time 16:09
 * @description
 */
public class UseMethodTest {

    @Test
    public void useMethod() {
        UseMethod.useMethod(UseClass.class);
    }

    @Test
    public void useInvoke() {
        UseMethod.useInvoke();
    }
}