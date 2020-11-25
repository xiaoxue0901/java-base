package com.autumn.excel.bean;

import lombok.Data;

/**
 * @author xql132@zcsmart.com
 * @date 2020/8/11 17:17
 * @description
 */
@Data
public class ExcelDataVO {
    /**
     * 接口名称
     */
    private String name;
    /**
     * groupId
     */
    private String groupId;
    /**
     * 老url
     */
    private String oldUrl;
    /**
     * 新url
     */
    private String newUrl;
    /**
     * 调用流程
     */
    private String process;
}
