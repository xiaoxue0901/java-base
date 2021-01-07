package com.autumn.demo.javabase.inter;

import java.util.Arrays;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/30
 * @time 15:13
 * @description Orange类实现接口DefineComparable, Powered
 */
public class Orange implements DefineComparable<Orange>, Powered, Cloneable {
    private int size;

    /**
     * 数组排序
     */
    public void sort() {
        int[] a = new int[]{4, 5, 7, 6};
        Arrays.sort(a);
    }

    @Override
    public int compareTo(Orange other) {
        Integer a = 5;
        // Integer方法的compareTo()
        return a.compareTo(other.size);
    }

    @Override
    public double milesPerGallon() {
        return 0;
    }

    @Override
    public void move(double x, double y) {

    }

    /**
     * 实现Cloneable的clone()
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void useInterface() {
        // 声明接口的变量
        DefineComparable orange = new Orange();
        Orange orange1 = new Orange();
        // 使用instanceOf检查一个对象是否实现了某个特定的接口
        if (orange1 instanceof DefineComparable) {

        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
