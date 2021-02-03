package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.config.ComponentScanJavaConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;
import kr.or.ddit.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ComponentScanJavaConfig.class})
public class ComponentJavaScanTest {

	/* @Repository 어노테이션을 적용한 userDaoImpl 스프링 bean이 
	   정상적으로 컨테이너에 등록되었는지 확인하는 테스트
	*/
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="userServiceimpl")
	private UserService userService;
	
	@Test
	public void userDaoImplSpringtest() {
		assertNotNull(userDao);
		
		UserVo userVo = userDao.selectUser("brown");
		assertEquals("브라운", userVo.getUsernm());
	}
	
	//userServiceimpl 스프링 bean이 정상적으로 컨테이너에 등록이 되었는지 확인하는 테스트
	@Test
	public void userServiceImplSpringtest() {
		assertNotNull(userService);
		
		UserVo userVo = userService.selectUser("brown");
		assertEquals("브라운", userVo.getUsernm());
	}
}
