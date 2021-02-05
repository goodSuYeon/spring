package kr.or.ddit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PerformanceCheckInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(PerformanceCheckInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//시작시간을 기록
		long startTime = System.nanoTime();
		request.setAttribute("startTime", startTime);
		
		//요청을 다음(interceptor 또는 Controller)에게 위임할지 여부를 반환함
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//ModelAndView객체가 있는것을 보고 응답이 이미 생성되었음을 알 수 있음
		
		//Controller메서드가 실행된 이후 실행되는 영역
		long endTime = System.nanoTime();
		long startTime = (long)request.getAttribute("startTime");
		
		logger.debug("uri{}, duration: {}" , request.getRequestURI(), endTime-startTime);
	}
}
