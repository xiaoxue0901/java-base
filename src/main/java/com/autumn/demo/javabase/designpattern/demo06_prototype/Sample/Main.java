package com.autumn.demo.javabase.designpattern.demo06_prototype.Sample;


import com.autumn.demo.javabase.designpattern.demo06_prototype.Sample.deepclone.Car;
import com.autumn.demo.javabase.designpattern.demo06_prototype.Sample.deepclone.Person;
import com.autumn.demo.javabase.designpattern.demo06_prototype.Sample.framework.Manager;
import com.autumn.demo.javabase.designpattern.demo06_prototype.Sample.framework.Product;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 准备
        Manager manager = new Manager();
        UnderlinePen upen = new UnderlinePen('~');
        MessageBox mbox = new MessageBox('*');
        MessageBox sbox = new MessageBox('/');
        manager.register("strong message", upen);
        manager.register("warning box", mbox);
        manager.register("slash box", sbox);

        // 生成
        Product p1 = manager.create("strong message");
        p1.use("Hello, world.");
        Product p2 = manager.create("warning box");
        p2.use("Hello, world.");
        Product p3 = manager.create("slash box");
        p3.use("Hello, world.");

        MessageBox clonea = (MessageBox) manager.createBean("warning box");
        clonea.use("hello, world...");
        System.out.println(clonea.getDecochar());

        /*******************************/
        Car car = new Car("brand", 10000);
        Person person = new Person("测试", 23, car);
        Person person2 = Manager.createDeepObj(person);
        System.out.println("复制的值为:{}"+person2.getCar().getMaxSpeed());

        manager.register("person", person);
        Person person3 = manager.createDeep("person");
        System.out.println("使用原型模式赋值:"+person3.use("你好"));
        System.out.println("打印结果:"+person3.getCar().getMaxSpeed());
    }
}
