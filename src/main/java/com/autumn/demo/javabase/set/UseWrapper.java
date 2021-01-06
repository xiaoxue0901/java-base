package com.autumn.demo.javabase.set;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/6
 * @time 18:17
 * @description
 */
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
}
