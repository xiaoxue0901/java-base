package com.autumn.demo.javabase.io.stream;

import com.autumn.demo.javabase.bean.Employee;
import com.autumn.demo.javabase.bean.Manager;
import com.autumn.demo.javabase.constant.BaseConsts;

import java.io.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/16
 * @time 13:01
 * @description 对象流与序列化
 */
public class ObjectStream {
    private static String ABS_FILE = BaseConsts.PATH_EMPLOYEE + File.separator + "employee.dat";

    /**
     * ObjectOutputStream和ObjectInputStream的使用
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void useObjectStream() throws IOException, ClassNotFoundException {
        // 保存对象到employee.dat中
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ABS_FILE));
        // 使用方法
        Employee employee = new Employee("Harry Hacker", 5000, new Date());
        Manager manager = new Manager("Carl Cracker", 12555, new Date());
        oos.writeObject(employee);
        oos.writeObject(manager);

        // 读取对象
        ObjectInputStream ooi = new ObjectInputStream(new FileInputStream(ABS_FILE));
        Employee e = (Employee) ooi.readObject();
        Manager m = (Manager) ooi.readObject();

    }

}
