package com.autumn.demo.javabase.reflection;

import com.autumn.demo.javabase.bean.Employee;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.*;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/29
 * @time 11:19
 * @description Class类
 */
@Slf4j
public class UseClass {


    /**
     * 3种方式获取类对象
     *
     * @throws ClassNotFoundException
     */
    public void getClassInstance() throws ClassNotFoundException {
        // 1. 获取Class实例: instance.getClass()
        Employee employee = new Employee();
        Class employeeClass = employee.getClass();
        //  根据Class实例获取类名
        String className = employeeClass.getName();
        // 2. 根据类名获取类名对应的Class对象
        Class empClass = Class.forName(className);
        // 3. 获取Class实例
        Class empClass4 = Employee.class;
    }

    /**
     * 根据Class对象快速创建Employee类实例
     *
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void getObjectInstance() throws IllegalAccessException, InstantiationException {
        // 类对象比较
        Employee employee = new Employee();
        if (employee.getClass() == Employee.class) {
            log.info("类对象比较: 虚拟机为每个类型管理一个Class对象, 并且只有一个");
        }
        // 创建类的实例
        Class<Employee> clazz = Employee.class;
        // newInstance(): 调用Employee默认的构造器(无参)
        Employee em = clazz.newInstance();
    }

    /**
     * 根据Class对象创建T的实例
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getInstance(Class<T> clazz) {
        T object = null;
        try {
            object = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * class类方法
     *
     * @param clazz
     */
    public static void classMethod(Class clazz) {
        // 返回类提供的public域(包括超类的公有成员)
        Field[] fields = clazz.getFields();
        // 返回类提供的方法
        Method[] methods = clazz.getMethods();
        // 返回类提供的构造器
        Constructor[] constructor = clazz.getConstructors();

        // 返回类中声明的全部域,方法和构造器(包括私有和受保护成员, 不包含超类成员)
        // 域
        Field[] declaredFields = clazz.getDeclaredFields();
        // 方法
        Method[] declaredMethods = clazz.getDeclaredMethods();
        //  构造器
        Constructor[] constructors = clazz.getDeclaredConstructors();
    }

    /**
     * 构造器
     *
     * @param clazz
     */
    public static void printConstructors(Class clazz) {
        Constructor[] constructors = clazz.getConstructors();
        // 构造器: public String(String original)
        for (Constructor constructor : constructors) {
            // 构造器名称: String
            String name = constructor.getName();
            // 构造器修饰符
            String modifier = Modifier.toString(constructor.getModifiers());
            Class declareClazz = constructor.getDeclaringClass();
            // 构造器参数: String original
            // print parameter types
            log.info("构造器, 修饰符:{}, 名称:{}, 构造器的Class对象:{}", modifier, name, declareClazz.getName());
            Class[] paramTypes = constructor.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) {
                    System.out.println(", " + paramTypes[i].getName());
                } else {
                    System.out.print(paramTypes[i].getName());
                }
            }
        }
    }

    public static void printMethod(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        // public boolean startsWith(String prefix)
        for (Method method : methods) {
            // 方法返回的Class类型: boolean
            Class retType = method.getReturnType();
            // 方法名称: startWith
            String name = method.getName();
            // 修饰符
            String modifiers = Modifier.toString(method.getModifiers());
            // 方法参数类型: String
            log.info("修饰符:{},返回类型:{}, 方法名称:{}", modifiers, retType.getName(), name);
            Class[] paramTypes = method.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                    System.out.print(paramTypes[i].getName());
                } else {
                    System.out.print(paramTypes[i].getName());
                }
            }
        }
    }

    public static void printField(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // getType(): 获取域对应的类型
            Class fieldClazz = field.getType();
            // 域名称
            String name = field.getName();
            // getModifiers(): 用不同的位开关描述public, static等修饰符
            int flag = field.getModifiers();
            // Modifiers: 分析getModifiers()返回的整数值. Modifier.toString(): 将修饰符打印出来
            String modifiers = Modifier.toString(field.getModifiers());
            log.info("域修饰符:{}, 域对应类型:{}, 域名称:{}, ", Modifier.toString(field.getModifiers()), fieldClazz.getName(), name);
        }
    }



}
