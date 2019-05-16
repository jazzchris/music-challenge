package com.jazzchris.musicchallenge.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ApplicationLog {
	
	private Logger logger = LoggerFactory.getLogger(getClass().getName());

	@Before("execution(* com.jazzchris.musicchallenge.service.TestService.create())")
	public void createTest() {
		logger.info("Creating new quiz");
	}
	
	@Around("execution(* com.jazzchris.musicchallenge.ChallengeApplication.dbxClientV2())")
	public Object dropboxConnect(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		logger.info("Creating access to the cloud");
		Object result = theProceedingJoinPoint.proceed();
		logger.info("Connection success");
		return result;
	
	}
}
