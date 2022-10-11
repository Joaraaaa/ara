package org.ara.controller;

import javax.servlet.http.HttpSession;

import org.ara.model.MemberVO;
import org.ara.service.MemberService;
import org.ara.service.MailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
	@Autowired
	MemberService ms;

	@Autowired
	private MailSendService mailService;
	
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
			return "member/login";
		} catch (Exception e) {
			e.printStackTrace();
			return "member/signup";
		}
	}
	
	// 이메일 인증
	@RequestMapping(value = "/member/emailchk/{email}/", method = RequestMethod.GET)
	@ResponseBody
	public String mailCheck(@PathVariable String email) {
		System.out.println("이메일 인증 요청이 들어옴!");
		System.out.println("이메일 인증 이메일 : " + email);
		return mailService.joinEmail(email);

	}

	// 아이디, 이메일, 전화번호 중복체크
	@RequestMapping(value = "/member/signup/{str}", method = RequestMethod.GET)
	public ResponseEntity<String> emchk(@PathVariable String str) {
		try {
			if (str.contains("@")) {
				System.out.println(str);
				return new ResponseEntity<>(ms.emchk(str).getEmail(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(ms.idchk(str).getId(), HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
