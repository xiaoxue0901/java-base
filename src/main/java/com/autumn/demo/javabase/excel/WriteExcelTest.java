package com.autumn.demo.javabase.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xql132@zcsmart.com
 * @date 2020/8/11 17:24
 * @description
 */
@Slf4j
public class WriteExcelTest {
    public static void main(String[] args) {
        List<ExcelDataVO> data = new ArrayList<>();
        ExcelDataVO vo1 = new ExcelDataVO();
        vo1.setName("接口1");
        vo1.setGroupId("cardserver");
        vo1.setOldUrl("old/v1/v3");
        vo1.setNewUrl("");
        ExcelDataVO vo2 = new ExcelDataVO();
        vo2.setName("接口2");
        vo2.setGroupId("cardserver");
        vo2.setOldUrl("old/v2/v3");
        vo2.setNewUrl("");
        data.add(vo1);
        data.add(vo2);
        // 写入数据到工作簿对象内
        Workbook workbook = WriteExcel.exportData(data, "lgm-cardserver");

        // 以文件的形式输出工作簿对象
        FileOutputStream fileOut = null;
        try {
            String exportFilePath = "C:\\Users\\xql\\Desktop\\20200720接口url统一更改\\测试.xlsx";
            File exportFile = new File(exportFilePath);
            if (!exportFile.exists()) {
                exportFile.createNewFile();
            }

            fileOut = new FileOutputStream(exportFilePath);
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
