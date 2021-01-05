package com.autumn.demo.javabase.set.map;

import com.autumn.demo.javabase.bean.Employee;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/5
 * @time 18:24
 * @description
 */
public class UseMap {

    public static void useMap() {
        Map<String, Employee> staff = new HashMap<>();
        Employee harray = new Employee("Harray Hacker");
        // 映射表添加对象
        staff.put("9996", harray);
        // 映射表取对象
        staff.get("9996");
    }
}
