package com.brody.iaas.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class Aop {
    @Before("execution(* com.brody.iaas.service.implementation.VirtualMachineServiceImpl.*(..))")
    public void logMethodVmServiceEntry(JoinPoint joinPoint){
        logger(joinPoint);
    }

    @Before("execution(* com.brody.iaas.service.implementation.UserServiceImpl.*(..))")
    public void logMethodUserServiceEntry(JoinPoint joinPoint){
        logger(joinPoint);
    }

    private void logger(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        log.info("In method : "+name+":");
    }
}
