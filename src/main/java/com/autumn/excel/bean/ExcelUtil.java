package com.autumn.excel.bean;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author xql132@zcsmart.com
 * @date 2020/8/11 18:14
 * @description
 */
@Slf4j
public class ExcelUtil {

    /**
     * 读取接口文件
     * @param data
     * @param absFileName
     */
    public static void write(List<ExcelDataVO> data, String absFileName, String moudleName, boolean flag) {
        // 写入数据到工作簿对象内
        Workbook workbook = WriteExcel.exportData(data,absFileName, moudleName, flag);

        // 以文件的形式输出工作簿对象
        FileOutputStream fileOut = null;
        try {
            File exportFile = new File(absFileName);
            if (!exportFile.exists()) {
                exportFile.createNewFile();
            }

            fileOut = new FileOutputStream(absFileName);
            workbook.write(fileOut);
            fileOut.flush();
        } catch (Exception e) {
            log.warn("输出Excel时发生错误，错误原因：" + e.getMessage());
        } finally {
            try {
                if (null != fileOut) {
                    fileOut.close();
                }
                if (null != workbook) {
                    workbook.close();
                }
            } catch (IOException e) {
                log.warn("关闭输出流时发生错误，错误原因：" + e.getMessage());
            }
        }
    }
}
