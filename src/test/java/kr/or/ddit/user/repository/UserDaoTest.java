package kr.or.ddit.user.repository;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;

//Spring환경에서 Junit코드를 실행 (즉,Junit코드도 스프링 bean으로 등록해야함)

public class UserDaoTest extends ModelTestConfig{

	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Test
	public void selectUserTest() {
		/*** Given ***/
		String userid = "brown";
		
		/*** When ***/
		UserVo userVo = userDao.selectUser(userid);
		
		/*** Then ***/
		assertEquals("브라운", userVo.getUsernm());
	}
	
	@Test
	public void selectAllUserTest() {
		/*** Given ***/
		
		/*** When ***/
		List<UserVo> userList = userDao.selectAllUser();
		logger.debug("userList:{}", userList);
		/*** Then ***/
		assertEquals(20, userList.size());
	}
	
	//사용자 페이징 조회 테스트
		@Test
		public void selectPagingUserTest() {
			/***Given***/
			PageVo pageVo = new PageVo(2, 5);
			
			/***When***/
			List<UserVo> userList = userDao.selectPagingUser(pageVo);

			/***Then***/
			assertEquals(5, userList.size());
		}
		
	@Test
	public void selectAllUserCntTest() {
		/*** Given ***/
		/*** When ***/
		int userCnt = userDao.selectAllUserCnt();
		logger.debug("userCnt:{}",userCnt);
		/*** Then ***/
		assertEquals(19, userCnt);
	}
	
	@Test
	public void modifyUserTest() {
		/***Given***/
		
		//userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode
		UserVo userVo = new UserVo("eee", "김뚜니", "eepass", "뚠뚜니",
								"대전시 중구 중앙로 76", "4층 대덕인재개발원", "34940","푸우","푸우.png" ,new Date());
		
		/***When***/
		int updateCnt = userDao.modifyUser(userVo);

		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void registUserTest() {
		/***Given***/
				
		//userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode
		UserVo userVo = new UserVo("sususu", "김뚜", "susuPass", "김쑬",
					"대전시 중구 중앙로 76", "4층 대덕인재개발원", "34940","푸우","푸우.png" ,new Date());
		
		/***When***/
		int insertCnt = userDao.registerUser(userVo);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	//삭제 테스트
	@Test
	public void deleteUserTest() {
		/***Given***/
		//해당 테스트가 실행될 때는 testUser라는 사용자가 before 메소드에 의해 등록이 된상태
		String userid = "sususu";
		
		/***When***/
		int deleteCnt = userDao.deleteUser(userid);

		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	
}

