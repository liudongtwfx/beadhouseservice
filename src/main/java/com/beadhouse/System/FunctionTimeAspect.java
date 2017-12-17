package main.java.com.beadhouse.System;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by beadhouse on 17-6-28.
 */
@Aspect
@Component
public class FunctionTimeAspect {
    private static final Logger logger = LogManager.getLogger("functiontime");

    public FunctionTimeAspect() {
        System.out.println("function time...");
    }

    @Pointcut("execution(* main.java.com.beadhouse.controller..*.*(..))")
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
            logger.info(jp.getTarget().getClass().getName() + " " + jp.getSignature().getName() + " " + String.valueOf(end - start));
        }
        return null;
    }

    @After("funcTime()")
    public void afterFuncTime(JoinPoint jp) {
        long end = System.currentTimeMillis();
    }

    @Before("funcTime()")
    public String beforeFuncTime(JoinPoint jp) {
        Object[] objects = jp.getArgs();
        for (Object o : objects) {
            if (o instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest) o;
                LogType.DEBUGINFO.getLOGGER().debug(request.getRequestURL());
                request.getParameterMap().forEach((k, v) -> LogType.DEBUGINFO.getLOGGER().debug(k.toString() + v.toString()));
                if (request.getRequestURL().indexOf("/admin/") != -1) {
                    if (request.getSession().getAttribute("adminName") == null) {
                        return "redirect:/admin/login";
                    }
                }
            }
        }
        return "";
    }
}
