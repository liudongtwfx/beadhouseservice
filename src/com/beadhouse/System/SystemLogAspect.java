package com.beadhouse.System;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by beadhouse on 2017/6/21.
 */
@Aspect
@Component
public class SystemLogAspect {


//    @Inject
//    VipUserRepository vipUserRepository;


    private static final Logger logger = LogManager.getLogger();

    public SystemLogAspect() {
        System.out.println("========Initialing=========");
    }

    //Controller层切点
    @Pointcut("execution(* com.beadhouse.controller.admin.AdminCarouselController.*(..))")
    public Object controllerAspect() {
        return null;
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("execution(* com.beadhouse.controller.admin.AdminCarouselController.*(..))")
    public Object doBefore(JoinPoint joinPoint) {
        System.err.println("==========执行controller前置通知===============");
        if (logger.isInfoEnabled()) {
            logger.info("before " + joinPoint);
        }
        return null;
    }

    //配置controller环绕通知,使用在方法aspect()上注册的切入点
    @Around("controllerAspect()")
    public Object around(JoinPoint joinPoint) {
        System.out.println("==========开始执行controller环绕通知===============");
        long start = System.currentTimeMillis();
        Object resObject = null;
        try {
            resObject = ((ProceedingJoinPoint) joinPoint).proceed();
            long end = System.currentTimeMillis();
            if (logger.isInfoEnabled()) {
                logger.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
            }
            System.out.println("==========结束执行controller环绕通知===============");
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            if (logger.isInfoEnabled()) {
                logger.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
            }
        }
        return resObject;
    }
}