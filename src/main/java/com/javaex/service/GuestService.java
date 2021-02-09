package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Service
public class GuestService {
	
	@Autowired
	private GuestDao guestDao;
	
	//리스트 가져오기
	public List<GuestVo> selectList() {
		System.out.println("[GuestService] selectList()");
		
		return guestDao.selectList();
	}

	//게시글 등록
	public int insert(GuestVo guestVo) {
		System.out.println("[GuestService] insert()");
		
		return guestDao.insert(guestVo);	
	}
	
	//삭제
	public int delete(GuestVo guestVo) {
		System.out.println("[GuestService] delete()");
		
		return guestDao.delete(guestVo);
		
	}
	
	//ajax 글 저장 -> 저장된 글 리턴
	public GuestVo writeResultVo(GuestVo guestVo) {
		//글 저장
		//int no = guestDao.insertSelectKey(guestVo);
		System.out.println("service: dao.insertSelectKey()실행전->" + guestVo);
		
		guestDao.insertSelectKey(guestVo);
		
		System.out.println("service: dao.insertSelectKey()실행후->" + guestVo);
		int no = guestVo.getNo();
		
		//글 1개 가져오기
		return guestDao.selectOne(no);
	}
	
	
}
