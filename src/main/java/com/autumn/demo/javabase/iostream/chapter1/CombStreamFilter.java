package com.autumn.demo.javabase.iostream.chapter1;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author xql132@zcsmart.com
 * @date 2019/8/8 21:05
 * @description
 */
@Slf4j
public class CombStreamFilter {

    public void useFileInputStream() {
        try {
            log.info("用户目录:{}", System.getProperty("user.dir"));
            // 所有在java.io中的类都将相对路径名解释为以用户工作目录开始
            // 查看用户目录下名为"employee.dat"的文件
            FileInputStream fin = new FileInputStream("employee.dat");
            // 用户工作目录
            System.getProperty("user.dir");

        } catch (FileNotFoundException e) {
            log.error("异常", e);
        }
    }
}
