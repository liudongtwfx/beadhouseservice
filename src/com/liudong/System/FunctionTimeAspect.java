package com.liudong.System;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Enumeration;

/**
 * Created by liudong on 17-6-28.
 */
@Aspect
@Component
public class FunctionTimeAspect {
    private static final Logger logger = LogManager.getLogger("functiontime");

    public FunctionTimeAspect() {
        System.out.println("function time...");
    }

    @Pointcut("execution(* com.liudong.controller..*.*(..))")
    private void funcTime() {
    }

    @Around("funcTime()")
    public Object timeAroud(ProceedingJoinPoint jp) {
        long start = System.currentTimeMillis();
        Object object = null;
        try {
            object = jp.proceed();
            return object;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            long end = System.currentTimeMillis();
            System.out.println(object);
            logger.info(jp.getTarget() + " " + jp.getSignature().getName() + " " + String.valueOf(end - start));
        }
        return null;
    }

    @After("funcTime()")
    public void afterFuncTime(JoinPoint jp) {
        long end = System.currentTimeMillis();
    }
}
