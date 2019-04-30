package com.autumn.demo.javabase.designpattern.demo01_iterator.A1;

public interface Iterator {
    /**
     * 判断是否存在下一个元素。 hasNext方法主要用于循环终止条件。
     * @return true-存在；false-不存在（遍历至集合末尾）
     */
    public abstract boolean hasNext();

    /**
     * 返回集合中的下一个元素。（在Iterator接口的实现类的next方法隐含着将迭代器移动至下一个元素的处理）
     * @return Object=集合中的一个元素。
     */
    public abstract Object next();
}
