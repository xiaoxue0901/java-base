package com.autumn.demo.javabase.generic;

import com.autumn.demo.javabase.bean.Employee;

import java.util.Date;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/25
 * @time 18:11
 * @description
 */
public class EmployeeFactory implements BeanFactory<Employee>  {
    @Override
    public Employee getBean(Class<Employee> clazz) {
        try {
            Employee employee = clazz.newInstance();
            employee.setHireDay(new Date());
            employee.setName("新中国");
            return employee;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 定义泛型方法
     * @param u
     * @param <U>
     * @return
     */
    private <U> U getResult(U u) {
        return u;
    }

    /**
     * 定义泛型方法.
     * @param k
     * @param v
     * @param <K>
     * @param <V>
     * @return
     */
    private <K, V> V getValue(K k, V v) {
        return v;
    }
}
