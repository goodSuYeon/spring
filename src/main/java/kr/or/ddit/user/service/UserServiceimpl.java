package kr.or.ddit.user.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;

@Service
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
	public UserVo getUser(String userid) {
		return userDao.getUser(userid);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	
}
