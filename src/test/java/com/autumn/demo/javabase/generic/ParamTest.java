package com.autumn.demo.javabase.generic;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/25
 * @time 16:15
 * @description
 */
public class ParamTest {

    @Test
    public void swap() {
        new Param().swap(3, 5);
    }

    @Test
    public void valuePass() {
        Param param = new Param();
        param.valuePass();
    }

    @Test
    public void callByReference() {
        new Param().callByReference();
    }
}