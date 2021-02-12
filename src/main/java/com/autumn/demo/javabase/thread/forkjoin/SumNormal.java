package com.autumn.demo.javabase.thread.forkjoin;

import com.autumn.demo.javabase.thread.SleepTools;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/11
 * @time 8:46 上午
 * @description
 */
@Slf4j
public class SumNormal {
    public static void main(String[] args) {
        int count = 0;
        int[] src = MakeArray.makeArray();
        long start = System.currentTimeMillis();
        for (int i=0; i<src.length; i++) {
            SleepTools.ms(1);
            count = count +src[i];
        }
      log.info("count:{}, spend time:{} ms", count, System.currentTimeMillis()-start);
    }
}
