package com.autumn.file;

import java.io.*;
import java.util.Map;
import java.util.Set;

/**
 * @author xql132@zcsmart.com
 * @date 2020/8/12 11:42
 * @description
 */
public class Modify {

    /**
     * @param contPath 操作目录。从该目录开始。该文件目录下及其所有子目录的文件都将被替换。
     * @param data     key:需要被替换、改写的内容。value:需要新写入的内容。
     */
    public static void operation(String contPath, Map<String, String> data) {
        File file = new File(contPath);
        opeationDirectory(file, data);
    }

    public static void opeationDirectory(File dir, Map<String, String> data) {
        if (dir.isFile()) {
            operationFile(dir, data);
        } else {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                if (f.isDirectory()) {
                    // 如果是目录，则递归。
                    opeationDirectory(f, data);
                }
                if (f.isFile()) {
                    operationFile(f, data);
                }
            }
        }
    }

    public static void operationFile(File file, Map<String, String> data) {

        try {
            InputStream is = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is));

            String filename = file.getName();
            // tmpfile为缓存文件，代码运行完毕后此文件将重命名为源文件名字。
            File tmpfile = new File(file.getParentFile().getAbsolutePath()
                    + "\\" + filename + ".tmp");

            BufferedWriter writer = new BufferedWriter(new FileWriter(tmpfile));


            Set<String> keySet = data.keySet();
            while (true) {
                final String str = reader.readLine();

                if (str == null) {
                    break;
                }
                // 是否无匹配
                boolean flag = true;
                for (String key : keySet) {
                    if (str.contains(key)) {
                        String newStr = str.replace(key, data.get(key));
                        writer.write(newStr + "\n");
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    writer.write(str + "\n");
                }
            }

            is.close();

            writer.flush();
            writer.close();
            file.delete();
            tmpfile.renameTo(new File(file.getAbsolutePath()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
