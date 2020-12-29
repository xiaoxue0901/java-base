package com.autumn.demo.netty5.chapter2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/****
 * 读取资源文件信息
 * @author xieqin
 * @date 2014-09-12
 * @versions 1.0
 */
public class PropertiesUtil {
    /***
     * 根据资源文件存放在src目录下的路径获取文件信息
     * @param propPath:资源文件目录下的路径
     * @return
     */
    public  static Properties getProperties(String propPath){
           Properties props = new Properties();
        try {
//            logger.info("begin to load properties file");
            File file = new File(propPath);
            if(!file.exists()) {
                throw new FileNotFoundException("prop file not found !");
            }
            FileInputStream fin = new FileInputStream(new File(propPath));
             props.load(fin);
        }catch (IOException e) {
            e.printStackTrace();
        }
       return props;
     }
    
}
