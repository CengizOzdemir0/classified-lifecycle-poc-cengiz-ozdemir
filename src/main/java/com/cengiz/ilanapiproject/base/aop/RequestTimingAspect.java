package com.cengiz.ilanapiproject.base.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Cengiz ÖZDEMİR
 * @created 13/11/2024
 */

@Aspect
@Component
public class RequestTimingAspect {
    private static final Logger logger = LoggerFactory.getLogger(RequestTimingAspect.class);
    private static final long THRESHOLD = 5;

    @Around("within(@org.springframework.web.bind.annotation.RestController *)")
    public Object logLongRunningRequests(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;

        if (duration > THRESHOLD) {
            logger.warn("İstek belirlenen süreden uzun sürdü: {} took {} ms", joinPoint.getSignature(), duration);
        }

        return result;
    }
}