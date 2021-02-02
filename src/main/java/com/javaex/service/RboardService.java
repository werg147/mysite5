package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RboardDao;
import com.javaex.vo.RboardVo;

@Service
public class RboardService {
	
	@Autowired
	private RboardDao rboardDao;
	
	//댓글게시판 리스트
	public List<RboardVo> rList() {
		System.out.println("[service] rList()");
		
		return rboardDao.selectRList();
	}

	//읽기
	public RboardVo read(int no) {
		System.out.println("[service] read()");
		
		int count = rboardDao.hitCount(no);
		System.out.println(count);
		
		return rboardDao.selectOne(no);	
	}
	
}
