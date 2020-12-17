package com.autumn.demo.javabase.io.file;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/17
 * @time 11:34
 * @description Files类: 使普通文件操作变得更快捷
 */
public class FileDemo {

    /**
     * 使用Files类基本的读写操作
     *
     * @throws IOException
     */
    public static void useFilesReadAndWrite() throws IOException {
        Path path = Paths.get("src", "main", "resources", "path.properties");
        // 读取文件内所有内容
        byte[] bytes = Files.readAllBytes(path);
        // 把文件当字符串读入
        String content = new String(bytes, StandardCharsets.UTF_8);
        // 写出一个字符到文件中
        Files.write(path, content.getBytes());
        // 向指定文件追加内容
        Files.write(path, content.getBytes(), StandardOpenOption.APPEND);
        // 将一行集合写出到文件中
        List<String> lines = new ArrayList<>(2);
        lines.add("第一行");
        lines.add("第二行");
        Files.write(path, lines);
    }

    /**
     * 使用Files构造流对象
     *
     * @param path
     * @throws IOException
     */
    public static void useFilesConstruct(Path path) throws IOException {
        // 大文件使用流处理
        InputStream is = Files.newInputStream(path);
        OutputStream os = Files.newOutputStream(path);
        Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
    }

    /**
     * Files: 复制, 移动, 删除操作
     *
     * @throws IOException
     */
    public static void useFilesBaseOps() throws IOException {
        Path source = Paths.get("src", "main", "resources", "path.properties");
        Path target = Paths.get("/home");
        // 将文件从一个位置复制到另一个位置
        Files.copy(source, target);
        // 移动文件（即复制并删除原文件）可以调用
        Files.move(source, target);
        // 如果目标路径已经存在，那么复制或移动将失败。如果想要覆盖已有的目标路径， 可以使用 REPLACE_EXISTING 选项。如果想要复制所有的文件属性，可以使用 COPY_ATTRIBUTES 选项。
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
        // 可以将移动操作定义为原子性的，这样就可以保证要么移动操作成功完成，要么源文 件继续保持在原来位置。
        Files.move(source, target, StandardCopyOption.ATOMIC_MOVE);
        // 删除文件
        Files.delete(source);
        // 要删除的文件不存在，delete()就会抛出异常, 使用deleteIfExists(), 此方法还可以移除空目录
        boolean deleted = Files.deleteIfExists(source);
    }

    /**
     * Files: 创建文件和目录
     *
     * @throws IOException
     */
    public static void useFilesCreat() throws IOException {
        Path path = Paths.get("src", "main", "resources", "temp");
        // 创建新目录(包含路径中的中间目录)
        Files.createDirectories(path);
        // 创建空文件:文件已经存在了，那么这个调用就会抛出异常
        Files.createFile(path);
        // 有些便捷方法可以用来在给定位置或者系统指定位置创建临时文件或临时目录
        Path newPath = Files.createTempFile(path, "prefix", "suffix");
        Path newPath2 = Files.createTempFile("prefix", "suffix");
        Path newPath3 = Files.createTempDirectory(path, "prefix");
        Path newPath4 = Files.createTempDirectory("prefix");
    }

    public static void getFileInfo(Path path) throws IOException {
        // 文件是否存在
        Files.exists(path);
        Files.isHidden(path);
        Files.isReadable(path);
        Files.isWritable(path);
        Files.isExecutable(path);
        Files.isRegularFile(path);
        Files.isDirectory(path);
        Files.isSymbolicLink(path);
        // 返回文件的字节数
        long fileSize = Files.size(path);
        // 所有的文件系统都会报告一个基本属性集，它们被封装在 BasicFileAttributes 接口 中，
        BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
    }

    public static void recursionFile(Path path) throws IOException {
        // 用来获取由一个目录中的所有文件构成的数组
        DirectoryStream<Path> entries = Files.newDirectoryStream(path);
        for (Path entry : entries) {
            System.out.println("entry:" + entry.getFileName());
        }
        entries.close();

        // glob模式过滤文件
        DirectoryStream<Path> entryPaths = Files.newDirectoryStream(path, "*.java");
        // 某个目录的所有子孙成员
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (attrs.isDirectory()) {
                    System.out.println(path);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }


}
