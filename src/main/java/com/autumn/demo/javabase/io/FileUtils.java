package com.autumn.demo.javabase.io;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xql132@zcsmart.com
 * @date 2018/11/13 17:41
 * @description I/O常用的操作
 */
@Slf4j
public class FileUtils {

    /**
     * 写数据到文件中
     *
     * @param targetPath
     */
    public static void writeFile(String targetPath) {
        File file = new File(targetPath);
        try {
            // 文件不存在, 创建文件
            if (!file.exists()) {
                log.debug("path:{}", Paths.get(targetPath).getFileName());
                file = Files.createFile(Paths.get(targetPath)).toFile();
            }
            // 创建输出流
            FileOutputStream outputStream = new FileOutputStream(file);
            // 使用BufferWriter操作stream: 字符和字节流之间需要用OutputStreamWriter进行转换
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            String poems = "忆江南\n" +
                    "\n" +
                    "白居易 〔唐代〕\n" +
                    "江南好，风景旧曾谙。日出江花红胜火，春来江水绿如蓝。能不忆江南？ ";
            writer.write(poems, 0, poems.length() - 2);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            log.error("写文件报错:", e);
        }
    }

    /**
     * 根据文件全路径读取文件
     *
     * @param sourcePath
     */
    public static void readFile(String sourcePath) {
        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = new FileInputStream(new File(sourcePath));
            // 字节流转字符流需要转换器: InputStreamReader
            reader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            log.info("打印读出的数据");
            while (null != (line = reader.readLine())) {
                log.info("{}", line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取文件操作后写出到另一个文件中
     *
     * @param sourcePath
     * @param targetPath
     */
    public static void readAndwriteFile(String sourcePath, String targetPath) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(sourcePath))));
            String line = null;
            // 写数据到新文件中
            File file = new File(targetPath);
            if (!file.exists()) {
                file = Files.createFile(Paths.get(targetPath)).toFile();
            }
            // FileOutputStream(file, boolean): boolean为true时, 代表文件可续写
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            while (null != (line = reader.readLine())) {
                writer.println(line);
            }
            writer.flush();
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Files: 复制, 移动, 删除操作
     *
     * @throws IOException
     */
    public static void baseFileOps(String sourcePath) throws IOException {
        Path source = Paths.get(sourcePath);
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
        // 重命名
        File oldFile = new File(sourcePath);
        File newFile = Files.createFile(target).toFile();
        boolean result = oldFile.renameTo(newFile);
        log.info("更名结果:{}", result);
    }

    /**
     * 递归的方式读取文件
     *
     * @param fileDir
     * @param fileType
     * @param suffix
     * @return
     */
    public static List<File> getFiles(File fileDir, String fileType, String suffix) {
        List<File> fileList = new ArrayList<File>();
        File[] fs = fileDir.listFiles();
        for (File f : fs) {
            if (f.isFile()) {
                if (fileType.equals(f.getName().substring(f.getName().lastIndexOf(suffix) + 1,
                        f.getName().length()))) {
                    fileList.add(f);
                }
            } else {
                List<File> list = getFiles(f, fileType, suffix);
                fileList.addAll(list);
            }
        }
        return fileList;
    }

}
