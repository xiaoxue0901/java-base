package com.autumn.demo.javabase.iostream;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xql132@zcsmart.com
 * @date 2018/11/13 17:41
 * @description
 */
public class FileUtils {

    /**
     * 递归的方式读取文件
     *
     * @param fileDir
     * @param fileType
     * @param suffix
     * @return
     */
    public List<File> getFiles(File fileDir, String fileType, String suffix) {
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

    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtils();
        URL xmlpath = Thread.currentThread().getContextClassLoader().getResource("");
        File file = new File(xmlpath.getPath());
        List<File> files = fileUtils.getFiles(file, ".brmn.xml", ".brmn.xml");
    }


}
