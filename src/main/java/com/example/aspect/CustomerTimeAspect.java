package com.example.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class CustomerTimeAspect {

    @Around("execution(* com.example.service.CustomerService.*(..))")
    public Object calculateTime(ProceedingJoinPoint joinPoint)
            throws Throwable {

        long start = System.currentTimeMillis();

        System.out.println("耗时切面：开始执行");

        try {
            return joinPoint.proceed();
        } finally {
            long end = System.currentTimeMillis();

            System.out.println("耗时切面：执行结束");
            System.out.println("方法耗时：" + (end - start) + "ms");
        }
    }
}