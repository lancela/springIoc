package com.example.service;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

//@Component
public class CustomerLifecycleService {

    // 构造方法：观察 Bean 实例化
    public CustomerLifecycleService() {
        System.out.println("1. Bean 实例化：创建对象");
    }
    @PostConstruct
    public void init() {
        System.out.println("2. Bean 初始化：执行 init()");
    }
    @PreDestroy
    public void destroy() {
        System.out.println("3. Bean 销毁：执行 destroy()");
    }

    public void doWork() {
        System.out.println("4. Bean 正在执行业务");
    }
}
