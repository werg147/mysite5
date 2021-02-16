package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	SqlSession sqlSession;
	
	//리스트
	
	
	//등록
	public void insert(GalleryVo galleryVo) {
		System.out.println("[GalleryDao] insert()");
		
		sqlSession.insert("gallery.fileInsert", galleryVo);
	}
	
	
}
