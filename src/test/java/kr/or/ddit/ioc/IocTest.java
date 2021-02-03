package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/kr/or/ddit/ioc/ioc.xml")
public class IocTest {

	//userServiceCons 스프링 bean이 정상적으로 생성되었는지 확인하기 위한 테스트작업
//	@Resource(name="userService")
    @Autowired
 	private UserService userService;
	
	@Resource(name="userService")
	private UserService userService2;
	
	@Resource(name="userServiceCons")
	private UserService userServiceCons;
	
	@Resource(name="userServicePrototype")
	private UserService userServicePrototype;
	
	@Resource(name="userServicePrototype")
	private UserService userServicePrototype2;
	
	@Resource(name="dbConfig")
	private DbConfig dbConfig;
	
	@Test
	public void userServiceConstest() {
		/***Given***/
		

		/***When***/

		/***Then***/
		assertNotNull(userServiceCons);
	}
	
	@Test
	public void beanScopeTest() {
		/***Given***/
		/***When***/
		/***Then***/
		//디자인 패턴의 싱글톤 개념으로 보면, 
		//두개의 객체는 한 클래스로부터 나왔으므로 동일해야한다
		//하지만 스프링의 싱글톤 개념으로 bean앨레맨트를 기준으로 하나의 객체가 생성된다
		assertNotEquals(userService, userServiceCons);

	}
	
	@Test
	public void beanScopeTest2() {
		/***Given***/	
		/***When***/
		/***Then***/
		//동일한 스프링 bean을 주입받았으므로, userService, userService2는 같은 객체이다.
		assertNotEquals(userService, userService2);
	}
	
	@Test
	public void propertyPlaceholderTest() {
		/***Given***/	
		/***When***/
		/***Then***/
		assertNotNull(dbConfig);
		assertEquals("oracle.jdbc.driver.OracleDriver", dbConfig.getDriverClassName());
		assertEquals("jdbc:oracle:thin:@localhost:1521:xe", dbConfig.getUrl());
		assertEquals("su", dbConfig.getUsername());
		assertEquals("java", dbConfig.getPassword());

	}
	
}
