package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	//리스트
	public List<BoardVo> getList() {
		System.out.println("[service] getList()");
		
		return boardDao.selectList();
	}
	
	//게시글 삭제
	public int remove(int no) {
		System.out.println("[service] remove()");
		
		return boardDao.delete(no);
	}
	
	//게시글 읽기
	public BoardVo getRead(int no) {
		System.out.println("[service] getRead()");
		
		int count = boardDao.hitCount(no);
		System.out.println(count);
		
		return boardDao.selectOne(no);	
	}
	
	//게시글 작성
	public int write(BoardVo boardVo) {
		System.out.println("[service] write()");
		
		return boardDao.insert(boardVo);
		
	}
	
	//게시글 수정폼
	public BoardVo modifyForm(int no) {
		System.out.println("[service] modifyForm()");
		
		return boardDao.selectOne(no);	
	}
	
	//게시글 수정
	public int modify(BoardVo boardVo) {
		System.out.println("[service] modify()");
		
		return boardDao.update(boardVo);
	}
	
}
