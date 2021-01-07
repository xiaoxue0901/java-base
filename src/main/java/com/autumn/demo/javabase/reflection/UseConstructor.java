package com.autumn.demo.javabase.reflection;

import com.alibaba.fastjson.JSON;
import com.autumn.demo.javabase.bean.Employee;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Date;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/29
 * @time 13:13
 * @description Constructor: 描述类的构造器
 */
@Slf4j
public class UseConstructor {


    public static void useConstructor(Class clazz) {
        // 包含了clazz对象所描述的类的所有的共有构造器
        Constructor[] constructors = clazz.getConstructors();
        // 包含class对象描述的类的所有构造器
        Constructor[] declaredCons = clazz.getDeclaredConstructors();
        for (Constructor constructor : declaredCons) {
            // 修饰符
            String modifier = Modifier.toString(constructor.getModifiers());
            // 构造器名称: 包名+类名
            String name = constructor.getName();
            // 构造器参数
            Class[] paramTypes = constructor.getParameterTypes();
            // 构造器对应的Class类
            Class declareClazz = constructor.getDeclaringClass();
            log.info("构造器: 修饰符:{}, 名称:{}, 构造器参数:{}, 构造器对应的Class类:{}",
                    modifier, name, JSON.toJSONString(paramTypes), declareClazz.getName());
        }
    }

    /**
     * 使用构造器构造有参对象
     * @param employeeClass
     */
    public static void constructEmployee(Class employeeClass) {
        try {
          Constructor<Employee> lily =   employeeClass.getConstructor(String.class, double.class, Date.class);
          Employee employee = lily.newInstance("lily", 5600, new Date());
          log.info("lily: {}", JSON.toJSONString(employee));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
