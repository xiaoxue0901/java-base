package com.autumn.demo.javabase.io.stream;

import java.io.*;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/16
 * @time 20:11
 * @description 深度拷贝
 */
public class SerialClone implements Cloneable, Serializable {

    /**
     * 使用ByteArrayOutputStream将数据保存到字节数组中
     * @return
     */
    @Override
    protected Object clone()  {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try {
            ObjectOutputStream out = new ObjectOutputStream(bout);
            out.writeObject(this);
            out.close();

            ByteArrayInputStream bis = new ByteArrayInputStream(bout.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            Object res = ois.readObject();
            ois.close();
            bout.close();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
