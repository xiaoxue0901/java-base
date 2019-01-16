package com.autumn.demo.javabase.lambda.demo1;

/**
 * @author xql132@zcsmart.com
 * @date 2019/1/15 16:55
 * @description
 */
@FunctionalInterface
public interface DemoLambda2 {

    int delete(String a);

    // Object中的方法不包括
    public int hashCode();

    // default修饰的不包括
    default int insert(){
        return 1;
    }
    // static修饰的不包括
    static void update() {}
}
