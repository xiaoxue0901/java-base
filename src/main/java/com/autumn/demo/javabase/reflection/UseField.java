package com.autumn.demo.javabase.reflection;

import com.autumn.demo.javabase.bean.Employee;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/29
 * @time 13:13
 * @description 描述类的域
 */
@Slf4j
public class UseField {

    public static void useField(Class clazz) throws IllegalAccessException, InstantiationException {
        // 获取指定类或超类的共有域(public)
        Field[] fields = clazz.getFields();
        // 获取指定类的全部域
        Field[] declaredFields = clazz.getDeclaredFields();

        for (Field field : declaredFields) {
            // 描述域对应的Class对象,Clazz是 Empolyee的话, fieldClass就是Employee
            Class fieldClass = field.getDeclaringClass();
            // 描述构造器,方法或域的修饰符的整型数值
            int m = field.getModifiers();
            // 描述域名的字符串
            String name = field.getName();
            // 描述域的类型
            Class fieldClass2 = field.getType();
            log.info("描述域的Class对象:{}, 描述域的修饰符: {}, 描述域名的字符串:{}, 描述域的类型:{}", fieldClass.getName(),
                    m, name, fieldClass2.getName());
            // 让private修饰的域可以设置值
            Employee employee = (Employee) clazz.newInstance();
            if(field.getName().equals("name")) {
                field.setAccessible(true);
                field.set(employee, "xxx");
                log.info("域值:{}", field.get(employee));
            }
            log.info("name: {}", employee.getName());
        }


    }


}
