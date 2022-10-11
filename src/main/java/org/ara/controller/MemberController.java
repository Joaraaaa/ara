package org.ara.controller;

import javax.servlet.http.HttpSession;

import org.ara.model.MemberVO;
import org.ara.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {
	@Autowired
	MemberService ms;
	
	// 회원가입
	@RequestMapping(value = "/member/signup", method = RequestMethod.GET)
	public String signup(HttpSession session) {
		session.invalidate();
		return "member/signup";
	}

	@RequestMapping(value = "/member/signup", method = RequestMethod.POST)
	public String signupin(MemberVO member) {
		try {
			ms.signUp(member);
			return "/";
//			return "member/login";
		} catch (Exception e) {
			e.printStackTrace();
			return "member/signup";
		}
	}
	
	// 로그인
		@RequestMapping(value = "/member/login", method = RequestMethod.GET)
		public String login(HttpSession session,Model model) {
			session.invalidate();
			return "member/login";
		}

		@RequestMapping(value = "/member/login", method = RequestMethod.POST)
		public String loginPost(MemberVO member, HttpSession session) {
			System.out.println(ms.login(member));
			session.setAttribute("userInfo", ms.login(member));
			System.out.println(session.getAttribute("userInfo"));
			if (session.getAttribute("userInfo") != null) {

				return "redirect:/";
			} else {
				return "member/login";
			}
		}
}
