/**
 * Project Name:hht-backend-communicationservice-websocket-api
 * File Name:TimeInterceptor.java
 * Package Name:com.hht.interceptor
 * Date:2018年9月14日
 * Copyright (c) 2018 深圳市鸿合创新信息技术 Inc.All Rights Reserved.
 */
package com.hht.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;

/**
 * @author zhangguokang
 *
 * @description 检测方法执行耗时的spring切面类
 *              使用@Aspect注解的类，Spring将会把它当作一个特殊的Bean（一个切面），也就是不对这个类本身进行动态代理
 * 
 */
@Component
@Aspect
public class TimeInterceptor {
    private static final Log LOG = LogFactory.getLog(TimeInterceptor.class);

    /**
     * 定义一个切入点. 解释下：
     *
     * ~ 第一个 * 代表任意修饰符及任意返回值. ~ 第二个 * 定义在web包或者子包 ~ 第三个 * 任意方法 ~ .. 匹配任意数量的参数.
     */
    @Pointcut("execution(* com.hht.controller..*.*(..))")
    public void logPointcut() {
    }

    @org.aspectj.lang.annotation.Around("logPointcut()")

    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long end = System.currentTimeMillis();
            LOG.info("[exec-  " + joinPoint + "\tUse time : " + (end - start) + " ms!]");
            return result;

        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            LOG.error("[exec-  " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage() + "]");
            throw e;
        }

    }
}
