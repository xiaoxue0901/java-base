package com.autumn.mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author xql132@zcsmart.com
 * @date 2019/9/27 14:45
 * @description Injecting a Mock into a Spy
 * 不支持注解方式使用mock注入到spy实例中
 */
public class MocitoAnnotation5_MockAndSpy_Test {
    @Mock
    Map<String, String> wordMap;
    @Spy
    MyDictionary spyDic = new MyDictionary();

    @Before
    public void setUp() throws Exception {
        //开启MockitoAnnotation注解
        MockitoAnnotations.initMocks(this);
    }


    /**
     * Mockito不支持注入mock到spy实例中. 此单元测试会抛出异常
     */
    @Test
    public void whenUseInjectMocksAnnotation_thenCorrect() {
        // wordMap不能注入到syDic中
        Mockito.when(wordMap.get("aWord")).thenReturn("aMeaning");

        assertEquals("aMeaning", spyDic.getMeaning("aWord"));
    }


}
