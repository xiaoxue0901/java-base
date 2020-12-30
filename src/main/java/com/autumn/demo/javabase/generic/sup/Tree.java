package com.autumn.demo.javabase.generic.sup;

import java.util.Comparator;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/30
 * @time 18:29
 * @description Tree: Leaf的子类
 */
public class Tree extends Leaf  {
    /**
     * compare的参数类型是Leaf, 不是Tree
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Leaf o1, Leaf o2) {
        return super.compare(o1, o2);
    }

    /**
     * min方法定义
     * @param req
     * @param <T>
     * @return
     */
    public <T extends Comparator<? super T>> T min(T req) {
        // 假设T是Tree, 不是直接继承Comparator, 而是继承Leaf实现compare(Leaf o1, Leaf o2), 而不是compare(Tree o1, Tree o2);
        // Leaf是Tree的父类, 实现的是compa
        return null;
    }
}
