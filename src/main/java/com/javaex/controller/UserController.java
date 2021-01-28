package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.UserDao;
import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	//필드
	@Autowired
	private UserService userService;
	
	
	//회원가입 폼
	@RequestMapping(value = "/joinForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("/user/joinForm");
		
		return "user/joinForm";
	}
	
	//회원가입
	@RequestMapping(value = "/join", method= {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("/user/join");
		System.out.println(userVo.toString());
		
		int count = userService.join(userVo);
		System.out.println(count);
		
		return "user/joinOk";
	}
	
	//로그인 폼
	@RequestMapping(value = "/loginForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("/user/loginForm");
		
		return "user/loginForm";
	}
	
	//로그인
	@RequestMapping(value = "/login", method= {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("/user/login");
		//System.out.println(userVo.toString());
		
		UserVo authUser = userService.login(userVo);
		
		if(authUser == null) {
			//실패시
			return "redirect:/user/loginForm?result=fail";
		} else {
			// 성공시
			session.setAttribute("authUser", authUser);
			return "redirect:/";
		}
	}
	
	//로그아웃
	@RequestMapping(value = "/logout", method= {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("/user/logout");
		
		//세션 vo값 삭제
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
	//수정 폼
	@RequestMapping(value = "/modifyForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(HttpSession session, Model model) {
		System.out.println("/user/modifyForm");
		
		//1명 정보 가져오기 - 세션영역의 no로
		int no = ((UserVo)session.getAttribute("authUser")).getNo();
		//UserVo authUser = (UserVo)session.getAttribute("authUser");
		//int no = authUser.getNo();
		
		//세션값이 없으면 -> 로그인 폼
		
		UserVo userVo = userService.modifyForm(no);
		
		//모델에 데이터 담기
		model.addAttribute("userVo", userVo);
		
		return "user/modifyForm";
	}
	
	//수정하기
	@RequestMapping(value = "/update", method= {RequestMethod.GET, RequestMethod.POST})
	public String update(HttpSession session, @ModelAttribute UserVo userVo) {
		System.out.println("/usre/update");
        //System.out.println(userVo.toString());
		
		//세션 no꺼내기
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int no = authUser.getNo();
		
		//vo에 no담기
		userVo.setNo(no);
		
		//정보 수정
		int count = userService.update(userVo);
		
		//세션 name 업데이트
		authUser.setName(userVo.getName());
		
		return "redirect:/";
	}
	
	
	
	
}
