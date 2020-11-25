package com.autumn.excel.bean;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author xql132@zcsmart.com
 * @date 2020/8/11 17:15
 * @description
 */
@Slf4j
public class WriteExcel {
    private static List<String> CELL_HEADS; //列头

    static {
        // 类装载时就载入指定好的列头信息，如有需要，可以考虑做成动态生成的列头
        CELL_HEADS = new ArrayList<>();
        CELL_HEADS.add("交易名称");
        CELL_HEADS.add("groupId");
        CELL_HEADS.add("oldUrl");
        CELL_HEADS.add("newUrl");
        CELL_HEADS.add("调用流程");
    }


    /**
     * 生成Excel并写入数据信息
     *
     * @param dataList 数据列表
     * @return 写入数据后的工作簿对象
     */
    public static Workbook exportData(List<ExcelDataVO> dataList, String fileName, String sheetName, boolean flag) {
        Workbook workbook = null;
        Sheet sheet = null;
        if (flag) {
            // 如果每次重写表
            // 生成xlsx的Excel
            workbook = new SXSSFWorkbook();
            sheet = buildDataSheet(workbook, sheetName, false);
        } else {
            // 如果每次在工作簿后面新建表

            try {
                workbook = new XSSFWorkbook(new FileInputStream(new File(fileName)));
            } catch (Exception e) {
                e.printStackTrace();
            }

            sheet = buildDataSheet(workbook, sheetName, true);
        }


        // 如需生成xls的Excel，请使用下面的工作簿对象，注意后续输出时文件后缀名也需更改为xls
        //Workbook workbook = new HSSFWorkbook();

        // 生成Sheet表，写入第一行的列头

        //构建每行的数据内容
        int rowNum = 1;
        for (Iterator<ExcelDataVO> it = dataList.iterator(); it.hasNext(); ) {
            ExcelDataVO data = it.next();
            if (data == null) {
                continue;
            }
            //输出行数据
            Row row = sheet.createRow(rowNum++);
            convertDataToRow(data, row, workbook);
        }
        return workbook;
    }

    /**
     * 生成sheet表，并写入第一行数据（列头）
     *
     * @param workbook 工作簿对象
     * @return 已经写入列头的Sheet
     */
    private static Sheet buildDataSheet(Workbook workbook, String sheetName,  boolean flag) {
        // 获取已有的表格数量
        int number = workbook.getNumberOfSheets();
        Sheet sheet = workbook.createSheet(sheetName);
        if (flag) {
            // sheet从0开始计数
            workbook.setSheetOrder(sheetName, number);
        }
        // 设置列头宽度
        for (int i = 0; i < CELL_HEADS.size(); i++) {
            sheet.setColumnWidth(i, 4000);
        }
        // 设置默认行高
        sheet.setDefaultRowHeight((short) 400);
        // 构建头单元格样式
        CellStyle cellStyle = buildHeadCellStyle(sheet.getWorkbook());
        // 写入第一行各列的数据
        Row head = sheet.createRow(0);
        for (int i = 0; i < CELL_HEADS.size(); i++) {
            Cell cell = head.createCell(i);
            cell.setCellValue(CELL_HEADS.get(i));
            cell.setCellStyle(cellStyle);
        }
        return sheet;
    }


    /**
     * 设置第一行列头的样式
     *
     * @param workbook 工作簿对象
     * @return 单元格样式对象
     */
    private static CellStyle buildHeadCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        //对齐方式设置
        style.setAlignment(HorizontalAlignment.LEFT);
        //边框颜色和宽度设置
//        style.setBorderBottom(BorderStyle.THIN);
//        style.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 下边框
//        style.setBorderLeft(BorderStyle.THIN);
//        style.setLeftBorderColor(IndexedColors.BLACK.getIndex()); // 左边框
//        style.setBorderRight(BorderStyle.THIN);
//        style.setRightBorderColor(IndexedColors.BLACK.getIndex()); // 右边框
//        style.setBorderTop(BorderStyle.THIN);
//        style.setTopBorderColor(IndexedColors.BLACK.getIndex()); // 上边框
        //设置背景颜色
//        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //字体和大小设置
        Font font = workbook.createFont();
        font.setFontName("Times new Roman");
        //设置字号
        font.setFontHeightInPoints((short) 16);
//        font.setBold(true);
        style.setFont(font);

        return style;
    }


    private static CellStyle buildBodyCellStyle(Workbook workbook, String fontName, int size, short color) {
        CellStyle style = workbook.createCellStyle();
        //对齐方式设置
        style.setAlignment(HorizontalAlignment.LEFT);
        //字体和大小设置
        Font font = workbook.createFont();
        font.setFontName(fontName);
        //设置字号
        font.setFontHeightInPoints((short) size);
        // 设置颜色
        font.setColor(color);
//        font.setBold(true);
        style.setFont(font);

        return style;
    }

    /**
     * 将数据转换成行
     *
     * @param data 源数据
     * @param row  行对象
     * @return
     */
    private static void convertDataToRow(ExcelDataVO data, Row row, Workbook workbook) {
        int cellNum = 0;
        Cell cell;
        // 接口名称
        cell = row.createCell(cellNum++);
        cell.setCellValue(null == data.getName() ? "" : data.getName());
        cell.setCellStyle(buildBodyCellStyle(workbook, "宋体", 14, IndexedColors.GREY_50_PERCENT.getIndex()));
        // groutId
        cell = row.createCell(cellNum++);
        cell.setCellValue(null == data.getGroupId() ? "" : data.getGroupId());
        cell.setCellStyle(buildBodyCellStyle(workbook, "Times new Roman", 14, IndexedColors.BLACK.getIndex()));
        // oldUrl
        cell = row.createCell(cellNum++);
        if (null != data.getOldUrl()) {
            cell.setCellValue(data.getOldUrl());
        } else {
            cell.setCellValue("");
        }
        cell.setCellStyle(buildBodyCellStyle(workbook, "Times new Roman", 14, IndexedColors.GREEN.getIndex()));
        // 新url: 此处存放的是新的url
        cell = row.createCell(cellNum++);
//        cell.setCellValue(getNewUrl(data.getOldUrl()));
        cell.setCellValue(data.getNewUrl());
        cell.setCellStyle(buildBodyCellStyle(workbook, "Times new Roman", 14, IndexedColors.BLACK.getIndex()));
        // 流程:
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getProcess());
        cell.setCellStyle(buildBodyCellStyle(workbook, "Times new Roman", 14, IndexedColors.BLACK.getIndex()));

    }

    /**
     * 获取新的url
     *
     * @param oldUrl
     * @return
     */
    public static String getNewUrl(String oldUrl) {
        if (oldUrl.isEmpty()) {
            return "";
        }
        // 未更新的url以"i/s"开头, 已更新的url以v1开头
        if (oldUrl.startsWith("i/s")) {
            return oldUrl.replace("v1/", "").replace("i/s", "v1");
        } else {
            return oldUrl;
        }
    }

}
