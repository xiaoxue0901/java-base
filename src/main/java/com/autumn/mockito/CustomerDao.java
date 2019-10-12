package com.autumn.mockito;

/**
 * @author xql132@zcsmart.com
 * @date 2019/9/26 20:21
 * @description
 */
public interface CustomerDao {
    public boolean save(Customer customer) ;

    public Customer saveCustomer(Customer customer) ;

    public boolean exists(String phone) ;

    public boolean update(Customer customer, String id) ;

    public void updateEmail(Customer customer, String id) ;

    public void delete(Customer customer) ;
}
