package com.autumn.demo.lambda.demo1;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @author xql132@zcsmart.com
 * @date 2019/1/15 16:10
 * @description Lambda表达式示例
 */
@Slf4j
public class DemoLambda1 {
    public static void main(String[] args) {
        demoLambda1();
        demoLambda2();
        demoRunnabel();
        try {
            demoCallable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        testOwnLambda();
        demoFunction();
        testUnaryOperator();

    }

    public static void testUnaryOperator() {
        UnaryOperator<String> u = (a) -> {
            log.info("出入参数一致, 入参是: {}", a);
            return a;
        };
        log.info("结果是:{}", u.apply("hello"));
    }

    public static void demoFunction() {
        // 花括号中逻辑体多, 不能省略
        Function<String, String> f1 = (a) -> {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i <= 10; i++) {
                sb.append(a);
            }
            return sb.toString();
        };
        log.info("function结果: {}", f1.apply("hello"));
    }

    public static void testOwnLambda() {
        // old
        DemoLambda2 demo = new DemoLambda2() {
            @Override
            public int delete(String a) {
                log.info("old way delete, a: {}", a);
                return 1;
            }
        };

        // 有参有返回值
        DemoLambda2 demo1 = (a) -> {
            log.info("lambda a {}", a);
            return 1;
        };

        // 省略入参类型和{}, return
        DemoLambda2 demo2 = (String a) -> 1;

        log.info("执行结果: {}", demo.delete("hello xiao xiao"));
        log.info("执行结果: {}", demo1.delete("hello xiao xiao"));
        log.info("执行结果: {}", demo2.delete("hello xiao xiao"));
    }

    /**
     * Callable的lambda表达式写法
     *
     * @throws Exception
     */
    public static void demoCallable() throws Exception {
        // old
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello world";
            }
        };

        // new 完整写法
        Callable<String> callable1 = () -> {
            return "lambda hello wold 1";
        };

        // new 省略{}和return
        Callable<String> callable2 = () -> "lambda hello wold 2";

        log.info(callable.call());
        log.info(callable1.call());
        log.info(callable2.call());


    }

    /**
     * 实现runnable的两种方式
     */
    public static void demoRunnabel() {
        // old
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("old way");
            }
        };
        runnable.run();

        // new
        Runnable runnable1 = () -> {
            System.out.println("lambda expression");
        };
        runnable1.run();

        // new 去除{}
        Runnable runnable2 = () -> System.out.println();
        runnable2.run();
    }

    /**
     * 实现单线程接口:两种方式
     */
    public static void demoLambda1() {
        // 1. old
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread run");
            }
        }).start();

        // 2. new
        new Thread(() -> System.out.println("thread run lambda")).start();
    }

    public static void demoLambda2() {
        List<String> strs = Arrays.asList("web", "nginx", "netty", "tomcat");

        // old
        Collections.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        //new
        Collections.sort(strs, (a, b) -> a.length() - b.length());
        System.out.println(strs);
    }
}
