package com.autumn.demo.javabase.io.stream;

import com.autumn.demo.javabase.constant.BaseConsts;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.zip.ZipInputStream;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/11
 * @time 13:12
 * @description
 */
@Slf4j
public class Stream {
    // 1.流:
    // 输入流:InputStream
    // 输出流: OutputStream

    /**
     * 1. 获取用户工作目录
     */
    public static String getUserDir() {
        // 所有在 java.io 中的类都将相对路径名解释为以用户工作目录开始
        String userDir = System.getProperty("user.dir");
        return userDir;
    }
}

/**
 * 组合流过滤器
 * 这种混合并 匹配过滤器类以构建真正有用的流序列的能力，将带来极大的灵活性，
 */
@Slf4j
class CombinedStreamFilter {

    /**
     * 1.通过文件名构造FileInputStream对象
     *
     * @param name
     * @return
     */
    public FileInputStream constructFileInputStreamByName(String name) {
        try {
            // 非绝对的路径名将按照相对于 VM 启动时所设置的工作目录来解析。
            // name如果是相对路径, 则在usr.dir路径下创建Stream对象
            return new FileInputStream(name);
        } catch (FileNotFoundException e) {
            log.error("constructFileInputStreamByName FileNotFoundException", e);
        }
        return null;
    }

    /**
     * 2.通过文件构造FileInputStream对象
     *
     * @param file
     * @return
     */
    public FileInputStream constructFileInputStreamByFile(File file) {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            log.error("constructFileInputStreamByName FileNotFoundException", e);
        }
        return null;
    }

    /**
     * 3. 组合inputStream
     *
     * @throws FileNotFoundException
     */
    public void combineInputStream() throws IOException {
        String absFile = BaseConsts.PATH_EMPLOYEE + File.separator + "employee.dat";
        // 1. 使用文件路径构造FileInputStream实例对象: 分隔符使用File.separator
        FileInputStream fileInputStream = constructFileInputStreamByName(absFile);
        // 2. FileInputStream只支持字节和字节数组的读取
        // read(): 读取一个字节, 返回读取的字节, 如果到了流的末尾,返回-1
        byte b = (byte) fileInputStream.read();
        byte[] fileBytes = new byte[1024];
        // read(byte[] b): 读取n个字节
        fileInputStream.read(fileBytes);
        // read(byte[] b, int off, int len)
        fileInputStream.read(fileBytes, 1, 20);

        // 某些流只能读取字节(FileInputStream), 某些流可以将字节组装到更有用的数据类型中(DataInputStream和PrintWriter)
        // 对不同的流进行组合: new DataInputStream(fileInputStream), 可以直接从文件中读取到double类型的数据
        // 3.DataInputStream: 没有从文件获取数据的方法
        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        // 读取数值类型
        double s = dataInputStream.readDouble();

        // 4.嵌套过滤器
        // 给DataInputStream添加缓冲功能, 嵌套BufferedInputStream
        // 把 DataInputStream 置 于 构 造 器 链 的 最 后， 这 是 因 为 我 们 希 望 使 用
        // DataInputStream 的方法，并且希望它们能够使用带缓冲机制的 read 方法。
        DataInputStream din = new DataInputStream(new BufferedInputStream(new FileInputStream(absFile)));

        // 5. 跟踪各个中介流
        PushbackInputStream pushbackInputStream = new PushbackInputStream(new BufferedInputStream(
                new FileInputStream(absFile)
        ));
        // 预读下一字节
        int pb = pushbackInputStream.read();
        // pb并非你所期望的值时将其推回流中。
        if (pb != BaseConsts.EXPECT_VALUE) {
            pushbackInputStream.unread(pb);
        }

        // 6. 希望能够 预先浏览并且还可以读入数字还带缓冲功能: 需要一个既是可回推输入流，又是一个数据输入流 的引用。
        DataInputStream dpStream = new DataInputStream(new PushbackInputStream(new BufferedInputStream(new FileInputStream(absFile))));
        int dpb = dpStream.read();
        if (dpb == BaseConsts.EXPECT_VALUE){
            int dpbNumber = dpStream.readInt();
        }

        // 7. 从一个 ZIP 压缩文件中通过使用下面的流序列来读入数字
        ZipInputStream zin = new ZipInputStream(new FileInputStream(absFile));
        // 组合流
        DataInputStream zdip = new DataInputStream(zin);
        // 使用DataInputStream读数字
        float fNumber = zdip.readFloat();
    }


    /**
     * 文件输出流: 构造FileOutputStream对象
     * @return
     */
    public FileOutputStream constructFileOutputStream() {
        String absFile = BaseConsts.PATH_EMPLOYEE + File.separator + "employee.dat";
        // 文件输出流对象
        FileOutputStream fileOutputStream = null;
        try {
            // 方式1: FileOutputStream(String name);
            fileOutputStream = new FileOutputStream(absFile);
            // 方式2: new FileOutputStream(String name, boolean append) => append: true:续写; false: 覆盖原文件,重写
            fileOutputStream = new FileOutputStream(absFile, true);
            // 方式3: 默认覆盖原文件
            fileOutputStream = new FileOutputStream(new File(absFile));
            // 方式4:
            fileOutputStream = new FileOutputStream(new File(absFile), true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileOutputStream;
    }

    /**
     * 缓冲输入流
     * @param stream
     * @return
     */
    public BufferedInputStream constructBufferedInputStream(InputStream stream) {
        BufferedInputStream bis = new BufferedInputStream(stream);
        return bis;
    }

    /**
     * 缓冲输出流
     * @param stream
     * @return
     */
    public BufferedOutputStream constructBufferedOutputStream(OutputStream stream) {
        BufferedOutputStream bos = new BufferedOutputStream(stream);
        return bos;
    }

    /**
     * 可回推输入流
     * @param stream
     * @return
     */
    public PushbackInputStream constructPushbackInputStream(InputStream stream) {
        PushbackInputStream pis = new PushbackInputStream(stream);
        try {
            // 用法:
            // 1. 预读
            byte b = (byte) pis.read();
            // 不满足条件, 回推
            if (b != BaseConsts.EXPECT_VALUE) {
                pis.unread(b);
            }
        } catch (IOException e) {
            log.error("读取异常", e);
        }
        return pis;
    }
}
