package com.autumn.demo.javabase.base.demoenum.instance;

/**
 * @author xql132@zcsmart.com
 * @date 2019/2/21 15:58
 * @description
 */
public enum DemoTripleEnum {
    INSTANCE1, INSTANCE2
    , INSTANCE3;

    private Triple triple;

    public static Triple getInstance(int num){
      return new Triple(num);
    }
}
