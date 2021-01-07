package com.autumn.demo.javabase.reflection;

import com.alibaba.fastjson.JSON;
import com.autumn.demo.javabase.bean.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.*;
import java.util.Date;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/29
 * @time 13:13
 * @description Method: 描述类的方法
 */
@Slf4j
public class UseMethod {

    /**
     * Method类的方法调用
     *
     * @param clazz
     */
    public static void useMethod(Class clazz) {
        // 返回所有的公共方法(包括从超类继承的公共方法)
        Method[] methods = clazz.getMethods();
        // 返回Clazz类或接口的全部方法(不包括超类继承的方法)
        Method[] declareMethods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            // 方法对应的类对象
            Class declareClass = method.getDeclaringClass();
            // 方法名
            String name = method.getName();
            // 修饰符
            String modifier = Modifier.toString(method.getModifiers());
            // 方法返回类型
            Class retClass = method.getReturnType();
            // 方法参数对应的Class类型数组
            Class[] paramTypes = method.getParameterTypes();
            // 方法抛出的异常类数组
            Class[] expTypes = method.getExceptionTypes();
            log.info("方法所在的类名:{}, 修饰符:{},方法返回类型:{} 方法名:{}, 方法参数数组:{}, 方法抛出的异常数组:{}",
                    declareClass.getName(), modifier, retClass.getName(), name, JSON.toJSONString(paramTypes), JSON.toJSONString(expTypes));
        }
    }

    /**
     * Method的invoke方法
     */
    public static void useInvoke() {
        // Object invoke(Object implicitParameter, Object[] explictParamters);
        Employee e = new Employee("李三", 30.45D, new Date());
        // method: 是Employee的getName()
        Class employeeClass = e.getClass();
        Method[] methods = employeeClass.getDeclaredMethods();
        try {
            for (Method method : methods) {
                // 方法名称
                String methodName = method.getName();
                switch (methodName) {
                    case "getName":
                        // 调用getName()
                        String empName = (String) method.invoke(e);
                        log.info("name:{}", empName);
                        break;
                    case "getSalary":
                        // 调用getSalary()
                        Double salary = (Double) method.invoke(e);
                        log.info("salary:{}", salary);
                        break;
                    default:
                        break;
                }
            }
            // 获取单个方法: getMethod(String name, Class...paramterTypes)
            Method getNameMethod = employeeClass.getMethod("getName");
            Method setSalaryMethod = employeeClass.getMethod("setSalary", double.class);
            log.info("get方法:{}, set方法:{}", getNameMethod.getName(), setSalaryMethod.getName());
            setSalaryMethod.setAccessible(true);
            // 调用setSalary()
            setSalaryMethod.invoke(e, 89999D);
            log.info("调用setSalary方法后的工资:{}", e.getSalary());


        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        } catch (InvocationTargetException invocationTargetException) {
            invocationTargetException.printStackTrace();
        } catch (NoSuchMethodException noSuchMethodException) {
            noSuchMethodException.printStackTrace();
        }


    }

    /**
     * 获取泛型类型
     * @param tClass
     * @param <T>
     */
    public static <T> void useGenericMethod(Class<T> tClass) {
        Method[] methods = tClass.getMethods();
        for (Method method : methods) {
            log.info("方法名:{}", method.getName());
            if (method.getName().equals("getResp")) {
                // 如果method被声明为泛型方法, 则获得泛型类型变量.
                TypeVariable[] variables = method.getTypeParameters();
                log.info("TypeVariable:{}", JSON.toJSONString(variables));
                // 获得这个方法被声明的泛型参数类型
                Type[] paramTypes = method.getGenericParameterTypes();
                log.info("Type:{}", JSON.toJSONString(paramTypes));
                // 获取method方法被声明的泛型返回类型
                Type type = method.getGenericReturnType();
                log.info("Type2:{}", JSON.toJSONString(type));
            }

        }
    }
}
