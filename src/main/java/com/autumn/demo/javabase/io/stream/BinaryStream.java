package com.autumn.demo.javabase.io.stream;

import com.autumn.demo.javabase.constant.BaseConsts;

import java.io.*;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/14
 * @time 13:36
 * @description 读写二进制数据
 */
public class BinaryStream {

    private static String ABS_FILE = BaseConsts.PATH_EMPLOYEE + File.separator + "employee.dat";

    /**
     * 使用DataOutputStream:  出 二 进 制 数 据
     */
    public static void useDataOutputStream() throws IOException {
        // 构造对象
        DataOutputStream dop = new DataOutputStream(new FileOutputStream(ABS_FILE));
        // 使用: 可以写入基本类型数据+字符串
        dop.writeBoolean(true);
        dop.writeByte((byte) 3);
        dop.writeDouble(3.45D);
        dop.writeInt(1);
        dop.writeLong(4L);
        dop.writeFloat(3.12f);
        dop.writeChar('你');
        dop.writeShort((short) 8);
        dop.writeChars("天气好");
    }

    /**
     * 使用DataInputStream: 从文件中读入二进制数据
     */
    public static void useDataInputStream() throws IOException {
        // 构造对象
        DataInputStream dip = new DataInputStream(new FileInputStream(ABS_FILE));
        // 方法使用
        boolean boolValue = dip.readBoolean();
        byte byteValue = dip.readByte();
        double doubleValue = dip.readDouble();
        int intValue = dip.readInt();
        long longValue = dip.readLong();
        float floatValue = dip.readFloat();
        char charValue = dip.readChar();
        short shortValue = dip.readShort();
    }
}
