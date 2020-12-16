package com.autumn.demo.javabase.io.stream;

import com.autumn.demo.javabase.bean.Operator;
import org.junit.Test;

import java.util.Date;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/16
 * @time 20:19
 * @description
 */
public class SerialCloneTest {

    @Test
    public void testDeepClone() {
        Operator lily = new Operator("Lily", 5000, new Date());
        Operator lily2 = (Operator) lily.clone();
        System.out.println("lily:"+lily);
        System.out.println("lily2:"+lily2);


    }
}