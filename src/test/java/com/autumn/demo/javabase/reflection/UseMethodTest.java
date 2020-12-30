package com.autumn.demo.javabase.reflection;

import com.autumn.demo.javabase.bean.Employee;
import com.autumn.demo.javabase.generic.RespVO;
import org.apache.poi.ss.formula.functions.T;
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

    @Test
    public void useGenericMethod() {
        Class<RespVO> employeeClass = RespVO.class;
        UseMethod.useGenericMethod(employeeClass);
    }
}