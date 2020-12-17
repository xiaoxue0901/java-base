package com.autumn.demo.excel;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author xql132@zcsmart.com
 * @date 2020/8/12 9:52
 * @description
 */
@Slf4j
public class WriteUrlTest {

    @Test
    public void testWriteUrl() {
        // controller的目录地址
        String controllerPath="D:\\workspace\\gitlab\\switch\\lgm-cardserver\\src\\main\\java\\com\\zcs\\cardmaker\\business\\controller\\";
        // 编译后的class文件地址
        String targetPath = "D:\\workspace\\gitlab\\switch\\lgm-cardserver\\target\\classes";
        // 记录url的表格地址
        String excelPath = "C:\\Users\\xql\\Desktop\\20200720接口url统一更改\\url统计表.xlsx";
        //proto文件的地址
        String protoPath ="D:\\workspace\\gitlab\\switch\\lgm-cardserver\\src\\main\\proto";
        // sheetName: 工作薄名称
        String sheetName = "lgm-cardserver";
        // 读取controller的老url, 并设置新url到表格中, 工作薄是追加到sheet表后面的
        ZcsUrlUtil.readAndWriteNewExcel(controllerPath, targetPath, excelPath, sheetName, "cardserver");
        // 读取controller的老url,并设置新url到表格中,会创建新的工作薄并覆盖之前的内容和sheet表
//        ZcsUrlUtil.readAndWriteNewExcel(controllerPath, targetPath, excelPath, sheetName, "cardserver");
        // 读取表格中的数据, 更新controller和proto文件中的url
        ZcsUrlUtil.readExcelAndModifyFile(excelPath, controllerPath, protoPath, sheetName);
    }


}
