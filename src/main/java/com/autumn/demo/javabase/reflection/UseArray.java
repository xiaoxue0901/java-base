package com.autumn.demo.javabase.reflection;

import java.lang.reflect.Array;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/29
 * @time 18:00
 * @description
 */
public class UseArray {

    public static void useArray(Object array) {
        // 根据索引获取数组元素
        Object object = Array.get(array, 0);
        // 获取指定类型的数组内容
        int index = 0;
        boolean value = Array.getBoolean(array, index);
        byte byteValue = Array.getByte(array, index);
        char charValue = Array.getChar(array, index);
        long longValue = Array.getLong(array, index);
        short shortValue = Array.getShort(array, index);
        int intValue = Array.getInt(array, index);
        float floatValue = Array.getFloat(array, index);
        double doubleValue = Array.getDouble(array, index);
        // 在指定位置设置新值
        Array.set(array, index, "xxx");
        Array.setBoolean(array, index, true);
        Array.setChar(array, index, ' ');
        Array.setInt(array, index, 1);
        Array.setLong(array, index, 1L);
        Array.setDouble(array, index, 3.23D);
        Array.setFloat(array, index, 3.2F);
        Array.setShort(array, index, (short) 3);

        // 获取数组长度
        int len = Array.getLength(array);
        // 构造实例
        creatArray(array, 30);

    }

    /**
     * 构造数组:
     *
     * @param a
     * @param newLen
     * @return
     */
    public static Object creatArray(Object a, int newLen) {
        // 1. 获取a数组的类对象
        Class clazz = a.getClass();
        // 确认clazz是一个数组
        if (!clazz.isArray()) {
            return null;
        }
        // 获取数组元素类型
        Class componentType = clazz.getComponentType();
        // 创建数组: newInstance(Class类型, 长度)
        Object newArray = Array.newInstance(componentType, newLen);
        // 数组复制
        System.arraycopy(a, 0, newArray, 0, newLen);
        return newArray;
    }
}
