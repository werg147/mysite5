package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	//회원가입
	public int join(UserVo userVo) {
		System.out.println("UserService join()");
		
		//int count = userDao.insert(userVo);
		return userDao.insert(userVo);
	}
	
	//로그인
	public UserVo login(UserVo userVo) {
		System.out.println("UserService login()");
		return userDao.selectUser(userVo);
	}
	
	//회원정보 수정폼
	public UserVo modifyForm(int no) {
		System.out.println("UserService modifyForm()");
		System.out.println(no);
		
		return userDao.getUser(no);
	}
	
	//회원정보 수정
	public int update(UserVo userVo) {
		System.out.println("userService update");
		return userDao.update(userVo);
		
	}
	
}
