package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//리스트
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String getList(Model model) {
		System.out.println("[controller] getList()");
		
		List<BoardVo> boardList = boardService.getList();
		
		model.addAttribute("boardList", boardList);
		System.out.println(boardList.toString());
		
		return "board/list";
	}
	
	//리스트2(검색기능 추가)
	@RequestMapping(value="/list2", method= {RequestMethod.GET, RequestMethod.POST})
	public String list2(@RequestParam(value="keyword", required=false, defaultValue="") String keyword, Model model) {
		System.out.println("[controller] list2()");
		System.out.println("keyword= " + keyword);
		
		List<BoardVo> boardList = boardService.getList2(keyword);
		
		model.addAttribute("boardList", boardList);
		
		return "board/list2";
	}
	
	//리스트3 (검색+페이징 기능 추가)
	@RequestMapping(value="/list3")
	public String list3(@RequestParam(value="keyword", required=false, defaultValue="") String keyword,
			            @RequestParam(value="crtPage", required=false, defaultValue="1") int crtPage,
			            Model model){
		System.out.println("[controller] list3()");
		//System.out.println("keyword= " + keyword);
		//System.out.println("crtPage= " + crtPage);
		
		Map<String, Object> pMap = boardService.getList3(keyword, crtPage);
		System.out.println(pMap);
		
		model.addAttribute("pMap", pMap);
		
		return "board/list3";
	}
	
	
	//게시글 삭제
	@RequestMapping(value="/remove/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String remove(@PathVariable("no") int no) {
		System.out.println("[controller] remove()");
		
		int count = boardService.remove(no);
		System.out.println(count);
		
		return "redirect:/board/list";
	}

	//게시글 읽기
	@RequestMapping(value="/read/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String getRead(@PathVariable("no") int no, Model model) {
		System.out.println("[controller] getRead()");
		
		BoardVo boardVo = boardService.getRead(no);
		System.out.println(boardVo);
		
		model.addAttribute("boardVo", boardVo);
		
		return "board/read";
	}
	
	//글쓰기 폼
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("[controller] writeForm");
		
		return "board/writeForm";
	}
	
	//게시글 작성
	@RequestMapping(value="/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute BoardVo boardVo) {
		System.out.println("[controller] write()");
		
		int count = boardService.write(boardVo);
		System.out.println(count);
		
		return "redirect:/board/list";
	}
	
	//게시글 수정폼
	@RequestMapping(value="/modifyForm/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@PathVariable("no") int no, Model model) {
		System.out.println("[controller] modifyForm()");
		
		BoardVo boardVo = boardService.modifyForm(no);
		System.out.println(boardVo.toString());
		
		model.addAttribute("boardVo", boardVo);
		
		return "board/modifyForm";
	}
	
	//게시글 수정
	@RequestMapping(value="/modify", method= {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("[controller] modify()");
		
		int count = boardService.modify(boardVo);
		System.out.println(count);
		
		return "redirect:/board/list";
	}
	
	
}
