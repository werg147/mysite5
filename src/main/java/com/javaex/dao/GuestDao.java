package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//리스트 가져오기
	public List<GuestVo> selectList() {
		System.out.println("[GuestDao] selectList()");
		
		//List<GuestVo> guestList = sqlSession.selectList("guest.selectList");
		//System.out.println(guestList.toString());
		
		return sqlSession.selectList("guest.selectList");
	}

	//게시글 등록
	public int insert(GuestVo guestVo) {
		System.out.println("[GuestDao] insert()");
		
		return sqlSession.insert("guest.insert", guestVo);
		
	}
	
	//삭제
	public int delete(GuestVo guestVo) {
		System.out.println("[GuestDao] delete()");
		
		return sqlSession.delete("guest.delete", guestVo);
	}
	
	
	
}
