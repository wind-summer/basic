package com.basic.core.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  aop实例
 * </p>
 *
 * 执行顺序
 *
 * Around：---------------------
 * Before：---------------------
 * 好这个是demo 的测试方法：test
 * Around：--------Around结束-------------
 * After：---------------------
 * AfterReturning：---------------------
 *
 * @author wenlongfei
 * @since 2019/3/27
 */
//@Component
//@Aspect
@Slf4j
public class TestAspect {

    /**
     * 切入点
     */
    /*@Pointcut("@annotation(com.basic.core.annotation.SysLog)")
    public void poincut(){
    }

    *//**
     * @Before: 前置通知, 在方法执行之前执行
     *//*
    @Before("poincut()")
    public void before(JoinPoint point){
        log.info("Before：---------------------");
    }

    *//**
     * @Around: 环绕通知, 围绕着方法执行
     *//*
    @Around("poincut()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        log.info("Around：---------------------");
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //保存日志
        //saveSysLog(point, time);
        return result;
    }

    *//**
     * @After: 后置通知, 在方法执行之后执行
     *//*
    @After("poincut()")
    public void after(JoinPoint point){
        log.info("After：---------------------");
    }

    *//**
     * @AfterRunning: 返回通知, 在方法返回结果之后执行
     *//*
    @AfterReturning(value = "poincut()", returning = "result")
    public void afterReturning(JoinPoint point, Object result){
        log.info("AfterReturning：---------------------");
    }



    *//**
     * @AfterThrowing: 异常通知, 在方法抛出异常之后
     *//*
    @AfterThrowing(value = "poincut()", throwing = "ex")
    public void afterThrowing(JoinPoint point, Exception ex){
        log.info("AfterThrowing：---------------------");
    }*/


}
