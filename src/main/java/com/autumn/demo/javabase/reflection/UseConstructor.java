package com.autumn.demo.javabase.reflection;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

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
}
