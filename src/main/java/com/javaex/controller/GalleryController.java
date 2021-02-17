package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/gallery")
public class GalleryController {
	
	@Autowired
	GalleryService galleryService;

	//전체 리스트 출력
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("[GalleryController] list()");
		
		galleryService.list();
		List<GalleryVo> galleryList = galleryService.list();
		System.out.println("컨트롤러" + galleryList.toString());
		
		model.addAttribute("galleryList", galleryList);
		System.out.println("모델에담고" + galleryList.toString());
		
		return "/gallery/list";
	}
	
	//첨부파일 업로드
	@RequestMapping(value="/upload", method= {RequestMethod.GET,RequestMethod.POST})
	public String upload(@ModelAttribute GalleryVo galleryVo, @RequestParam("file") MultipartFile file, HttpSession session) {
		System.out.println("[GalleryController] upload()");
		System.out.println(galleryVo);
		System.out.println(file.getOriginalFilename());
		
		//로그인 유저 no 받아서 담기
		UserVo userVo = (UserVo)session.getAttribute("authUser"); //형변환 Object->UserVo
		galleryVo.setUserNo(userVo.getNo());
		
		galleryService.upload(galleryVo, file);
		
		return "redirect:/gallery/list";
	}
	
	
}
