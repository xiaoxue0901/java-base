package com.autumn.demo.javabase.generic;

import lombok.Data;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/25
 * @time 17:36
 * @description 泛型类定义
 */
@Data
public class RespVO<T> {
    private int code;
    private String message;
    private T data;
}
