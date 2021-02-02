package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RboardVo;

@Repository
public class RboardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//댓글 게시판 리스트
	public List<RboardVo> selectRList() {
		System.out.println("[RboardDao] selectRList()");
		
		return sqlSession.selectList("rboard.selectRList");
	}

	//읽기
	public RboardVo selectOne(int no) {
		System.out.println("[RboardDao] selectOne()");
		
		return sqlSession.selectOne("rboard.selectOne", no);	
	}
	
	//조회수
	public int hitCount(int no) {
		System.out.println("[service] hitCount()");

		return sqlSession.update("rboard.hitCount", no);
	}
	
}
