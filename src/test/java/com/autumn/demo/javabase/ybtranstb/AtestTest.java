package com.autumn.demo.javabase.ybtranstb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author xql132@zcsmart.com
 * @date 2019/4/18 18:55
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AtestTest {
    @Autowired
    private Atest atest;
    @Autowired
    private Btest btest;

    @Test
    public void name() {
        atest.getAInfo();
        btest.getBInfo();
    }
}