package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	GalleryDao galleryDao;

	//리스트 출력
	public List<GalleryVo> list() {
		System.out.println("[GalleryService] list()");

		return galleryDao.selectList();
	}
	
	
	//갤러리 게시글 등록
	public void upload(GalleryVo galleryVo, MultipartFile file) {
		System.out.println("[GalleryService] upload()");
		
		//db저장 할 정보 수집
		String saveDir = "C:\\javastudy\\upload";
		
		//오리지널 파일이름
		String orgName = file.getOriginalFilename();
		System.out.println(orgName);
		
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println(exName);
		
		//서버저장 파일이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println(saveName);
		
		//서버 파일패스 -> 저장경로
		String filePath = saveDir + "\\" + saveName;
		System.out.println(filePath);
		
		//파일사이즈
		long fileSize = file.getSize();
		System.out.println(fileSize);
		
		//서버 하드디스크 저장
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(out);
			
			bos.write(fileData);
			bos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//db저장
		galleryVo.setFilePath(filePath);
		galleryVo.setOrgName(orgName);
		galleryVo.setSaveName(saveName);
		galleryVo.setFileSize(fileSize);
		
		galleryDao.insert(galleryVo);
		
	}
	
	
}
