package com.autumn.mockito;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

/**
 * @author xql132@zcsmart.com
 * @date 2019/9/27 15:26
 * @description NullPointExcpetion异常原因
 */
public class NPETest {
    @Mock
    List mockList;

    /**
     * 此单元测试会发生NPE, 因为没有开启Mockito注解.
     * 解决方式:
     * 1. 在类上加@RunWith(MockitoJunitTest.class)
     * 2. 在@Before单元测试里加MockitoAnnotations.initMocks(this);
     */
    @Test
    public void test() {
        Mockito.when(mockList.size()).thenReturn(1);
    }
}
