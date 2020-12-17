package com.autumn.mockito;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author xql132@zcsmart.com
 * @date 2019/9/27 10:52
 * @description 如何使用@Mock注解
 * 用处: @Mock注解用来创建和注入模拟实例
 */
// 启用Mockito注解, 方式1
@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class MocitoAnnotation1_Mock_Test {
//    @Before
//    public void setUp() throws Exception {
//        // 启用Mockito注解, 方式2
//        MockitoAnnotations.initMocks(this);
//    }


    /**
     * 不使用@Mock注解
     */
    @Test
    public void whenNotUseMockAnnotation_thenCorrect() {
        List mockList = Mockito.mock(ArrayList.class);

        mockList.add("one");
        Mockito.verify(mockList).add("one");
        assertEquals(0, mockList.size());

        Mockito.when(mockList.size()).thenReturn(100);
        assertEquals(100, mockList.size());
    }

    @Mock
    List<String> mockedList;

    /**
     * 使用@Mock注解
     */
    @Test
    public void whenUseMockAnnotation_thenMockIsInjected() {
        mockedList.add("one");
        Mockito.verify(mockedList).add("one");
        assertEquals(0, mockedList.size());

        Mockito.when(mockedList.size()).thenReturn(100);
        assertEquals(100, mockedList.size());
    }
}
