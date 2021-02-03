package kr.or.ddit.user.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;

@Service("userService")
public class UserServiceimpl implements UserService {

	@Resource(name="userDao")
	private UserDao userDao;
	
	//인자가 없는 생성자
	public UserServiceimpl() {}
	
	//인자가 있는 생성자
	public UserServiceimpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public UserVo selectUser(String userid) {
		return userDao.selectUser(userid);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<UserVo> selectAllUser() {
		return userDao.selectAllUser();
	}

	@Override
	public Map<String, Object> selectPagingUser(PageVo pageVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		int userCnt = userDao.selectAllUserCnt();
		   
		resultMap.put("pageVo", pageVo);
		resultMap.put("userList", userDao.selectPagingUser(pageVo));
		resultMap.put("pagination", (int)Math.ceil((double)userCnt/ pageVo.getPageSize()));
		
		return resultMap;
	}

	@Override
	public int modifyUser(UserVo userVo) {
		return userDao.modifyUser(userVo);
	}

	@Override
	public int registerUser(UserVo userVo) {
		return userDao.registerUser(userVo);
	}

	@Override
	public int deleteUser(String userid) {
		return userDao.deleteUser(userid);
	}

}
