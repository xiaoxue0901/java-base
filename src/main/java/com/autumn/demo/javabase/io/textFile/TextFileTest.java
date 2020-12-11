package com.autumn.demo.javabase.io.textFile;

import com.autumn.demo.javabase.bean.Employee;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author xql132@zcsmart.com
 * @date 2019/7/16 21:15
 * @description
 */
public class TextFileTest {

    private static void writeData(Employee[] employees, PrintWriter printWriter) {
        printWriter.println(employees.length);

        for (Employee e: employees) {

        }
    }

    /**
     * 输出employee对象
     * @param printWriter
     * @param employee
     */
    public static void writeEmployee(PrintWriter printWriter, Employee employee) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(employee.getHireDay());
        printWriter.println(employee.getName()+"|"+ employee.getSalary()+"|"+calendar.get(Calendar.YEAR)
        +"|"+(calendar.get(Calendar.MONTH)+1) + "|"+calendar.get(Calendar.DAY_OF_MONTH));

    }


    public static void main(String[] args) {
        String a = "1.00";
        System.out.println(Double.parseDouble(a));
    }
}
