package com.autumn.excel.bean;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xql132@zcsmart.com
 * @date 2020/8/13 16:48
 * @description
 */
@Slf4j
public class CompareExcel {

    /**
     * 读取表格数据, 比较然后去除重复的数据
     * @param excelPath
     * @param sampSheetName
     * @param copySheetName
     */
    public static void readAndCompareExcel(String excelPath,String sampSheetName, String copySheetName, String newSheetName) {
        // 读取样本文件
        List<ExcelDataVO> sampDatas = ReadExcel.readExcel(excelPath,sampSheetName);
        // 读取副本文件
        List<ExcelDataVO> copyDatas = ReadExcel.readExcel(excelPath,copySheetName);
        // 转换副本的数据形式
        Map<String, ExcelDataVO> initMap = copyDatas.stream().collect(Collectors.toMap(ExcelDataVO::getNewUrl, data -> data));
        // 比对样本和副本, 如果有相同的值, 则副本中删除
        log.info("副本中初始数量:{}", copyDatas.size());
        sampDatas.forEach(e -> {
            compare(e.getNewUrl(), initMap);
        });
        List<ExcelDataVO> result = initMap.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
        log.info("去除重复值后, 副本中的数量:{}, 共删除数量:{}", result.size(), copyDatas.size()-result.size());
        // 将副本的数据重新写入文件中
        ExcelUtil.write(result, excelPath, newSheetName, false);
    }

    /**
     * 去重
     * @param source
     * @param initMap
     */
    public static void compare(String source, Map<String, ExcelDataVO> initMap) {
        // initMap中是否存在source
        if(initMap.containsKey(source)){
            ExcelDataVO vo = initMap.remove(source);
            log.info("找到重复的url, 接口名称:{}, oldUrl={}, newUrl = {}", vo.getName(), vo.getOldUrl(), vo.getNewUrl());
        }
    }

}
