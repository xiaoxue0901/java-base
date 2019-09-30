package com.autumn.demo.javabase.iostream.chapter1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author xql132@zcsmart.com
 * @date 2019/8/8 21:09
 * @description
 */
public class CombStreamFilterTest {

    public CombStreamFilter filter;
    @Before
    public void setUp() throws Exception {
        filter = new CombStreamFilter();
    }

    @Test
    public void useFileInputStream() {
        filter.useFileInputStream();
    }

}