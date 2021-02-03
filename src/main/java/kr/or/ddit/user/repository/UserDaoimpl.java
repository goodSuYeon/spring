package kr.or.ddit.user.repository;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

// <bean id="" class="" 
// @Repository에서 별 다른 설정을 하지 않으면 스프링 bean 이름으로 class이름에서 첫글자를 소문자로 한
// 문자열이 스프링 빈의 이름으로 설정돈다.
// UserDaoImpl ==> userDaoImpl

// UserDao / UserDaoimpl -> @Resource(name="userDaoimpl")
// UserDaoI / UserDao    -> @Resource(name="userDao")

@Repository("userDao")
public class UserDaoimpl implements UserDao{

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	@Override
	public UserVo selectUser(String userid) {
		/*원래는 DB에서 조회를 해야하나, 개발 초기단계이기 때문에
		  설정이 완료되지 않음, 현재 확인하려고 하는 기능은 스프링 컨테이너에 초점을 맞추기 위해
		  NEW 연산자를 통해 생성한 vo객체를 반환한다
		*/
		return template.selectOne("users.selectUser", userid);
	}

	@Override
	public List<UserVo> selectAllUser() {
		return template.selectList("users.selectAllUser");
	}

	@Override
	public List<UserVo> selectPagingUser(PageVo vo) {
		return template.selectList("users.selectPagingUser", vo);
	}

	@Override
	public int selectAllUserCnt() {
		return template.selectOne("users.selectAllUserCnt");
	}

	@Override
	public int modifyUser(UserVo userVo) {
		return template.update("users.modifyUser", userVo);
	}

	@Override
	public int registerUser(UserVo userVo) {
		return template.insert("users.registerUser", userVo);
	}

	@Override
	public int deleteUser(String userid) {
		return template.delete("users.deleteUser", userid);
	}
	
}
