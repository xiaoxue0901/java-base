package com.autumn.demo.javabase.reflection;

import com.autumn.demo.javabase.bean.Employee;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/29
 * @time 15:32
 * @description
 */
public class UseFieldTest {

    @Test
    public void useField() throws InstantiationException, IllegalAccessException {
        UseField.useField(Employee.class);
    }
}