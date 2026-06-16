package com.example.hcmk24cntt1duongphucthinh003.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WatchLogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(WatchLogAspect.class);

    @Pointcut("execution(* com.example.hcmk24cntt1duongphucthinh003.service..*.create(..))"
            + " || execution(* com.example.hcmk24cntt1duongphucthinh003.service..*.update(..))"
            + " || execution(* com.example.hcmk24cntt1duongphucthinh003.service..*.patch(..))")
    public void watchMutationMethods() {
    }

    @Before("watchMutationMethods()")
    public void logMethodName(JoinPoint joinPoint) {
        LOGGER.info("Method Name: {}", joinPoint.getSignature().getName());
    }
}
