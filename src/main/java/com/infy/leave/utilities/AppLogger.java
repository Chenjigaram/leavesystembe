package com.infy.leave.utilities;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AppLogger {

	private final static Logger logger =LoggerFactory.getLogger(AppLogger.class);
	
	@Around("execution(* com.infy.leave.controller.*.*(..))")
	public Object logAroundAllMethods(ProceedingJoinPoint jointPoint) throws Throwable{
		
		long startTime = System.currentTimeMillis();
		String className =jointPoint.getSignature().getDeclaringTypeName();
		String methodName= jointPoint.getSignature().getName();
		logger.info(className+"---"+"Entering into "+methodName+"with param"+Arrays.toString(jointPoint.getArgs()));
		
		Object result  =jointPoint.proceed();
		long endTime =System.currentTimeMillis();
		logger.info(className+"---"+"Exiting from "+methodName+"with result "+result+
				" ---execution completed in "+(endTime-startTime)+" ms");
		
		return result;
		
	}
	@AfterThrowing(pointcut="execution(* com.infy.leave.DAO.*.*(..))", throwing ="exception")
	public void logDBExceptions(JoinPoint jointPoint ,Exception exception) throws Exception {
		String className =jointPoint.getSignature().getDeclaringTypeName();
		String methodName= jointPoint.getSignature().getName();
		logger.error("Error and Exception :"+className+"---"+methodName+"---"+exception.getMessage());
		throw exception;
	}
	@AfterThrowing(pointcut="execution(* com.infy.leave.services.*.*(..))", throwing ="exception")
	public void logServiceExceptions(JoinPoint jointPoint ,Exception exception) throws Exception {
		String className =jointPoint.getSignature().getDeclaringTypeName();
		String methodName= jointPoint.getSignature().getName();
		logger.error("Error and Exception :"+className+"---"+methodName+"---"+exception.getMessage());
		throw exception;
	}
	public static void loginfo(String className,String methodName,String message) {
		logger.info(className+"---"+methodName+" :-- "+message);
	}
	public static void logError(String className,String methodName,String message) {
		logger.info(className+"---"+methodName+" :-- "+message);
	}
}
