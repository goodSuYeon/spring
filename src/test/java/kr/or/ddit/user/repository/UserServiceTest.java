package kr.or.ddit.user.repository;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

public class UserServiceTest extends ModelTestConfig{

	@Resource(name="userService")
	private UserService userService;
	@Test
	public void test() {
		/***Given***/
		String userid = "brown";

		/***When***/
		UserVo userVo = userService.selectUser(userid);
		
		/***Then***/
		assertEquals("브라운", userVo.getUsernm());
	}
	
		//전체 사용자 조회 테스트
		@Test
		public void selectAllUserTest() {
			/***Given***/
			
			/***When***/
			List<UserVo> userList = userService.selectAllUser();

			/***Then***/
			assertEquals(20, userList.size());
		}
		
		//사용자 아이디를 이용하여 특정 사용자 정보 조회
		@Test
		public void selectUserTest() {
			/***Given***/
			String userid = "brown";

			/***When***/
			UserVo user	= userService.selectUser(userid);

			/***Then***/
			assertNotNull(user);
			assertEquals("브라운", user.getUsernm());
		}
		
		//사용자 아이디가 존재하지 않을 때, 특정 사용자 조회
		@Test
		public void selectUserNotExsistTest() {
			/***Given***/
			String userid = "brownNotexists";

			/***When***/
			UserVo user	= userService.selectUser(userid);

			/***Then***/
			assertNull(user);
		}
		
		
		//사용자 페이징 조회 테스트
		@Test
		public void selectPagingUserTest() {
			/***Given***/
			PageVo pageVo = new PageVo(2, 5);
			
			/***When***/
			//List<UserVo> userList = userDao.selectPagingUser(page, pagesize);
			Map<String, Object> map = userService.selectPagingUser(pageVo);
			List<UserVo> userList = (List<UserVo>)map.get("userList");
			int userCnt = (int)map.get("userCnt");

			/***Then***/
			assertEquals(5, userList.size());
			assertEquals(20, userCnt);
		}
		
		@Test
		public void modifyUserTest() {
			/***Given***/
			
			//userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode
			UserVo userVo = new UserVo("dd", "김뚜니", "eepass", "뚠뚜니",
						"대전시 중구 중앙로 76", "4층 대덕인재개발원", "34940","푸우","푸우.png" ,new Date());
			
			/***When***/
			int updateCnt = userService.modifyUser(userVo);

			/***Then***/
			assertEquals(1, updateCnt);
		}
		
		@Test
		public void registUserTest() {
			/***Given***/
			
			//userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode
			UserVo userVo = new UserVo("dd", "대덕인재", "dditPass",
									   "개발원_m", "대전시 중구 중앙로 76", "4층 대덕인재개발원", "34940","푸우","푸우.png", new Date());
			
			/***When***/
			int insertCnt = userService.registerUser(userVo);

			/***Then***/
			assertEquals(1, insertCnt);
		}

}
