package com.example;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;


// 实现 BeanPostProcessor 接口  自定义 Bean 后置处理器。
//@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("customerLifecycleService".equals(beanName)) {
            System.out.println("Bean 初始化前处理：" + beanName);
        }
        // 返回原对象：继续使用原来的 Bean。
        return bean;
    }

    // 初始化方法执行之后
    @Override
    public Object postProcessAfterInitialization(
            Object bean, String beanName) throws BeansException {

        if ("customerLifecycleService".equals(beanName)) {
            System.out.println("Bean 初始化后处理：" + beanName);
        }

        return bean;
    }
}
