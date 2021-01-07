package com.autumn.demo.javabase.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author xql132@zcsmart.com
 * @date 2019/7/16 21:16
 * @description
 */
@Slf4j
public class Employee implements Serializable {
    private String name;
    private double salary;
    private Date hireDay;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, double salary) {
        log.info("构造器: this:{}, this.toString():{}", this, this.toString());
        // this指的是Empoyee类产生的实例对象
        this.name = name;
        this.salary = salary;
    }

    public Employee(String name, double salary, Date hireDay) {
        this.name = name;
        this.salary = salary;
        this.hireDay = hireDay;
    }

    @Override
    public boolean equals(Object o) {
        // 1. 比较两个对象的存储地址.this就是this.hashCode()的值.
        if (this == o) return true;
        // 2. o对象非空, 并且要比较的两个对象是同一个类产生的对象
        if (o == null || getClass() != o.getClass()) return false;
        // 3. 比较2个对象的每个实例域是否相等.
        Employee employee = (Employee) o;
        return Double.compare(employee.salary, salary) == 0 &&
                name.equals(employee.name) &&
                hireDay.equals(employee.hireDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary, hireDay);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Employee{");
        sb.append("name='").append(name).append('\'');
        sb.append(", salary=").append(salary);
        sb.append(", hireDay=").append(hireDay);
        sb.append('}');
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getHireDay() {
        return hireDay;
    }

    public void setHireDay(Date hireDay) {
        this.hireDay = hireDay;
    }
}
