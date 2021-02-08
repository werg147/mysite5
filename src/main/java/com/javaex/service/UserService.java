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
	
	//회원가입 - 아이디 중복체크
	public String idcheck(String id) {
		System.out.println("userService idcheck()" + id);
		
		UserVo userVo = userDao.selectById(id);
		
		String result = "";
		if(userVo==null) {
			//회원가입 가능
			result = "can";
		} else {
			//회원가입 불가능
			result = "cant";
		}
		
		return result;
	}
	
}
