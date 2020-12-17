package com.autumn.mockito;

/**
 * @author xql132@zcsmart.com
 * @date 2019/9/26 20:23
 * @description
 */
public class CustomerService {


    private CustomerDao customerDao;

    public boolean addCustomer(Customer customer){

        if(customerDao.exists(customer.getPhone())){
            return false;
        }

        return customerDao.save(customer);
    }

    public Customer changeEmail(String oldEmail, String newEmail) {
        Customer customer = new Customer();
        customer.setEmail(newEmail);
        customer.setId(1);
        customerDao.updateEmail(customer, "1");
        return customer;
    }
    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void register(Customer customer) {
        System.out.println("注册新用户, 在将新用户保存到数据库之前应该分配一个随机令牌");
    }
}
