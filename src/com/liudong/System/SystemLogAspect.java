package com.liudong.System;

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
 * Created by liudong on 2017/6/21.
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
    @Pointcut("execution(* com.liudong.controller.admin.AdminCarouselController.*(..))")
    public Object controllerAspect() {
        return null;
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("execution(* com.liudong.controller.admin.AdminCarouselController.*(..))")
    public Object doBefore(JoinPoint joinPoint) {
        System.out.println("==========执行controller前置通知===============");
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
//
//    /**
//     * 后置通知 用于拦截Controller层记录用户的操作
//     *
//     * @param joinPoint 切点
//     */
//    @After("controllerAspect()")
//    public Object after(JoinPoint joinPoint) {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = request.getSession();
//        //读取session中的用户
//        // User user = (User) session.getAttribute("user");
//        //VipUser user = this.vipUserRepository.findByUserName((String) session.getAttribute("user"));
//        //请求的IP
//        String ip = request.getRemoteAddr();
//        try {
//
//            String targetName = joinPoint.getTarget().getClass().getName();
//            String methodName = joinPoint.getSignature().getName();
//            Object[] arguments = joinPoint.getArgs();
//            Class targetClass = Class.forName(targetName);
//            Method[] methods = targetClass.getMethods();
//            String operationType = "";
//            String operationName = "";
//            for (Method method : methods) {
//                if (method.getName().equals(methodName)) {
//                    Class[] clazzs = method.getParameterTypes();
//                    if (clazzs.length == arguments.length) {
////                        operationType = method.getAnnotation(Log.class).operationType();
////                        operationName = method.getAnnotation(Log.class).operationName();
//                        break;
//                    }
//                }
//            }
////            //*========控制台输出=========*//
////            System.out.println("=====controller后置通知开始=====");
////            System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()") + "." + operationType);
////            System.out.println("方法描述:" + operationName);
////            //System.out.println("请求人:" + user.getUserName());
////            System.out.println("请求IP:" + ip);
////            //*========数据库日志=========*//
////            SystemLog log = new SystemLog();
////            log.setId(UUID.randomUUID().toString());
////            log.setDescription(operationName);
////            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()") + "." + operationType);
////            log.setLogType((long) 0);
////            log.setRequestIp(ip);
////            log.setExceptioncode(null);
////            log.setExceptionDetail(null);
////            log.setParams(null);
////            //log.setCreateBy(user.getUserName());
////            log.setCreateDate(new Date());
////            //保存数据库
////            System.out.println("=====controller后置通知结束=====");
//        } catch (Exception e) {
//            //记录本地异常日志
//            logger.error("==后置通知异常==");
//            logger.error("异常信息:{}", e.getMessage());
//        }
//        return null;
//    }
//
//    //配置后置返回通知,使用在方法aspect()上注册的切入点
//    @AfterReturning("controllerAspect()")
//    public Object afterReturn(JoinPoint joinPoint) {
//        System.out.println("=====执行controller后置返回通知=====");
//        if (logger.isInfoEnabled()) {
//            logger.info("afterReturn " + joinPoint);
//        }
//        return null;
//    }
//
//    /**
//     * 异常通知 用于拦截记录异常日志
//     *
//     * @param joinPoint
//     * @param e
//     */
//    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
//    public Object doAfterThrowing(JoinPoint joinPoint, Throwable e) {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = request.getSession();
//        //读取session中的用户
//        // User user = (User) session.getAttribute("user");
//        //VipUser user = this.vipUserRepository.findByUserName((String) session.getAttribute("user"));
//        //请求的IP
//        String ip = request.getRemoteAddr();
//
//
//        String params = "";
//        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
//            for (int i = 0; i < joinPoint.getArgs().length; i++) {
//                params += joinPoint.getArgs()[i] + ";";
//            }
//        }
//        try {
//            StringBuilder operationType = new StringBuilder();
//            StringBuilder operationName = new StringBuilder();
//            handleJoinPoint(joinPoint, operationType, operationName);
//             /*========控制台输出=========*/
//            System.out.println("=====异常通知开始=====");
//            System.out.println("异常代码:" + e.getClass().getName());
//            System.out.println("异常信息:" + e.getMessage());
//            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()") + "." + operationType);
//            System.out.println("方法描述:" + operationName);
//            //System.out.println("请求人:" + user.getUserName());
//            System.out.println("请求IP:" + ip);
//            System.out.println("请求参数:" + params);
//               /*==========数据库日志=========*/
////            SystemLog log = new SystemLog();
////            log.setId(UUID.randomUUID().toString());
////            log.setDescription(operationName.toString());
////            log.setExceptioncode(e.getClass().getName());
////            log.setLogType((long) 1);
////            log.setExceptionDetail(e.getMessage());
////            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
////            log.setParams(params);
////            log.setCreateBy("admin");
////            log.setCreateDate(new Date());
////            log.setRequestIp(ip);
//            //保存数据库
//            System.out.println("=====异常通知结束=====");
//        } catch (Exception ex) {
//            //记录本地异常日志
//            logger.error("==异常通知异常==");
//            logger.error("异常信息:{}", ex.getMessage());
//        }
//         /*==========记录本地异常日志==========*/
//        logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);
//        return null;
//    }
//
//    private void handleJoinPoint(JoinPoint joinPoint, StringBuilder operationType, StringBuilder operationName) {
//        String targetName = joinPoint.getTarget().getClass().getName();
//        String methodName = joinPoint.getSignature().getName();
//        Object[] arguments = joinPoint.getArgs();
//        Class targetClass = null;
//        try {
//            targetClass = Class.forName(targetName);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        Method[] methods = targetClass.getMethods();
//        for (Method method : methods) {
//            if (method.getName().equals(methodName)) {
//                Class[] clazzs = method.getParameterTypes();
//                if (clazzs.length == arguments.length) {
////                    operationType.append(method.getAnnotation(Log.class).operationType());
////                    operationName.append(method.getAnnotation(Log.class).operationName());
//                    break;
//                }
//            }
//        }

}