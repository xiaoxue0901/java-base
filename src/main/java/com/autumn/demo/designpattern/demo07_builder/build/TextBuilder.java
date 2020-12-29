package com.autumn.demo.designpattern.demo07_builder.build;

import java.util.stream.Stream;

/**
 * @author xql132@zcsmart.com
 * @date 2018/11/8 15:29
 * @description 使用纯文本编写文档
 */
public class TextBuilder extends Builder {
    private StringBuffer buffer = new StringBuffer(); // 文档内容保存在该字段中
    @Override
    public void makeTitle(String title) {
        buffer.append("===========================\n"); // 装饰线
        buffer.append("**"+title+"**\n");
        buffer.append("\n");
    }

    @Override
    public void makeString(String str) {
        buffer.append('*'+str+"\n");
        buffer.append("\n");
    }

    @Override
    public void makeItems(String[] items) {
        Stream<String> stream = Stream.of(items);
        stream.forEach((a) -> buffer.append("*" + a + "\n"));

    }

    @Override
    public void close() {
        buffer.append("======================\n"); // 装饰线
    }

    public String getResult() {
        return buffer.toString();           // 将StringBuffer变换为String
    }
}
