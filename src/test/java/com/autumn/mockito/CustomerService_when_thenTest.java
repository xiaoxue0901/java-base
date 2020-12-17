package com.autumn.mockito;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * @author xql132@zcsmart.com
 * @date 2019/9/29 16:10
 * @description
 */
// 开启mockito注解模式
@RunWith(MockitoJUnitRunner.class)
public class CustomerService_when_thenTest {

    @Mock
    private CustomerDao customerDao;
    @InjectMocks
    private CustomerService customerService;

    @Test
    public void testAddCustomer_returnNewCustomer() {
        Mockito.when(customerDao.saveCustomer(any(Customer.class))).thenReturn(new Customer());

        Customer customer = new Customer();
        assertThat(customerService.addCustomer(customer), is(notNullValue()));
    }

    // Using Answer to set an id to the customer which is passed in as a parameter to the mock method
    @Test
    public void testAddCustomer_returnNewCustomerWithId() {
        when(customerDao.save(any(Customer.class))).thenAnswer(new Answer<Customer>() {
            @Override
            public Customer answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] arguments = invocationOnMock.getArguments();

                if (arguments != null && arguments.length > 0 && arguments[0] != null) {
                    Customer customer = (Customer) arguments[0];
                    customer.setId(1);
                    return customer;
                }
                return null;
            }

        });
        Customer customer = new Customer();
        assertThat(customerService.addCustomer(customer), is(notNullValue()));
    }

    // Throwing an exception from the mocked method
    @Test(expected = RuntimeException.class)
    public void testAddCustomer_throwsException() {
        when(customerDao.save(any(Customer.class))).thenThrow(RuntimeException.class);

        Customer customer = new Customer();

        customerService.addCustomer(customer);

    }

    @Test
    public void testUpdate() {
        doAnswer(new Answer<Void>(){
            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] arguments = invocationOnMock.getArguments();
                if (arguments != null && arguments.length> 1 && arguments[0] !=null && arguments[1] !=null) {
                    Customer customer = (Customer) arguments[0];
                    String email = (String) arguments[1];
                    customer.setEmail(email);
                }
                return null;
            }
        }).when(customerDao).updateEmail(any(Customer.class), any(String.class));

        // calling the method under test
        Customer customer = customerService.changeEmail("old@test.com", "new@test.com");

        // some asserts
        assertThat(customer, is(notNullValue()));
        assertThat(customer.getEmail(), is(equalTo("new@test.com")));
    }




}
