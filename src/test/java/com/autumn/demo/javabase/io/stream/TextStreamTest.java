package com.autumn.demo.javabase.io.stream;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/11
 * @time 21:02
 * @description
 */
public class TextStreamTest {

    @Test
    public void getEndSymbol() {
    }

    @Test
    public void constructIsr() {
    }

    @Test
    public void constructOsw() {
    }

    @Test
    public void constructPrint() {
        TextStream.constructPrint();
    }

    @Test
    public void userPrintWriter() {
        try {
            TextStream.userPrintWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}