package kr.or.ddit.user.repository;

import java.util.List;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface UserDao {
	//사용자 아이디로 사용자 조회
	UserVo selectUser(String userid);

	//전체 사용자 정보 조회
	List<UserVo> selectAllUser();

	//페이징처리
	List<UserVo> selectPagingUser(PageVo vo);

	//사용자 전체 수 조회
	int selectAllUserCnt();
		
	//사용자 정보 수정
	int modifyUser(UserVo userVo);
		
	//사용자 신규 등록
	int registerUser(UserVo userVo);
			
	// 사용자 삭제 
	int deleteUser(String userid);
}
