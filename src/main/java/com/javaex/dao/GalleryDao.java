package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	SqlSession sqlSession;
	
	//리스트
	public List<GalleryVo> selectList() {
		System.out.println("[GalleryDao] selectList()");
		
		//List<GalleryVo> galleryList = sqlSession.selectList("gallery.selectList");
		//System.out.println("db저장후 다오:" + galleryList.toString());
		return sqlSession.selectList("gallery.selectList");
		
	}
	
	//등록
	public int insert(GalleryVo galleryVo) {
		System.out.println("[GalleryDao] insert()");
		System.out.println("Dao: " + galleryVo);
		
		return sqlSession.insert("gallery.fileInsert", galleryVo);
	}
	
	
}
