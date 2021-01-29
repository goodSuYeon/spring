package kr.or.ddit.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;
import kr.or.ddit.user.service.UserService;

public class IocMain {
	
	private static final Logger logger = LoggerFactory.getLogger(IocMain.class);

	public static void main(String[] args) {
		//1. Spring 설정파일을 이용해 스프링 컨테이너를 생성(kr/or/ddit/ioc/ioc.xml)
	    //   스프링컨테이너 타입: ApplicationContext

		//2. 스프링컨테이너에게 만들어진 scope Bean(=객체)를 "요청"
		//   DL(Dependency Lookup) : 스프링 컨테이너에게 스프링 bean을 요청하는 과정
		
		//3. 스프링컨테이너에서 관리되고 있는 빈이 잘 만들어졌는지 확인
		
		//1 과정 
		//경로 지정 방법은 2가지 (우리는 배포가 되는 파일이기 때문에, clasPath경로를 사용할 것임)
		// (1)물리적인 파일 경로 : d:\\upload
		// (2)classPath 경로 : class classpath:
		
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("classpath:/kr/or/ddit/ioc/ioc.xml");
		
		//2 과정(DL작업)
		// getBean메소드는 Object를 반환 근데 우리는 UserDao를 반환하기때문에 형변환 필요! 
		UserDao userDao = (UserDao) context.getBean("userDao");
		
		UserVo userVo = userDao.getUser("brown");
		logger.debug("userVo : {}" , userVo);
		
		//스프링 컨테이너로부터 userService 스프링 빈을 DL을 통해 얻어오고 
		// getUser메소드를 call, 반환된 값(userVo)를 logger를 통해 출력하기
		UserService userService = (UserService) context.getBean("userService");
		UserVo userVo2 = userService.getUser("brown");
		logger.debug("userVo : {}" , userVo2);
		
		for(String beanName : context.getBeanDefinitionNames()) {
			logger.debug("beanName: {}", beanName);
		}
	}
}
