package com.autumn.demo.javabase.reflection;

import com.autumn.demo.javabase.bean.Employee;
import org.junit.Test;

import java.lang.reflect.Modifier;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/29
 * @time 13:48
 * @description
 */
public class UseClassTest {

    @Test
    public void getClassInstance() {
    }

    @Test
    public void getObjectInstance() {
    }

    @Test
    public void getInstance() {
    }

    @Test
    public void classMethod() {
    }

    @Test
    public void printConstructors() {
    }

    @Test
    public void printMethod() {
    }

    @Test
    public void printField() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter class name (e.g java.util.Date):");
        String name = scanner.next();
        Class clazz = null;
        try {
            clazz = Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Class superClazz = clazz.getSuperclass();
        String modifiers = Modifier.toString(clazz.getModifiers());
        if (modifiers.length() > 0) {
            System.out.println(modifiers + " ");
        }
        System.out.println("class " + name);
        if (superClazz !=null && superClazz != Object.class) {
            System.out.println("extends " + superClazz.getName());
        }
        System.out.println("\n{\n");
        UseClass.printConstructors(clazz);
        System.out.println();
        UseClass.printMethod(clazz);
        System.out.println();
        UseClass.printField(clazz);
        System.out.println("}");
        System.exit(0);
    }
    @Test
    public void reflectionTest() throws ClassNotFoundException {

    }

    @Test
    public void genericClass() {
        Class<Employee> employeeClass = Employee.class;
        UseClass.genericClass(employeeClass);
    }
}