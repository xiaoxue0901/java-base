package com.autumn.demo.javabase.io;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
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
