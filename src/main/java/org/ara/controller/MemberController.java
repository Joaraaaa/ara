package org.ara.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ara.model.MemberVO;
import org.ara.service.MemberService;
import org.json.XML;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.ara.service.GetBuisnessInfoService;
import org.ara.service.GetUserInfoService;
import org.ara.service.RestJsonService;
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
	
	// 회원가입 화면
	@RequestMapping(value = "/member/signup", method = RequestMethod.GET)
	public String signup(HttpSession session) {
		session.invalidate();
		return "member/signup";
	}

	// 사업자 회원가입 화면
	@RequestMapping(value = "/member/bsignup", method = RequestMethod.GET)
	public String signupB(HttpSession session) {
		session.invalidate();
		return "member/bsignup";
	}

	@RequestMapping(value = "/member/signup", method = RequestMethod.POST)
	public String signup(MemberVO member,HttpSession session) {
		System.out.println("member="+member);
		try {
			ms.signUp(member);
			session.setAttribute("userInfo", ms.login(member));
			if(member.isAdmin()==true) {
				return "redirect:/bhome";
			}else {
				return "redirect:/nhome";
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(member.isAdmin()==true) {
				return "member/bsignup";
			}else {
				return "member/signup";
			}
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

	// 이메일, 닉네임 중복체크
	@RequestMapping(value = "/member/signup/{str}", method = RequestMethod.GET)
	public ResponseEntity<String> emchk(@PathVariable String str) {
		try {
			if (str.contains("@")) {
				System.out.println(str);
				return new ResponseEntity<>(ms.emchk(str).getEmail(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(ms.nachk(str).getName(), HttpStatus.OK);
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
			if(member.isAdmin()==true) {
				return "redirect:/bhome";
			}else {
				return "redirect:/nhome";
			}
		} else {
			return "member/login";
		}
	}
	// 카카오 로그인(js에서 js키 사용해서 서버로 인증코드 보내기 -> 인증코드 사용해서 엑세스 코드 받기 -> 엑세스 토큰 사용해서 사용자 정보 받기)
	// 1번-login.js 확인하기. RestJsonService.java , GetUserInfoService.java , pom.xml의 문자열json변환(추가) 확인하기
	@RequestMapping(value = "/snscheck", method = RequestMethod.GET)
	public String snsCheck(String code,HttpSession session,Model model) {

		// 2. 받은 인증코드를 카카오로 보내서 엑세스 토큰을 받아내는 클래스(서비스에 만들어져있음.)
		RestJsonService restJsonService = new RestJsonService();

		//access_token이 포함된 JSON String을 받아온다.
		String accessTokenJsonData = restJsonService.getAccessTokenJsonData(code);
		System.out.println(accessTokenJsonData);
		if(accessTokenJsonData=="error") return "error";

		//JSON String -> JSON Object

		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(accessTokenJsonData);
			JSONObject jsonObj = (JSONObject) obj;
			System.out.println(jsonObj.get("access_token"));
			String accessToken = (String) jsonObj.get("access_token");
			session.setAttribute("accessToken",accessToken);

			// 3. 받아낸 엑세스 토큰을 보내서 사용자 정보를 받아내는 클래스(서비스에 만들어져있음.)
			GetUserInfoService getUserInfoService = new GetUserInfoService();
			//유저 정보가 포함된 JSON String을 받아온다.
			String userInfo = getUserInfoService.getUserInfo(accessToken);
			System.out.println(userInfo);
			//JSON String -> JSON Object
			JSONParser userInfoParser = new JSONParser();
			Object uobj = userInfoParser.parse(userInfo);
			JSONObject userInfojsonObj = (JSONObject) uobj;
			System.out.println(userInfojsonObj.get("id"));

			// 4. 사용자 정보 추출
			JSONObject kakaoAccountJsonObject = (JSONObject)userInfojsonObj.get("kakao_account");
			JSONObject propertiesJsonObject = (JSONObject)userInfojsonObj.get("properties");
			String email = kakaoAccountJsonObject.get("email").toString();
			String id = userInfojsonObj.get("id").toString();
			String nickname = propertiesJsonObject.get("nickname").toString();
			String birthday = kakaoAccountJsonObject.get("birthday").toString();
			String month = birthday.substring(0,2);
			String day = birthday.substring(2);

			if(month.indexOf("0")==0) {
				month=month.substring(1);
			}
			if(day.indexOf("0")==0) {
				day=day.substring(1);
			}
			// 5. MemberVO에 담기. ---- 끝!
			MemberVO member = new MemberVO();
			member.setEmail(email);
			member.setPassword(id);
			member.setName(nickname);
			member.setBirth_m(month);
			member.setBirth_d(day);

			System.out.println(member);
			MemberVO mvo = new MemberVO();
			mvo= ms.snsCheck(member.getEmail());
			try {
				ms.snsSignup(member);
			}catch(Exception e) {

				e.printStackTrace();
			}
			session.setAttribute("userInfo",ms.login(member));

		} catch (ParseException e) {
			e.printStackTrace();
		}


		return "redirect:/";
	}
	
	@RequestMapping(value = "/buisnesscheck", produces = "application/text; charset=UTF-8", method = RequestMethod.GET)
	public ResponseEntity<String> buisnessCheck(String num,HttpServletResponse response) {
		System.out.println(num);
		GetBuisnessInfoService getBuisnessInfoService = new GetBuisnessInfoService();
		//유저 정보가 포함된 JSON 을 받아온다.
		org.json.JSONObject userInfo = getBuisnessInfoService.getUserInfo(num);
		System.out.println(userInfo);
		org.json.JSONObject responseJsonObject = (org.json.JSONObject) userInfo.get("response");
		org.json.JSONObject bodyJsonObject = (org.json.JSONObject) responseJsonObject.get("body");
		org.json.JSONObject itemsJsonObject = (org.json.JSONObject) bodyJsonObject.get("items");
		org.json.JSONObject itemJsonObject = (org.json.JSONObject) itemsJsonObject.get("item");
		System.out.println(itemJsonObject);
		String company = itemJsonObject.get("company").toString();
		System.out.println(company);
		return new ResponseEntity<>(company,HttpStatus.OK);
	}	
	
	@RequestMapping(value = "/makename", produces = "application/text; charset=UTF-8", method = RequestMethod.GET)
	public ResponseEntity<String> makename(HttpServletResponse response) {
		final String HTTP_REQUEST = "https://nickname.hwanmoo.kr/?format=json&count=2";
		 try {
		String info = "";
		URL url = new URL(HTTP_REQUEST);
		BufferedReader bf;
        bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
        String line;
        while((line = bf.readLine()) != null){
        	info+=line;
        	System.out.println(info);
        	
        	JSONParser mname = new JSONParser();
			Object nobj = mname.parse(info);
			JSONObject nameObj = (JSONObject) nobj;
			System.out.println(nameObj.get("words"));
        	String makename=info;
        	return new ResponseEntity<>(makename,HttpStatus.OK);
        }
	} catch(Exception e) {
        return null;
    }
		 return null; 
	}
	
}
