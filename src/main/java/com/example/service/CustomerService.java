package com.example.service;


import com.example.dao.CustomerDao;
import com.example.dao.FollowupDao;
import com.example.entity.Customer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerDao customerDao;

    private final FollowupDao followupDao;

    public CustomerService(CustomerDao customerDao, FollowupDao followupDao) {
        this.customerDao = customerDao;
        this.followupDao = followupDao;
    }

    public void getCustomer() {
        System.out.println("CustomerService：调用 CustomerDao 查询客户数据");
//        customerDao.getCustomer();
    }

    public String addCustomer() {
        System.out.println("CustomerService：新增客户");
        return "客户新增成功";
    }

    public void addCustomerError() {
        System.out.println("CustomerService：开始新增客户");
        // 模拟新增客户失败
        throw new RuntimeException("客户手机号已经存在，新增失败");
    }

    public void updateCustomer() {
        System.out.println("CustomerService：修改客户");
    }

    public void deleteCustomer() {
        System.out.println("CustomerService：删除客户");
    }

    // 查询所有客户
    public List<Customer> findAllCustomers() {

        System.out.println("CustomerService：查询客户列表");

        return customerDao.findAll();
    }

    // 新增客户
    public int addCustomer(Customer customer) {
        System.out.println("CustomerService：新增客户");

        return customerDao.add(customer);
    }

    // 修改客户
    public int updateCustomer(Customer customer) {
        System.out.println("CustomerService：修改客户");

        return customerDao.update(customer);
    }

    // 删除客户
    public int deleteCustomer(Long id) {
        System.out.println("CustomerService：删除客户");

        return customerDao.deleteById(id);
    }

    // 查询单个客户
    public Customer findById(Long id) {
        System.out.println("CustomerService：查询单个客户");
        try {
            return customerDao.findById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("未找到客户，ID：" + id);
            return null;
        }
    }

    @Transactional
    public void addCustomerWithError() {

        Customer customer = new Customer();
        customer.setName("事务测试客户");
        customer.setPhone("13800000099");
        customer.setEmail("test@example.com");
        customer.setCompany("事务测试公司");
        customer.setAddress("浙江宁波");
        customer.setStatus(1);

        // 第一步：新增客户
        customerDao.add(customer);

        System.out.println("第一步：新增客户成功");

        // 第二步：故意制造异常
        int result = 10 / 0;

        System.out.println("第二步：执行成功");
    }



    @Transactional
    public void createCustomerWithFollowup(Customer customer) {

        // 第一步：新增客户，获取客户ID
        Long customerId = customerDao.addAndReturnId(customer);

        System.out.println("客户新增成功，ID：" + customerId);

        // 第二步：新增初始跟进记录
        followupDao.add(
                customerId,
                "首次联系客户，建立客户档案"
        );

        System.out.println("客户跟进记录新增成功");
    }
}



