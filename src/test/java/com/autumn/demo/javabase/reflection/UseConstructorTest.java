package com.autumn.demo.javabase.reflection;

import com.autumn.demo.javabase.bean.Employee;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/29
 * @time 16:22
 * @description
 */
public class UseConstructorTest {

    @Test
    public void useConstructor() {
        UseConstructor.useConstructor(Employee.class);
    }

    @Test
    public void constructEmployee() {
        UseConstructor.constructEmployee(Employee.class);
    }
}