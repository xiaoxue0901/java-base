package com.autumn.demo.javabase.base.demo_generic;

import lombok.Data;

/**
 * @author xql132@zcsmart.com
 * @date 2019/1/18 17:00
 * @description 泛型类的使用
 */
@Data
public class GenericClass<T> {
    T data;
}
