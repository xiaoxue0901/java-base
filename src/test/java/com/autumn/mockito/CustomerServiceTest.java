package com.autumn.mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import static org.mockito.ArgumentMatchers.any;

/**
 * @author xql132@zcsmart.com
 * @date 2019/9/29 12:00
 * @description when then模式介绍
 */
public class CustomerServiceTest {
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    // @Mock创建和注入模拟实例: 创建CustomerDao的模拟实例
    @Mock
    private CustomerDao customerDao;
    // @InjectMocks将模拟字段自动注入到测试对象中: 将@Mock标记的字段customerDao注入到customerService实例中.
    @InjectMocks
    private CustomerService customerService;

    @Test
    public void use_when_then() {
        Customer customer = new Customer();
        // 调用customerDao.save()方法后(忽略save()方法的真正返回值), 返回true
        Mockito.when(customerDao.save(customer)).thenReturn(true);

        // 1. 解析静态方法 when(). 调用when()会返回OngogingStubbing<T>, T是模拟的实例(实例:customerDao)的方法(save())的返回类型(Boolean).
        OngoingStubbing<Boolean> stub = Mockito.when(customerDao.save(customer));

        // 2. 在stub(OngoingStubbing<T>)上可以调用的方法
            // 2.1 设置响应值
            stub.thenReturn(true);
             // 2.2 抛出异常
            stub.thenThrow(new NullPointerException());
            // 2.3 调用真正的方法
            stub.thenCallRealMethod();
            // 2.4 建立更智能的stub, 也可以模拟void方法
            stub.thenAnswer(null);

        // 3.使用匹配器: 使用any(Customer.class)代替new Customer()
        Mockito.when(customerDao.save(any(Customer.class))).thenReturn(true);
        // 匹配器不能和实际对象混合使用. 以下是错误示范
        Mockito.when(customerDao.update(any(Customer.class), "id")).thenReturn(true);
        // 总结: 要么全部使用匹配器. 要么全部使用实际值
        Mockito.when(customerDao.update(any(Customer.class), any(String.class))).thenReturn(true);
    }
}