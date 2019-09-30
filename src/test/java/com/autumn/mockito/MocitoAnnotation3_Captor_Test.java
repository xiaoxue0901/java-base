package com.autumn.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author xql132@zcsmart.com
 * @date 2019/9/27 11:54
 * @description @Captor注解使用
 * 用处: 创建ArgumentCaptor实例
 */
// 开启Mockito注解使用
@RunWith(MockitoJUnitRunner.class)
public class MocitoAnnotation3_Captor_Test {
    @Test
    public void whenNotUseCptorAnnotation_thenCorrect() {
        List mockList = Mockito.mock(List.class);
        ArgumentCaptor<String> arg = ArgumentCaptor.forClass(String.class);

        mockList.add("one");
        Mockito.verify(mockList).add(arg.capture());
        
        assertEquals("one", arg.getValue());
    }

    @Mock
    List mockedList;
    @Captor
    ArgumentCaptor argumentCaptor;
    @Test
    public void whenUseCaptorAnnotation_thenTheSam() {
        mockedList.add("one");
        Mockito.verify(mockedList).add(argumentCaptor.capture());

        assertEquals("one", argumentCaptor.getValue());

    }
}
