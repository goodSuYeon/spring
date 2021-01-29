package kr.or.ddit.user.repository;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.model.UserVo;

//Spring환경에서 Junit코드를 실행 (즉,Junit코드도 스프링 bean으로 등록해야함)

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/kr/or/ddit/ioc/ioc.xml")
public class UserDaoTest {

	@Resource(name="userDao")
	private UserDao userDao;
	
	@Test
	public void test() {
		/*** Given ***/
		String userid = "brown";
		
		/*** When ***/
		UserVo userVo = userDao.getUser(userid);
		
		/*** Then ***/
		assertEquals("브라운", userVo.getUsernm());
	}

}

