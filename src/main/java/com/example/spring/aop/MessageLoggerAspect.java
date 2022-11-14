package com.example.spring.aop;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class MessageLoggerAspect {

    @Pointcut("execution(* com.example.spring.service.MessageService.*(..))")
    public void logArgs() {}

    @Pointcut("@annotation(com.example.spring.aop.annotation.LoggingExecution)")
    public void elapsedTimePC(){}

    @Before("logArgs()")
    public void messageWriteLoggerBefore(JoinPoint joinPoint) {
        Arrays.stream(joinPoint.getArgs()).forEach(arg -> log.info("Args: {}",arg));
    }

    @SneakyThrows
    @Around("elapsedTimePC()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) {
        log.info("ActionLog.{}.start", joinPoint.getSignature().getName());
        long startTime = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        log.info("Around: Elapsed Time: {}", endTime - startTime);
        log.info("ActionLog.{}.success", joinPoint.getSignature().getName());
        return proceed;
    }
}
