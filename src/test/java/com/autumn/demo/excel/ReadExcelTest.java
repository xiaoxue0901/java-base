package com.autumn.demo.excel;

import com.autumn.demo.excel.bean.ExcelDataVO;
import com.autumn.demo.excel.util.ReadExcel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

/**
 * @author xql132@zcsmart.com
 * @date 2020/8/12 14:25
 * @description
 */
@Slf4j
public class ReadExcelTest {

    @Test
    public void readExcel() {
        // 设定Excel文件所在路径
        String excelFileName = "C:\\Users\\xql\\Desktop\\20200720接口url统一更改\\url统计表.xlsx";
        // 读取Excel文件内容
        List<ExcelDataVO> readResult = ReadExcel.readExcel(excelFileName, "lgm-cardserver");
        readResult.forEach(e -> {
            log.info("文件内容:oldUrl={}, newUrl = {}", e.getOldUrl(), e.getNewUrl());
        });
    }
}