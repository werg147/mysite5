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
	public int inert(GuestVo guestVo) {
		System.out.println("[GuestService] insert()");
		
		return guestDao.insert(guestVo);	
	}
	
	//삭제
	public int delete(GuestVo guestVo) {
		System.out.println("[GuestService] delete()");
		
		return guestDao.delete(guestVo);
		
	}
	
	
}
