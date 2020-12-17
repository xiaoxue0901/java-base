package com.autumn.demo.excel.util;

import com.autumn.demo.excel.bean.ExcelDataVO;
import com.autumn.demo.excel.constant.ExcelConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xql132@zcsmart.com
 * @date 2020/8/12 14:17
 * @description
 */
@Slf4j
public class ReadExcel {

    /**
     * 入口:读取Excel文件内容
     *
     * @param fileName  要读取的Excel文件所在路径(文件全路径)
     * @param sheetName 工作薄名称: 例如:Sheet1
     * @return
     */
    public static List<ExcelDataVO> readExcel(String fileName, String sheetName) {
        Workbook workbook = null;
        FileInputStream inputStream = null;
        try {
            // 获取Excel后缀名
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            // 获取Excel文件
            File excelFile = new File(fileName);
            if (!excelFile.exists()) {
                log.info("指定的Excel文件不存在！");
                return null;
            }

            // 获取Excel工作簿的文件流
            inputStream = new FileInputStream(excelFile);
            // 根据文件后缀名类型获取对应的工作簿对象
            workbook = getWorkbook(inputStream, fileType);
            // 读取excel中的数据
            return parseExcel(workbook, sheetName);
        } catch (Exception e) {
            log.error("解析Excel失败, 文件名:{}, 错误信息:", fileName, e);
            return null;
        } finally {
            try {
                if (null != workbook) {
                    workbook.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {
                log.error("关闭数据流出错！错误信息：", e);
            }
        }
    }

    /**
     * 根据文件后缀名类型获取对应的工作簿对象
     *
     * @param inputStream 读取文件的输入流
     * @param fileType    文件后缀名类型（xls或xlsx）
     * @return 包含文件数据的工作簿对象
     * @throws IOException
     */
    public static Workbook getWorkbook(InputStream inputStream, String fileType) throws IOException {
        Workbook workbook = null;
        if (fileType.equalsIgnoreCase(ExcelConst.XLS)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (fileType.equalsIgnoreCase(ExcelConst.XLSX)) {
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }


    /**
     * 解析Excel数据
     *
     * @param workbook Excel工作簿对象
     * @return 解析结果
     */
    private static List<ExcelDataVO> parseExcel(Workbook workbook, String sheetName) {
        List<ExcelDataVO> resultDataList = new ArrayList<>();
        // 按顺序解析sheet
//        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
//         Sheet sheet = workbook.getSheetAt(sheetNum);
//        }
        // 按sheet名称解析
        Sheet sheet = workbook.getSheet(sheetName);
        // 校验sheet是否合法
        if (sheet == null) {
            log.info("表格:{}没有数据", sheetName);
            return null;
        }

        // 获取第一行数据:第一行认为是表头
        int firstRowNum = sheet.getFirstRowNum();
        Row firstRow = sheet.getRow(firstRowNum);
        if (null == firstRow) {
            log.info("解析Excel失败，在第一行没有读取到任何数据！");
        }

        // 解析每一行的数据，构造数据对象
        int rowStart = firstRowNum + 1;
        // 结束行
        int rowEnd = sheet.getPhysicalNumberOfRows();
        for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (null == row) {
                continue;
            }
            // 此处可改为根据不同的表格内容定义不同的对象.
            ExcelDataVO resultData = convertRowToData(row);
            if (null == resultData) {
                log.info("第 " + row.getRowNum() + "行数据不合法，已忽略！");
                continue;
            }
            resultDataList.add(resultData);
        }
        return resultDataList;
    }

    /**
     * 将单元格内容转换为字符串
     *
     * @param cell
     * @return
     */
    private static String convertCellValueToString(Cell cell) {
        if (cell == null) {
            return null;
        }
        String returnValue = null;
        switch (cell.getCellType()) {
            case NUMERIC:
                //数字
                Double doubleValue = cell.getNumericCellValue();

                // 格式化科学计数法，取一位整数
                DecimalFormat df = new DecimalFormat("0");
                returnValue = df.format(doubleValue);
                break;
            case STRING:
                //字符串
                returnValue = cell.getStringCellValue();
                break;
            case BOOLEAN:
                //布尔
                Boolean booleanValue = cell.getBooleanCellValue();
                returnValue = booleanValue.toString();
                break;
            case BLANK:
                // 空值
                break;
            case FORMULA:
                // 公式
                returnValue = cell.getCellFormula();
                break;
            case ERROR:
                // 故障
                break;
            default:
                break;
        }
        return returnValue;
    }

    /**
     * 提取每一行中需要的数据，构造成为一个结果数据对象
     * <p>
     * 当该行中有单元格的数据为空或不合法时，忽略该行的数据
     *
     * @param row 行数据
     * @return 解析后的行数据对象，行数据错误时返回null
     */
    private static ExcelDataVO convertRowToData(Row row) {
        ExcelDataVO resultData = new ExcelDataVO();

        Cell cell;
        int cellNum = 0;
        // 获取接口名称
        cell = row.getCell(cellNum++);
        String name = convertCellValueToString(cell);
        resultData.setName(name);
        // 获取groupId
        cell = row.getCell(cellNum++);
        String groupId = convertCellValueToString(cell);
        if (null == groupId || "".equals(groupId)) {
            // groupId为空
            resultData.setGroupId(null);
        } else {
            resultData.setGroupId(groupId);
        }
        // 获取oldUrl
        cell = row.getCell(cellNum++);
        String oldUrl = convertCellValueToString(cell);
        resultData.setOldUrl(oldUrl);
        // 获取newUrl
        cell = row.getCell(cellNum++);
        String newUrl = convertCellValueToString(cell);
        resultData.setNewUrl(newUrl);

        return resultData;
    }

}
