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
import org.springframework.web.bind.annotation.ResponseBody;
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
		//System.out.println("컨트롤러" + galleryList.toString());
		
		model.addAttribute("galleryList", galleryList);
		//System.out.println("모델에담고" + galleryList.toString());
		
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
	
	//게시글 한개 가져오기
	@ResponseBody     //꼭 !! 추가하는거 잊지않기 (콘솔창 404에러 -> 데이터 받지못함)
	@RequestMapping(value="/read", method= {RequestMethod.GET, RequestMethod.POST})
	public GalleryVo read(@RequestParam("no") int no) {
		System.out.println("[GalleryController] read()");
		System.out.println(no);
		
		GalleryVo galleryVo = galleryService.read(no);
		//System.out.println("컨트롤러: " + galleryVo);
		
		return galleryVo;
	}
	
	//게시글 삭제
	@ResponseBody
	@RequestMapping(value="/remove", method= {RequestMethod.GET, RequestMethod.POST})
	public int remove(@RequestParam("no") int no) {
		System.out.println("[GalleryController] remove()");
		
		int count = galleryService.remove(no);
		System.out.println(count);
		
		return count;
	}
	
}
