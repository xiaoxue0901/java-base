package com.autumn.demo.designpattern.demo07_builder.build;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Stream;

/**
 * @author xql132@zcsmart.com
 * @date 2018/11/8 15:29
 * @description 使用HTML编写文档
 */
public class HTMLBuilder extends Builder {
    private String filename; // 文件名
    private PrintWriter writer; // 编写文件

    @Override
    public void makeTitle(String title) {
        filename = title +".html";
        try {
            writer = new PrintWriter(new FileWriter(filename)); // 生产PringtWriter
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.println("<html><head><title>" + title + "</title></head><body>");
        writer.println("<h1>" + title + "</h1>");
    }

    @Override
    public void makeString(String str) {
        writer.println("<p>" + str + "</p>");
    }

    @Override
    public void makeItems(String[] items) {
        writer.println("<ul>");
        Stream<String> stream = Stream.of(items);
        stream.forEach(a -> {writer.println("<li>"+a+"</li>");});
        writer.println("</ul>");
    }

    @Override
    public void close() {
        writer.print("</body></html>");
        writer.close();
    }

    public String getResult() {
        return filename;  // 返回文件名
    }
}
