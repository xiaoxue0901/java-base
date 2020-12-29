package com.autumn.demo.java8.chap6;

import lombok.extern.slf4j.Slf4j;

import static com.autumn.demo.java8.chap4.Dish.menu;
import static java.util.stream.Collectors.*;

/**
 * @author xql132@zcsmart.com
 * @date 2019/12/27 18:37
 * @description
 */
@Slf4j
public class Summarizing {
    public static void main(String[] args) {
        log.info("菜的数量:{}", howManyDishs());
    }

    private static long howManyDishs() {return menu.stream().collect(counting());}


}
