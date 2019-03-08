package com.autumn.demo.javabase.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.autumn.demo.javabase.entity.Student;
import com.autumn.demo.javabase.entity.Teacher;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xql132@zcsmart.com
 * @date 2019/3/6 14:04
 * @description 序列化操作: javaBean转为json字符串
 * 好记性不如烂笔头, 看了还要写, 写了之后还要复习. 否则就忘光光了.去年看了Gson. 今年不用就记不住了
 */
@Slf4j
public class ObjectToJsonDemo {

    /**
     * 序列化: 无论是简单对象, 还是数组, 集合, 复杂对象(包含集合和对象). 都可以用JSON.toJSONString(obj)处理为json字符串;
     */
    public static void objSerialization() {
        // 简单对象直接序列化
        Student student = new Student();
        student.setAge(12);
        student.setId("0001");
        student.setName("周大");

        // 方式1:
        String studentJson1 = JSON.toJSONString(student);
        // 方式2:
        String studentJson2 = JSONObject.toJSONString(student);

        // 数组序列化
        Student[] students = new Student[] {new Student("002", "周二", 10), student};
        // 方式3: 使用JSONArray
        String studentsJson3 = JSONArray.toJSONString(students);
        // 方式4: 使用JSON.toJSONString().
        String studentsJson4 = JSON.toJSONString(students);

        // 集合序列化
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(new Student("002", "周二", 10));
        Teacher teacher = new Teacher();

        teacher.setId("01");
        teacher.setName("明师辅导");
        teacher.setSubject("语文");
        teacher.setStudents(studentList);
        String teacherJson = JSON.toJSONString(teacher);

        log.info("转换1:{}", studentJson1);
        log.info("转换2:{}", studentJson2);
        log.info("转换3:{}", studentsJson3);
        log.info("转换4:{}", studentsJson4);
        log.info("转换5:{}", teacherJson);
    }

    public static void main(String[] args) {
        ObjectToJsonDemo.objSerialization();
    }
}
