package com.autumn.demo.excel;


import com.autumn.demo.excel.util.CompareExcel;
import com.autumn.demo.excel.bean.ExcelDataVO;
import com.autumn.demo.excel.util.ExcelUtil;
import com.autumn.demo.excel.util.ReadExcel;
import com.autumn.file.Modify;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xql132@zcsmart.com
 * @date 2020/8/12 9:47
 * @description
 */
@Slf4j
public class ZcsUrlUtil {

    /**
     * 读取文档的注释和url
     * @param controllerPath controller注解所在类地址
     * @param targetPath controller注解所在类编译后地址
     * @param groupId
     * @return
     */
    public static List<ExcelDataVO> readOldUrl(String controllerPath, String targetPath, String groupId) {
        // 读取某个包下的controller文件
        File file = new File(controllerPath);
        List<String> fileNames = new ArrayList<>();
        getFile(file, fileNames, "");
        List<ExcelDataVO> total = new ArrayList<>();
        for (String f : fileNames) {
            log.info("要读取注释和url的文件名:{}", f);

            String source = controllerPath.concat(f);
            com.sun.tools.javadoc.Main.execute(new String[]{"-doclet",
                    JavaDocUse.Doclet.class.getName(),
                    "-encoding", "utf-8",
                    "-classpath", targetPath,
                    source
            });
            total.addAll(JavaDocUse.show(f, controllerPath, groupId));
        }

        return total;
    }

    /**
     * 将接口信息写入到新的Excel表中
     * @param controllerPath
     * @param targetPath
     * @param excelPath
     * @param moudleName
     * @param groupId
     */
    public static void readAndWriteNewExcel(String controllerPath, String targetPath, String excelPath, String moudleName, String groupId) {
        List<ExcelDataVO> total = readOldUrl(controllerPath, targetPath, groupId);
        ExcelUtil.write(total, excelPath, moudleName, true);
        log.info("老url读取到excel表完毕");

    }

    /**
     * 将接口信息写入到已存在的Excel表中
     * @param controllerPath
     * @param targetPath
     * @param excelPath
     * @param moudleName
     * @param groupId
     */
    public static void readAndWriteOldExcel(String controllerPath, String targetPath, String excelPath, String moudleName, String groupId) {
        List<ExcelDataVO> total = readOldUrl(controllerPath, targetPath, groupId);
        ExcelUtil.write(total, excelPath, moudleName, false);
        log.info("老url读取到excel表完毕");
    }

    /**
     * 获取文件名
     * @param root
     * @return
     */
    public static List<String> getFile(File root, List<String> fileNames, String parentName) {
//        List<String> fileNames = new ArrayList<>();
        // 获取文件下的所有文件: 包括目录
        File[] files = root.listFiles();
        for (File file:files) {
            if (file.isDirectory()) {
                String dirName = file.getName();
                getFile(file, fileNames, dirName.concat("/"));
            } else {
                fileNames.add(parentName.concat(file.getName()));
            }
        }
        return fileNames;
    }


    /**
     * 更新url
     * @param sourcePath
     * @param data
     */
    public static void modifyUrl(String sourcePath, Map<String, String> data) {
        //代码测试：假设有一个test文件夹，test文件夹下含有若干文件或者若干子目录，子目录下可能也含有若干文件或者若干子目录（意味着可以递归操作）。
        //把test目录下以及所有子目录下（如果有）中文件含有"hi"的字符串行替换成新的"hello,world!"字符串行。
        Modify.operation(sourcePath, data);
    }

    /**
     * 读取表格并更新指定的controller和proto文件
     * @param excelPath
     * @param contPath
     * @param protoPath
     */
    public static void readExcelAndModifyFile(String excelPath, String contPath,String protoPath, String sheetName) {
        //读取excel表格数据
        List<ExcelDataVO> excelDatas = ReadExcel.readExcel(excelPath,sheetName);
        // 将表格数据转换为map, key=oldUrl, value=newUrl
        Map<String,String> data = excelDatas.stream().collect(Collectors.toMap(ExcelDataVO::getOldUrl, ExcelDataVO::getNewUrl));
        // 更新controller文件的url
        modifyUrl(contPath, data);
        log.info("controller文件更新完成");
        // 更新proto文件的url
        modifyUrl(protoPath, data);
        log.info("proto文件更新完成");
    }


    /**
     * 更新单个文件的url
     * @param excelPath
     * @param sheetName
     * @param sigleFile
     */
    public static void modifySingleFile(String excelPath,String sheetName, String sigleFile) {
        //读取excel表格数据
        List<ExcelDataVO> excelDatas = ReadExcel.readExcel(excelPath,sheetName);
        // 将表格数据转换为map, key=oldUrl, value=newUrl
        Map<String,String> data = excelDatas.stream().collect(Collectors.toMap(ExcelDataVO::getOldUrl, ExcelDataVO::getNewUrl));
        // 更新调用方的url
        modifyUrl(sigleFile, data);
        log.info("调用方url更新完毕");
    }

    /**
     * 读表并比较2个sheet表的newUrl, 去除copySheetName表中重复的值
     * @param excelPath
     * @param sampSheetName
     * @param copySheetName
     */
    public static void readExcelAndRewrite(String excelPath,String sampSheetName, String copySheetName, String newSheetName) {
        CompareExcel.readAndCompareExcel(excelPath, sampSheetName, copySheetName, newSheetName);
    }

}
