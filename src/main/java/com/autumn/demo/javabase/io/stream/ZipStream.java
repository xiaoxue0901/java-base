package com.autumn.demo.javabase.io.stream;

import com.autumn.demo.javabase.constant.BaseConsts;

import java.io.*;
import java.util.Scanner;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/15
 * @time 13:40
 * @description ZIP文档
 * ZIP 文档（通常）以压缩格式存储了一个或多个文件，每个 ZIP 文档都有一个头，包含诸 如每个文件名字和所使用的压缩方法等信息。
 * 在 Java 中，可以使用 ZipInputStream 来读 入 ZIP 文档。
 */
public class ZipStream {
    private static final String ABS_FILE = BaseConsts.PATH_EMPLOYEE + File.separator + "employee.dat";

    /**
     * 读取ZIP文件
     */
    public static void useZipStream()  {
        try {
            // 读ZIP文件
            ZipInputStream zin = new ZipInputStream(new FileInputStream(ABS_FILE));
            // 要浏览文档中每个单独的项
            ZipEntry entry;
            // getNextEntry 方法就可以返回一个描 述这些项的 ZipEntry 类型的对象。
            while ((entry = zin.getNextEntry()) != null) {
                String comment = entry.getComment();
                //返回这一项的名字。
                String name = entry.getName();
                // 返回用于这个 ZipEntry 的 CRC32 校验和的值
                long crc = entry.getCrc();
                // 返回这一项未压缩的尺寸，或者在未压缩的尺寸不可知的情况下返回－ 1。
                long size = entry.getSize();
                // 当这一项是目录时返回true
                boolean flag = entry.isDirectory();

                // 必须调用 closeEntry 来读入下一 项。
                zin.closeEntry();
            }
            //读入 ZIP 文件内部的一个文本文件
            Scanner scanner = new Scanner(zin);
            while (scanner.hasNextLine()) {
                // 读取文件
                String data = scanner.nextLine();
            }
            zin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 以使用 ZipOutputStream写出到ZIP文件
     * @throws IOException
     */
    public static void useWriteZip() throws IOException {
        FileOutputStream fout = new FileOutputStream("test.zip");
        ZipOutputStream zoup = new ZipOutputStream(fout);
        // 设置压缩级别
        zoup.setLevel(Deflater.DEFAULT_COMPRESSION);
        // 设置压缩方法
        zoup.setMethod(ZipOutputStream.DEFLATED);
        for(int i = 0; i<3; i++) {
            // 放入到 ZIP 文件中的 每一项，都应该创建一个 ZipEntry 对象，并将文件名传递给 ZipEntry 的构造器，它将设 置其他诸如文件日期和解压缩方法等参数。
            ZipEntry entry = new ZipEntry("filename"+i);
            // ：method 用于这一项的压缩方法，必须是 DEFLATED 或 STORED
            entry.setMethod(ZipEntry.STORED);
            // 设置这一项的尺寸, 只有压缩方式是STORED时才是必须的
            entry.setSize(10);
            // 设置CRC32校验和. 压缩方法= STORED时用到
            entry.setCrc(9848755388L);
            // 调用 ZipOutputStream 的 putNextEntry 方法来开始写出新文件，并将文件数据发送到 ZIP 流中。
            zoup.putNextEntry(entry);
            // send data to zout
            zoup.closeEntry();
        }
        zoup.close();

    }
}
