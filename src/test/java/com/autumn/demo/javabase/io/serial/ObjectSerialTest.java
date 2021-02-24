package com.autumn.demo.javabase.io.serial;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/22
 * @time 11:24
 * @description
 */
public class ObjectSerialTest {

    @Test
    public void objectSerial() {
        ObjectSerial serial = new ObjectSerial();
        serial.objectSerial();
    }

    @Test
    public void objectDeSerial() {
        ObjectSerial serial = new ObjectSerial();
        serial.objectDeSerial();
    }
}