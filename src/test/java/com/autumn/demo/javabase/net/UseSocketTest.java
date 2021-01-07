package com.autumn.demo.javabase.net;

import org.junit.Test;

import javax.jws.soap.SOAPBinding;

import static org.junit.Assert.*;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/7
 * @time 18:38
 * @description
 */
public class UseSocketTest {

    @Test
    public void useSocket() {
        UseSocket.useSocket();
    }

    @Test
    public void useInetAddress() {
        UseSocket.useInetAddress();
    }

    @Test
    public void useServerSocket() {
        UseSocket.useServerSocket();
    }
}