package com.autumn.demo.javabase.io.serial;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/22
 * @time 11:13
 * @description
 */
@Slf4j
public class ObjectSerial {

    static String FILE_PATH = "D:\\workspace\\github\\java-base\\src\\main\\resources\\testObjectSerial.out";

    /**
     * 对象序列化
     */
    public void objectSerial() {
        // 将Student序列化到文件中
        Student student = new Student(13, "nana");
        try {
            // 使用输出流
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
            // 对象写入输出流
            os.writeObject(student);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 对象反序列化
     */
    public void objectDeSerial() {
        // 读取文件内容到对象输入流
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(FILE_PATH));
            Student student = (Student)is.readObject();
            log.info("name:{}, age:{}", student.getName(), student.getAge());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
