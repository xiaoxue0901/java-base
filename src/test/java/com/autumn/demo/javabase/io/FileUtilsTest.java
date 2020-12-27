package com.autumn.demo.javabase.io;

import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/23
 * @time 16:50
 * @description
 */
public class FileUtilsTest {

    @Test
    public void writeFile() {
        String target = "D:\\workspace\\github\\java-base\\doc\\io\\yiJiangNan.txt";
        FileUtils.writeFile(target);
    }

    @Test
    public void readFile() {
        String source = "D:\\workspace\\github\\java-base\\doc\\io\\yiJiangNan.txt";
        FileUtils.readFile(source);
    }

    @Test
    public void readAndwriteFile() {
        String source = "D:\\workspace\\github\\java-base\\doc\\io\\yiJiangNan.txt";
        String target = "D:\\workspace\\github\\java-base\\doc\\io\\yiJiangNan1.txt";
        FileUtils.readAndwriteFile(source, target);
    }

    @Test
    public void getFiles() {
        URL xmlpath = Thread.currentThread().getContextClassLoader().getResource("");
        File file = new File(xmlpath.getPath());
        List<File> files = FileUtils.getFiles(file, ".brmn.xml", ".brmn.xml");
    }
}