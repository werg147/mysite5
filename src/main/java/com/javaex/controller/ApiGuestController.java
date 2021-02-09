package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping(value="/api/guest")
public class ApiGuestController {

	@Autowired
	private GuestService guestService;
	
	//전체 리스트 가져오기 (ajax)
	@ResponseBody
	@RequestMapping(value="/list")
	public List<GuestVo> list() {
		System.out.println("[ApiGuestController] list()");
		
		return guestService.selectList();
	}
	
	
	//글 작성(ajax)
	@ResponseBody
	@RequestMapping(value="/write")
	public GuestVo write(@ModelAttribute GuestVo guestVo) {
		System.out.println("[ApiGuestController] write()");
		System.out.println(guestVo);
		
		//입력된 vo값을 전달하고 DB에 저장된 vo를 받는다
		return guestService.writeResultVo(guestVo);
	}
	
	
}
