package com.example.controller;


import com.example.service.CustomerService;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void getCustomer() {
        System.out.println("CustomerController：调用 CustomerService 查询客户数据");
        customerService.getCustomer();
    }
}
