package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/gallery")
public class GalleryController {

	//전체 리스트 출력
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list() {
		System.out.println("[GalleryController] list()");
		
		return "/gallery/list";
	}
	
}
