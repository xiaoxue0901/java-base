package com.autumn.demo.javabase.iostream.chapter1;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Arrays;

/**
 * @author xql132@zcsmart.com
 * @date 2020/5/28 15:18
 * @description
 */
@Slf4j
public class FileUtil {

    public static void readFileName(String dir) {
        File file = new File(dir);
        String[] fileNames = file.list();
        Arrays.stream(fileNames).forEach(System.out::println);
    }

    public static void main(String[] args) {
        readFileName("C:\\Users\\xql\\Desktop\\20200528交换多流程改造");
    }
}
