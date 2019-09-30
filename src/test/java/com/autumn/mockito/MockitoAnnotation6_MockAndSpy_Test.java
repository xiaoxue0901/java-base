package com.autumn.mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author xql132@zcsmart.com
 * @date 2019/9/27 14:45
 * @description Injecting a Mock into a Spy
 */
public class MockitoAnnotation6_MockAndSpy_Test {
    @Mock
    Map<String, String> wordMap;
    MyDictionary spyDic;

    @Before
    public void setUp() throws Exception {
        //开启MockitoAnnotation注解
        MockitoAnnotations.initMocks(this);
        // 不使用注解, 手动创建spy
        spyDic = Mockito.spy(new MyDictionary(wordMap));
    }


    /**
     * Mockito不支持注入mock到spy实例中.
     * 使用mock带spy, 手动将mock注入到spy实例的构造函数汇中
     */
    @Test
    public void whenUseInjectMocksAnnotation_thenCorrect() {
        Mockito.when(wordMap.get("aWord")).thenReturn("aMeaning");

        assertEquals("aMeaning", spyDic.getMeaning("aWord"));
    }


}
