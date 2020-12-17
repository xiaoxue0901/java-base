package com.autumn.demo.javabase.io.file;

import com.autumn.demo.javabase.io.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/16
 * @time 20:27
 * @description Path: 表示的是一个目录名序列，其后还可以跟着一个文件名。
 */
@Slf4j
public class PathDemo {

    public static void usePath() {
        // Unix文件系统
        // 绝对路径
        Path absolute = Paths.get("/home", "cay");
        // 相对路径
        Path relative = Paths.get("src/main", "resources", "user.properties");
        // 从配置文件读取路径
        Properties prop = PropertiesUtil.getProperties("src/main/resources/path.properties");
        // 读取path.properties的配置路径
        String baseDir = prop.getProperty("base.dir");
        // 转换为平台对应的路径格式.
        Path basePath = Paths.get(baseDir);
        log.info("basePath:{}", basePath);
        // 路径转换
        // 方式1: resolve(Path other): 如果othor是绝对路径, 就返回other, 否则, 返回通过链接this和other获得的路径
        Path workRelation = Paths.get("work");
        Path workPath =  basePath.resolve(workRelation);
        // 方式2: resolve(String other)
        Path workPath2 = basePath.resolve("work");
        // 方式3
        // resolveSiblin: 它通过解析指定路径的父路径产生其兄弟路 径。
        Path source = Paths.get("/opt/myapp/work");
        // tempPath: /opt/myapp/temp
        Path tempPath = source.resolveSibling("temp");
        // 相对路径转绝对路径
        Path absolutePath = relative.toAbsolutePath();
        // 常用方法: get(...)通过给定的字符串创建一个路径
        Path p = Paths.get("/home", "cay", "my.properties");
        // parent: /home/cay
        Path parent = p.getParent();
        // file: my.properties
        Path filePath = p.getFileName();
        // root: '/'
        Path root = p.getRoot();
        // 创建一个File对象
        File file = p.toFile();
        // 创建一个Path对象
        Path path = file.toPath();
    }


    public static void main(String[] args) {
        usePath();
    }


}
