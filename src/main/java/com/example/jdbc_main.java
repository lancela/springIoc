package com.example;

import com.example.entity.Customer;
import com.example.service.CustomerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

public class jdbc_main {

    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        DataSource dataSource = context.getBean(DataSource.class);

        try (Connection connection = dataSource.getConnection()) {

            System.out.println("数据库连接成功！");
            System.out.println("数据库名称："
                    + connection.getCatalog());
            System.out.println("数据库版本："
                    + connection.getMetaData().getDatabaseProductVersion());
        }


        CustomerService customerService =
                context.getBean(CustomerService.class);


//        System.out.println("========== 新增客户 ==========");
//
//        Customer customer = new Customer();
//
//        customer.setName("赵六六");
//        customer.setPhone("13800000004");
//        customer.setEmail("zhaoliu@example.com");
//        customer.setCompany("赵六科技有限公司");
//        customer.setAddress("江西上饶");
//        customer.setStatus(1);
//        int rows = customerService.addCustomer(customer);
//        System.out.println("新增成功，影响行数：" + rows);

//        System.out.println("========== 修改客户 ==========");
//
//        Customer customer = new Customer();
//
//        customer.setId(5L);
//        customer.setName("赵六七八");
//        customer.setPhone("13900000004");
//        customer.setEmail("zhaoliu_new@example.com");
//        customer.setCompany("赵六科技有限公司");
//        customer.setAddress("浙江宁波");
//        customer.setStatus(1);
//
//        int rows = customerService.updateCustomer(customer);
//
//
//        System.out.println("修改成功，影响行数：" + rows);

//        System.out.println("========== 删除客户 ==========");
//        int delrows = customerService.deleteCustomer(4L);
//
//        System.out.println("删除成功，影响行数：" + delrows);

//        System.out.println("========== 客户列表 ==========");
//        List<Customer> customers =
//                customerService.findAllCustomers();
//
//        for (Customer customerItem : customers) {
//            System.out.println(customerItem);
//        }

//        System.out.println("========== 单个客户 ==========");
//
//        Customer customerOne =
//                customerService.findById(50L);
//        System.out.println(customerOne);


//        事务执行
//        try {
//            customerService.addCustomerWithError();
//        } catch (Exception e) {
//            System.out.println("业务执行失败：" + e.getMessage());
//        }


//        事务案例
        Customer customer = new Customer();

        customer.setName("事务实战客户");
        customer.setPhone("13800000088");
        customer.setEmail("crm@example.com");
        customer.setCompany("CRM测试有限公司");
        customer.setAddress("浙江宁波");
        customer.setStatus(1);

        customerService.createCustomerWithFollowup(customer);
        context.close();
    }
}
