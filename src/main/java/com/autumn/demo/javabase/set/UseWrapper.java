package com.autumn.demo.javabase.set;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/6
 * @time 18:17
 * @description
 */
@Slf4j
public class UseWrapper {

    public static void useArrays(){
        // 数组转集合
        String[] a = new String[]{"a", "b", "c", "d"};
        List<String> aList = Arrays.asList(a);
        // 创建一个包含100个字符串的List, 每个都设置为DEFAULT
        List<String> setting = Collections.nCopies(100, "DEFAULT");
        // 创建视图对象
        Set<String> view = Collections.singleton("view");
        // 子范围视图: 第3个~第4个元素, 不包含第四个
        List<String> subList = aList.subList(3, 4);
        // 删除子范围, aList也删除了subList的元素
        subList.clear();
    }

    public static void useHashtable(){
        // Hashtable: 和HashMap用法一致, 区别是: Hashtable是同步的
        Hashtable<String, String> hashtable = new Hashtable<>();
        // 枚举
        Enumeration<String> keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            String e = keys.nextElement();
        }
        // 值集合
        Enumeration<String> values = hashtable.elements();
    }

    public static void useProperties() {
        // Properties: 属性映射表
        Properties properties = new Properties();
        String key1 = null;
        // 获取属性对应关系
        String value1 = (String) properties.get(key1);
        // 取不到key对应的值, 则赋值默认值
        String defaultValue = properties.getProperty(key1, "default");

        try {
            // 从InputStream加载属性表
            properties.load(new FileInputStream(""));
            // 把属性映射表存储到OutputStream
            properties.store(new FileOutputStream(""), "存储");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void useStack() {
        // 栈
        Stack<String> stack = new Stack<>();
        // 将a压入栈并返回a
        String a = stack.push("a");
        String b = stack.push("b");
        // 弹出栈顶的item. 栈为空,不能用此方法
        String pop = stack.pop();
        // 返回栈顶元素, 但不弹出. 栈为空,不能用此方法
        String peek = stack.peek();
        log.info("a:{}, b:{}, pop:{}, peek:{}", a, b, pop, peek);
    }

    public static void useBitSet() {
        // 创建一个位集
        BitSet bitSet = new BitSet(64);
        // 返回位集的逻辑长度
        int length = bitSet.length();
        // 设置位
        bitSet.set(0, true);
        // 获取一个位
        boolean bit = bitSet.get(0);
        // 清除位
        bitSet.clear();
        BitSet bitSet1 = new BitSet(64);
        // bitSet和bitSet1进行逻辑"AND"
        bitSet.and(bitSet1);
        // bitSet和bitSet1进行逻辑"OR"
        bitSet.or(bitSet1);
        // bitSet和bitSet1进行逻辑"XOR"
        bitSet.xor(bitSet1);
        // 清除这个位集中对另一个位集中设置的所有位
        bitSet.andNot(bitSet1);;


    }
}
