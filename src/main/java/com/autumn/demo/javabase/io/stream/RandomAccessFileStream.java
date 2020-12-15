package com.autumn.demo.javabase.io.stream;

import com.autumn.demo.javabase.constant.BaseConsts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/15
 * @time 13:20
 * @description 随机访问文件: RandomAccessFile:可以在文件中的任何位置查找或写入数据。
 */
public class RandomAccessFileStream {
    private static final String ABS_FILE = BaseConsts.PATH_EMPLOYEE + File.separator + "employee.dat";

    /**
     * 构造RandomAccessFile对象的方式
     * @throws FileNotFoundException
     */
    public static void constructRaf() throws FileNotFoundException {
        // RandomAccessFile(String fileName, String mode)
        // r: 只读模式
        RandomAccessFile rafr = new RandomAccessFile(ABS_FILE, "r");
        // rw: 读/写模式
        RandomAccessFile rafrw = new RandomAccessFile(ABS_FILE, "rw");
        // rws: “ rws”表示每次更新 时，都对数据和元数据的写磁盘操作进行同步的读 / 写模式；
        RandomAccessFile rafrws = new RandomAccessFile(ABS_FILE, "rws");
        // “ rwd” 表示每次更新时，只对数据的写磁盘操作进行同步的读 / 写模式
        RandomAccessFile rafrwd = new RandomAccessFile(ABS_FILE, "rwd");
        // RandomAccessFile(File file, String mode)
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File(ABS_FILE), "r");
    }

    /**
     * RandomAccessFile的使用
     * @throws IOException
     */
    public static void useRandomAccessFile() throws IOException {
        // 1. 构造对象
        RandomAccessFile raf = new RandomAccessFile(new File(ABS_FILE), "rw");
        // 2. getFilePointer 方法将返回文件指针的当前位置
        long pointer = raf.getFilePointer();
        // 3. seek(): 随机访问文件有一个表示下一个将被读入或写出的字节所处位置的文件指针，s可以将这个文件指针设置到文件中的任意字节位置，seek 的参数是一个 long 类型的整数， 它的值位于 0 到文件按照字节来度量的长度之间。
        raf.seek(4);
        // 4. 实现了DataOutput和DataInput, 可以使用writeInt/readInt等方法
        int a = raf.readInt();
        // 5. 返回文件按照字节来度量的长度
        long len = raf.length();
    }
}
