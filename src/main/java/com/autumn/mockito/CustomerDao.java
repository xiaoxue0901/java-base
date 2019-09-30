package com.autumn.mockito;

/**
 * @author xql132@zcsmart.com
 * @date 2019/9/26 20:21
 * @description
 */
public class CustomerDao {
    public boolean save(Customer customer) {
        return false;
    }

    public Customer saveCustomer(Customer customer) {
        return null;
    }

    public boolean exists(String phone) {
        return false;
    }

    public boolean update(Customer customer, String id) {
        return false;
    }

    public void updateEmail(Customer customer, String id) {
        System.out.println("更新邮件信息");
    }
}
