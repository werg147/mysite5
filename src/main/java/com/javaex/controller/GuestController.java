package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping(value="/guest")
public class GuestController {

	@Autowired
	private GuestService guestService;
	
	//방명록 리스트
	@RequestMapping(value="/addList", method= {RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("[controller] addList()");
		//리스트 가져오기
		List<GuestVo> guestList = guestService.selectList();
		
		//model에 담기
		model.addAttribute("guestList", guestList);
		
		return "guestbook/addList";
	}
	
	//게시글 등록
	@RequestMapping(value="/insert", method= {RequestMethod.GET, RequestMethod.POST})
	public String insert(@ModelAttribute GuestVo guestVo) {
		System.out.println("[controller] insert()");
		 
		int count = guestService.insert(guestVo);
		System.out.println(count);
		System.out.println(guestVo.toString());
		
		return "redirect:/guest/addList";
	}
	
	//삭제 폼
	@RequestMapping(value="/deleteForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm() {
		System.out.println("[controller] deleteForm()");
		
		return "guestbook/deleteForm";
	}
	
	//삭제
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestVo guestVo) {
		System.out.println("[controller] delete()");
		
		int count = guestService.delete(guestVo);
		//System.out.println(count);
		
		if(count==1) {
			return "redirect:/guest/addList";
		} else {
			return "redirect:/guest/deleteForm?no=" + guestVo.getNo() + "&result=fail";
		}
		
	}
	
	//ajaxList
	@RequestMapping(value="/ajaxList", method= {RequestMethod.GET, RequestMethod.POST})
	public String ajaxList() {
		System.out.println("[controller] ajaxList()");
		
		return "guestbook/ajaxList";
	}
	
}
