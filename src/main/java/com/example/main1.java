package com.example;

import com.example.controller.CustomerController;
import com.example.service.CustomerLifecycleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class main1 {
    public static void main(String[] args) {
        // 1. 创建 Spring 容器
        System.out.println("========== 启动容器 ==========");

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        // 2. 从容器获取 Bean
        System.out.println("========== 获取 Bean ==========");

        CustomerLifecycleService customerLifecycleService = context.getBean(CustomerLifecycleService.class);

        CustomerLifecycleService service2 =
                context.getBean(CustomerLifecycleService.class);

        System.out.println(customerLifecycleService == service2);

        // 3. 执行业务
        System.out.println("========== 执行业务 ==========");

        customerLifecycleService.doWork();

        // 4. 关闭容器
        System.out.println("========== 关闭容器 ==========");




        context.close();
    }
}
