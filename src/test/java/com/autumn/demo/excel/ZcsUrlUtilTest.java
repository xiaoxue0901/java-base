package com.autumn.demo.excel;

import com.autumn.demo.excel.util.WriteExcel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xql132@zcsmart.com
 * @date 2020/11/26
 * @time 14:06
 * @description
 */
@Slf4j
public class ZcsUrlUtilTest {

    @Test
    public void readOldUrl() {
    }

    @Test
    public void modifyUrl() {
        String sourcePath = "D:\\workspace\\gitlab\\excel-use\\src\\main\\proto";
        Map<String, String> data = new HashMap<>();
        data.put("i/s/recharge/v1/quotaRechApply", "v1/recharge/quotaRechCheck");
        data.put("i/s/recharge/v1/quotaRechCheck", "v1/recharge/quotaRechApply");
        ZcsUrlUtil.modifyUrl(sourcePath, data);
    }

    @Test
    public void getUrl() {
        log.info("新url: {}", WriteExcel.getNewUrl("i/s/recharge/v1/quotaRechApply"));
    }

    @Test
    public void getFile() {
        List<String> fileNames = new ArrayList<>();
        List<String> result = ZcsUrlUtil.getFile(new File("D:\\workspace\\gitlab\\excel-use\\src\\main\\java\\com\\zcs"), fileNames, "");
        result.forEach(System.out::println);
    }

    @Test
    public void readExcelAndRewrite() {
        String excelPath = "C:\\Users\\xql\\Desktop\\20200720接口url统一更改\\交换接口范围.xlsx";
        String sheetName = "lgm-pay1";
        String copySheetName = "lgm-sdzw";
        String newSheetNme = "lgm-sdzw1";
        ZcsUrlUtil.readExcelAndRewrite(excelPath, sheetName, copySheetName, newSheetNme);
    }
}