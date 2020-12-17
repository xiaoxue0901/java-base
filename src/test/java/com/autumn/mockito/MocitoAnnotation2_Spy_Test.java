package com.autumn.mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author xql132@zcsmart.com
 * @date 2019/9/27 11:04
 * @description 如何使用@Spy注解
 * 用处: @Spy注解监视现有实例
 */
public class MocitoAnnotation2_Spy_Test {
    @Before
    public void setUp() throws Exception {
        // 启用Mockito注解, 方式2
        MockitoAnnotations.initMocks(this);
    }

    /**
     * 不使用@Spy注解
     */
    @Test
    public void whenNotUseSpyAnnotation_thenCorrect() {
        List<String> spyList = Mockito.spy(new ArrayList<>());

        spyList.add("one");
        spyList.add("two");

        Mockito.verify(spyList).add("one");
        Mockito.verify(spyList).add("two");

        assertEquals(2, spyList.size());

        Mockito.doReturn(100).when(spyList).size();
        assertEquals(100, spyList.size());
    }

    @Spy
    List<String> spiedList = new ArrayList<>();

    /**
     * 使用@Spy注解
     */
    @Test
    public void whenUseSpyAnnotation_thenSpyIsInjectedCorrectly() {
        spiedList.add("one");
        spiedList.add("two");

        Mockito.verify(spiedList).add("one");
        Mockito.verify(spiedList).add("two");

        assertEquals(2, spiedList.size());
        // 使用100替换spiedList.size()的值
        Mockito.doReturn(100).when(spiedList).size();
        assertEquals(100, spiedList.size());

    }
}
