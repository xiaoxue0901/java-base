package com.autumn.demo.designpattern.demo07_builder;

import com.autumn.demo.designpattern.demo07_builder.build.Builder;
import com.autumn.demo.designpattern.demo07_builder.build.TextBuilder;
import com.autumn.demo.designpattern.demo07_builder.build.HTMLBuilder;

/**
 * @author xql132@zcsmart.com
 * @date 2018/11/8 16:10
 * @description
 */
public class Client {
    public static void main(String[] args) {
        String module = "2";
        exe(module);
    }

    public static void exe(String module) {
        if (module.equals("text")) {
            Builder textBuilder = new TextBuilder();
            Director director = new Director(textBuilder);
            director.construct();
            String result = ((TextBuilder) textBuilder).getResult();
            System.out.println(result);
        } else if (module.equals("html")) {
            Builder htmlBuilder = new HTMLBuilder();
            Director director = new Director(htmlBuilder);
            director.construct();
            String fileName = ((HTMLBuilder) htmlBuilder).getResult();
            System.out.println(fileName+"html文件编写完成");
        } else {
            System.out.println("无匹配对象");
        }
    }
}
