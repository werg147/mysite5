package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.RboardService;
import com.javaex.vo.RboardVo;

@Controller
@RequestMapping(value="/rboard")
public class RboardController {
	
	@Autowired
	private RboardService rboardService;

	//댓글게시판 리스트
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String rList(Model model) {
		System.out.println("[RboardController] rList()");
		
		List<RboardVo> rboardList = rboardService.rList();
		System.out.println(rboardList.toString());
		
		model.addAttribute("rboardList", rboardList);
		
		return "rboard/list";
	}
	
	//읽기
	@RequestMapping(value="/read/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String getRead(@PathVariable("no") int no, Model model) {
		System.out.println("[controller] getRead()");
		
		RboardVo rboardVo = rboardService.read(no);
		System.out.println(rboardVo);
		
		model.addAttribute("rboardVo", rboardVo);
		
		return "rboard/read";
		
	}
	
	//게시글 쓰기
	@RequestMapping(value="/write/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@PathVariable("no") int no) {
		System.out.println("[controller] write()");
		
		return "";
	}
	
	
	//댓글쓰기 폼
	@RequestMapping(value="/commentForm/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String commentForm(@ModelAttribute RboardVo rboardVo) {
		System.out.println("[controller] commentForm()");
			
		return "rboard/commentForm";
	}
	
	//댓글쓰기
	@RequestMapping(value="/comment/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String comment() {
		System.out.println("[controller] comment()");
		
		return "";
		
	}
	
	
	
}
