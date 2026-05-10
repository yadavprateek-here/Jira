package com.prakar.jira.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspects {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspects.class);

    @Around("functionCallLogger()")
    public Object aroundFunctionCall(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("Method call: " + joinPoint.getSignature());

        return joinPoint.proceed();
    }

    @Pointcut("execution(* com.prakar.jira.controller..*(..))" +"execution(* com.prakar.jira.service..*(..)) || ")
    public void functionCallLogger() {}
}
