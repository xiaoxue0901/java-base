package com.autumn.demo.javabase.io.stream;

import com.autumn.demo.javabase.bean.Employee;
import com.autumn.demo.javabase.constant.BaseConsts;
import com.autumn.demo.javabase.designpattern.demo02_adapter.Adapter.Sample1.Print;

import java.io.*;
import java.util.Date;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/11
 * @time 20:33
 * @description 文本输入与输出
 * 规律: 输入流: 字节->Unicode字符; 输出流: 字符->字节;
 * 对流来说都是处理字节的, 加上Reader和Writer后可以在字节和字符间转换
 */
public class TextStream {
    private static String ABS_FILE = BaseConsts.PATH_EMPLOYEE + File.separator + "employee.dat";

    /**
     * 获取系统的行结束符
     *
     * @return
     */
    public static String getEndSymbol() {
        // 对目标系统来说恰当的行结束符（Windows 系统是 "\r\n"，UNIX 系统是 "\n"），
        String end = System.getProperty("line.separator");
        return end;
    }

    /**
     * 1. 如何写出文本输出: 使用PrintWriter
     *
     * @return
     */
    public static PrintWriter constructPrint() {
        System.out.println("文件地址:{}" + ABS_FILE);
        // PrintWriter: 这个类拥有以文本格式打印字符串和数字的方 法
        try {
            // 方式1:
            // PrintWriter printWriter = new PrintWriter(ABS_FILE);
            // 方式2: 输出到打印器, 最终输出到ABS_FILE文件中
            PrintWriter printWriter1 = new PrintWriter(new FileWriter(ABS_FILE));
            // 要输出到ABS_FILE中的内容
            printWriter1.print("Harry Hacker2");
            printWriter1.print(' ');
            printWriter1.println(5000);

            // 方式3: 设置自动冲刷模式
            // new PrintWriter(Writer out, Boolean autoFlush): autoFlush:是否开启自动冲刷器; true: 开启
            // 将Harry Hacker 4000写入到ABS_FILE文件中
            // PrintWriter printWriter2 = new PrintWriter(new FileWriter(ABS_FILE), true);
            printWriter1.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 2. PrintWriter的使用
     * @throws IOException
     */
    public static void userPrintWriter() throws IOException {
        // 构造器
        // 1.PrintWriter(Writer out)
        PrintWriter pw1 = new PrintWriter(new FileWriter(ABS_FILE));
        // 2.PrintWriter(Writer out, boolean autoFlush)
        PrintWriter pw2 = new PrintWriter(new FileWriter(ABS_FILE), true);
        // 3.PrintWriter(OutputStream out)
        PrintWriter pw3 = new PrintWriter(new FileOutputStream(ABS_FILE));
        // 4.PrintWriter(OutputStream out, boolean autoFlush)
        PrintWriter pw4 = new PrintWriter(new FileOutputStream(ABS_FILE), true);
        // 5. PrintWriter(String filename)
        PrintWriter pw5 = new PrintWriter(ABS_FILE);
        // 6. PrintWriter(File file)
        PrintWriter pw6 = new PrintWriter(new File(ABS_FILE));
        // PrintWriter的使用
        // 打印对象
        pw1.print(new Employee("嬴政", 4000000, new Date()));
        // 打印一个包含 Unicode 码元的字符串。
        pw1.print("秦始皇");
        // 打印一个字符串，后面紧跟一个行终止符。如果这个流处于自动冲刷模式，那么就会 冲刷这个流。
        pw1.println("秦始皇的出生日期");
        // 打印在给定的字符串中的所有 Unicode 码元。
        pw1.print(new char[]{'金', '光','闪', '闪'});
        // 打印一个 Unicode 码元。
        pw1.print('年');
        // 打印基本类型
        // int
        pw1.print(1);
        // long
        pw1.print(2L);
        // float
        pw1.print(1.2f);
        // double
        pw1.print(1.45d);
        // boolean
        pw1.print(true);
        // 按照格式字符串指定的方式打印给定的值。请查看第Ⅰ卷第 3 章以了解格式化字符串 的相关规范。
        pw1.printf("%010d", 50);
        pw1.close();
    }

    /**
     * 1. 文本输出: OutputStreamWriter
     *
     * @return
     */
    public OutputStreamWriter constructOsw() {
        // OutputStreamWriter: 将使用选定的字符编码方式，把 Unicode 字符流转换为字节 流。
        return null;
    }

    /**
     * 2. 文本输入:InputStreamReader
     *
     * @return
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public InputStreamReader constructIsr() throws FileNotFoundException, UnsupportedEncodingException {

        // InputStreamReader: 将包含字节（用某种字符编码方式表示的字符）的输入流转 换为可以产生 Unicode 码元的读入器。
        // 1. 从控制台读入键盘敲击信息，并 将其转换为 Unicode (假设控制台编码: ISO-8859-1)
        InputStreamReader isr = new InputStreamReader(System.in);
        // 2. 指定字符编码
        InputStreamReader isr2 = new InputStreamReader(new FileInputStream(ABS_FILE), "ISO8859_5");
        return isr2;
    }




}
