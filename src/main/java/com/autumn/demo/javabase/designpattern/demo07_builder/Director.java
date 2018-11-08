package com.autumn.demo.javabase.designpattern.demo07_builder;

import com.autumn.demo.javabase.designpattern.demo07_builder.build.Builder;

/**
 * @author xql132@zcsmart.com
 * @date 2018/11/8 15:27
 * @description 编写一个文档的类
 *
 */
public class Director {
    // Builder是抽象类, 是无法生成实例的.
    private Builder builder; // 因为接收的参数是Builder类的子类, 所以可以保存在builder字段中.

    public Director(Builder builder) {
        this.builder = builder;
    }

    /**
     * 编写文档的方法
     */
    public void construct() {
        builder.makeTitle("Builder模式");
        builder.makeString("建造者模式-构造复杂实例");
        builder.makeItems(new String[] {
                "TextBuilder", "HTMLBuilder", "Director"
        });
        builder.makeString("晚上");
        builder.makeItems(new String[] {
                "晚上好", "晚安", "再见"
        } );

        builder.close(); // 完成文档

    }
}
