package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class CustomerLogAspect {

    // 这里的 * 表示返回值类型不限，(..) 表示方法参数不限。
    // @Before("execution(* com.example.service.CustomerService.addCustomer(..))")
    //  定义切入点：拦截 CustomerService 中的所有方法
    @Pointcut("execution(* com.example.service.CustomerService.*(..))")
    public void customerServicePointcut() {
    }

    // 在切入点匹配的方法执行之前记录日志
    @Before("customerServicePointcut()")
    public void beforeCustomerOperation(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName();

        System.out.println("AOP日志：客户操作开始");
        System.out.println("操作方法：" + methodName);
    }
    // returning = "result1" 它表示将目标方法的返回值传递给通知方法中名为 result1 的参数
    //如果目标方法抛出异常，就不会执行 @AfterReturning。
    @AfterReturning(
            pointcut = "customerServicePointcut()",
            returning = "result1"
    )
    public void afterCustomerSuccess(Object result1) {
        System.out.println("AOP日志：客户操作成功");
        System.out.println("业务返回结果：" + result1);
    }

    @AfterThrowing(
            pointcut = "customerServicePointcut()",
            throwing = "exception"
    )
    public void afterCustomerError(Exception exception) {
        System.out.println("AOP日志：客户操作失败");
        System.out.println("异常信息：" + exception.getMessage());
    }
    @After("customerServicePointcut()")
    public void afterCustomerOperation() {
        System.out.println("AOP日志：客户操作执行结束");
    }

    @Around("customerServicePointcut()")
    // 这个方法的参数是 ProceedingJoinPoint，它是 JoinPoint 的子接口，提供了 proceed() 方法，用于执行目标方法。
    public Object aroundCustomerOperation(ProceedingJoinPoint joinPoint)
            throws Throwable {

        // 记录开始时间
        long start = System.currentTimeMillis();

        try {
            // 将业务返回值继续返回给调用方
            return joinPoint.proceed();
        } finally {
            long end = System.currentTimeMillis();

            System.out.println("业务执行耗时："
                    + (end - start) + "ms");
        }
    }

}


