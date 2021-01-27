package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//회원가입 -> 회원정보 저장
	public int insert(UserVo userVo) {
		System.out.println("Dao insert:" + userVo);
		System.out.println(userVo.toString());
		
		return sqlSession.insert("user.insert", userVo);
	}
	
	//로그인 -> 회원정보 가져오기
	public UserVo selectUser(UserVo userVo) {
		System.out.println("Dao selectUser: " + userVo);
		System.out.println(userVo.toString());
		
		UserVo vo = sqlSession.selectOne("user.selectUser", userVo);
		//System.out.println(vo.toString());
		
		return vo;
	}
	
	//회원정보수정 폼 -> 로그인한 유저 정보(1명) 가져오기
	public UserVo getUser(int no) {
		System.out.println("Dao getUser: " + no);
		
		UserVo userVo = sqlSession.selectOne("user.selectOne", no);
		//System.out.println(userVo.toString());
		
		return userVo;
	}
	
	//정보 수정
	public void update(UserVo userVo) {
		System.out.println("Dao update" + userVo);
	
		sqlSession.update("user.update", userVo);

	}
	
	
}
