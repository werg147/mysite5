package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	//리스트2 (리스트+검색 - 키워드로 가져오기)
	public List<BoardVo> selectList2(String keyword){
		System.out.println("[BoardDao] selectList2()");
		System.out.println("keyword= " + keyword);

		return sqlSession.selectList("board.selectList2", keyword);
	}
	
	//리스트3 (검색+페이징)
	public List<BoardVo> selectList3(String keyword, int startRnum, int endRnum){
		System.out.println("[BoardDao] selcetList3()");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		System.out.println(map);
	
		return sqlSession.selectList("board.selectList3", map);
	}
	
	//전체글 갯수 가져오기
	public int selectTotalCnt(String keyword) {
		System.out.println("[BoardDao] selectTotalCnt()");
		
		return sqlSession.selectOne("board.selectTotalCnt", keyword);
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
	
	//조회수 증가
	public int hitCount(int no) {
		System.out.println("[service] hitCount()");

		return sqlSession.update("board.hitCount", no);
	}
	
}
