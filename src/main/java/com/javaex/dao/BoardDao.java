package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;

	//리스트
	public List<BoardVo> selectList() {
		System.out.println("[BoardDao] selectList()");
		
		return sqlSession.selectList("board.selectList");
	}
	
	//게시글 삭제
	public int delete(int no) {
		System.out.println("[BoardDao] delete()");
		
		return sqlSession.delete("board.delete", no);	
	}
	
	//게시글 1개 읽기(read, modifyForm)
	public BoardVo selectOne(int no) {
		System.out.println("[BoardDao] selectOne()");
		
		return sqlSession.selectOne("board.selectOne", no);	
	}
	
	//게시글 작성
	public int insert(BoardVo boardVo) {
		System.out.println("[BoardDao] insert()");
		
		return sqlSession.insert("board.insert", boardVo);	
	}

	//게시글 수정
	public int update(BoardVo boardVo) {
		System.out.println("[BoardDao] update()");
		
		return sqlSession.update("board.update", boardVo);
	}
	
}
