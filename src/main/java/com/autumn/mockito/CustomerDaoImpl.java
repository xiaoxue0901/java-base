package com.autumn.mockito;

/**
 * @author xql132@zcsmart.com
 * @date 2019/10/12 21:40
 * @description
 */
public class CustomerDaoImpl implements CustomerDao {


    @Override
    public boolean save(Customer customer) {
        return false;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return null;
    }

    @Override
    public boolean exists(String phone) {
        return false;
    }

    @Override
    public boolean update(Customer customer, String id) {
        return false;
    }

    @Override
    public void updateEmail(Customer customer, String id) {
        System.out.println("更新邮件信息");
    }

    @Override
    public void delete(Customer customer) {
        System.out.println("删除信息");
    }
}
