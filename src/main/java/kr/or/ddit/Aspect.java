package kr.or.ddit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@org.aspectj.lang.annotation.Aspect
public class Aspect {
	
	private static final Logger logger = LoggerFactory.getLogger(Aspect.class);
	
	@Pointcut("execution(* kr.or.ddit..service.*.*(..))")
	public void dummy() {
		
	}
	
	//특정 메서드가 실행되기 전에 실행 되어야 할 공통의 관심사항
	
	@Before("dummy()")
	public void beforeMethod(JoinPoint joinpoint) {    //Joinpoint인자선언
		logger.debug("Aspect.beforMethod()");
	}
	
	//around : 특정 메서드 실행 전후
	//메서드 실행전 - 공통 관심사항
	// 메서드의 원래 로직
	// 메서드의 실행후 - 공통 관심사항
	@Around("dummy()")
	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		
		//메서드 본래 로직 실행 전
		long startTime = System.nanoTime();
		
		//proced를 쓰게되면 메서드의 원래 로직이 실행됨
		Object ret = joinPoint.proceed(joinPoint.getArgs());
		
		//메서드 본래 로직 실행 후
		long endTime = System.nanoTime();
		
		//클래스명,메서드명,실행시간
		logger.debug("{} {} : duration: {}" , className , methodName, endTime - startTime);

		return ret;
	}
}