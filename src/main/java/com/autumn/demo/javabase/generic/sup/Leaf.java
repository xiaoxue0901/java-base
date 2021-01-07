package com.autumn.demo.javabase.generic.sup;

import java.util.Comparator;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/30
 * @time 18:28
 * @description Leaf: 叶子类
 */
public class Leaf implements Comparator<Leaf> {
    @Override
    public int compare(Leaf o1, Leaf o2) {
        return 0;
    }
}
