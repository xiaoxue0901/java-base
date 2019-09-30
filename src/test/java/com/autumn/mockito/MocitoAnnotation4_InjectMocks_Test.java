package com.autumn.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author xql132@zcsmart.com
 * @date 2019/9/27 14:13
 * @description @InjectMocks注解
 * 用处: 将模拟字段自动注入到测试对象中
 */
@RunWith(MockitoJUnitRunner.class)
public class MocitoAnnotation4_InjectMocks_Test {

    @Mock
    Map<String, String> wordMap;

    @InjectMocks
    MyDictionary dic = new MyDictionary();

    /**
     * 将wordMap的值注入到MyDictionary的wordMap中
     */
    @Test
    public void whenUseInjectMocksAnnotation_thenCorrect() {
        Mockito.when(wordMap.get("aWord")).thenReturn("aMeaning");

        assertEquals("aMeaning", dic.getMeaning("aWord"));
    }
}
