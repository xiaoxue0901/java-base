package com.autumn.demo.javabase.annonation;

import java.lang.annotation.*;

/**
 * @author xql132@zcsmart.com
 * @date 2019/11/27 17:10
 * @description
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BodyAnnonation {

    /**
     * 长度: 获取属性长度
     * @return
     */
    int len();

    /**
     * 编码: 获取属性编码格式
     * @return
     */
    String code() default "HEX";

}
