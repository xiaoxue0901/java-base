package com.autumn.mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author xql132@zcsmart.com
 * @date 2019/10/12 21:20
 * @description
 */
public class CustomerService_void_method_Test {
    @Mock
    private CustomerDao customerDao;

    @InjectMocks
    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        // 开启mock注解
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mockito_verify() {
        when(customerDao.save(any(Customer.class))).thenReturn(true);

        Customer customer = new Customer();
        assertThat(customerService.addCustomer(customer), is(true));
        //verify that the save method has been invoked
        verify(customerDao).save(any(Customer.class));
        //the above is similar to :  verify(daoMock, times(1)).save(any(Customer.class));

        //verify that the exists method is invoked one time
        verify(customerDao, times(1)).exists(anyString());

        //verify that the delete method has never been  invoked
        verify(customerDao, never()).delete(any(Customer.class));

    }

    @Captor
    private ArgumentCaptor<Customer> customerArgument;

    @Test
    public void mockito_argumentCaptor() {
        //Requirement: we want to register a new customer. Every new customer should be assigned a random token before saving in the database.
        customerService.register(new Customer());

        //captures the argument which was passed in to save method.
        verify(customerDao).save(customerArgument.capture());

        //make sure a token is assigned by the register method before saving.
        assertThat(customerArgument.getValue().getToken(), is(notNullValue()));

    }
}
