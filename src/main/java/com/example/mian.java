package com.example;

import com.example.service.CustomerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class mian {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        CustomerService customerService =
                context.getBean(CustomerService.class);
//        try {
//            customerService.addCustomerError();
//        } catch (Exception e) {
//            System.out.println("新增失败：" + e.getMessage());
//        }
        customerService.addCustomer();

//        System.out.println("----------");
//
//        customerService.updateCustomer();
//
//        System.out.println("----------");
//
//        customerService.deleteCustomer();

        context.close();
    }
}
