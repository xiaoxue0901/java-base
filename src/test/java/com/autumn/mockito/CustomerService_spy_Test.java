package com.autumn.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author xql132@zcsmart.com
 * @date 2019/10/12 21:39
 * @description
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerService_spy_Test {

    @Spy
    private CustomerDaoImpl daoSpy;

    @InjectMocks
    private CustomerService service;

    @Test
    public void name() {
        Customer customer = new Customer();
        assertThat(service.addCustomer(customer), is(false));

        verify(daoSpy).save(any(Customer.class));

        verify(daoSpy, times(1)).exists(anyString());

        verify(daoSpy, never()).delete(any(Customer.class));
    }
}
