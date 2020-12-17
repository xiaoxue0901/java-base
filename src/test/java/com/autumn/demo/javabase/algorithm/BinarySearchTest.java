package com.autumn.demo.javabase.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author xql132@zcsmart.com
 * @date 2019/10/16 17:52
 * @description
 */
@Slf4j
public class BinarySearchTest {

    @Test
    public void binarySearch() {
        int[] s = {1, 5, 8, 11, 19, 22, 31, 35, 40, 45, 48, 49, 50};
        int x = 48;
        log.info("二分查找使用次数:{}", BinarySearch.binarySearch_asc(s, x));
    }
}