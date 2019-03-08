package com.autumn.demo.javabase.base.demoenum.instance;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xql132@zcsmart.com
 * @date 2019/2/21 15:57
 * @description
 */
@Data
@Slf4j
public class Triple {
    private int num;

    public Triple(int num) {
        this.num = num;
    }

    public static void main(String[] args) {
        Triple triple1 = DemoTripleEnum.getInstance(1);
        Triple triple2 = DemoTripleEnum.getInstance(2);
        Triple triple3 = DemoTripleEnum.getInstance(3);
        log.info("获取的实例为:{}", triple1.hashCode());
        log.info("获取的实例为:{}", triple2.hashCode());
        log.info("获取的实例为:{}", triple3.hashCode());
    }
}
