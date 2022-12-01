package org.ara.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ara.araclass.GetBuisnessInfoService;
import org.ara.araclass.MailSendService;
import org.ara.araclass.SnsLogin;
import org.ara.model.BMemberVO;
import org.ara.model.MemberVO;
import org.ara.model.StoreVO;
import org.ara.service.MemberService;
import org.json.XML;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.ara.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

@Controller
public class MemberController {
	@Autowired
	MemberService ms;
	
	@Autowired
	StoreService ss;

	@Autowired
	private MailSendService mailService;
	
// 회원가입
	
	// 일반 회원가입 화면
	@RequestMapping(value = "/member/nsignup", method = RequestMethod.GET)
	public String signup(HttpSession session) {
		// 회원가입 화면으로 이동 시 로그아웃 된다.
		session.invalidate();
		
		return "member/nsignup";
	}

	// 사업자 회원가입 화면
	@RequestMapping(value = "/member/bsignup", method = RequestMethod.GET)
	public String signupB(HttpSession session) {
		// 회원가입 화면으로 이동 시 로그아웃 된다.
		session.invalidate();
		return "member/bsignup";
	}
	
	// 일반 이메일 인증
	// 이메일 컨트롤러에서는 js에서 받은 이메일 정보를 MailSendService로 연결만 시켜준다.
	@RequestMapping(value = "/member/emailchk/{email}/", method = RequestMethod.GET)
	@ResponseBody
	public String mailCheck(@PathVariable String email) {
		System.out.println("이메일 인증 요청이 들어옴!");
		System.out.println("이메일 인증 이메일 : " + email);
		return mailService.joinEmail(email);
	}
	
	// 사업자 이메일 인증
	@RequestMapping(value = "/member/bemailchk/", method = RequestMethod.GET)
	@ResponseBody
	public String bMailCheck(BMemberVO bvo) {
		System.out.println("이메일 인증 요청이 들어옴!");
		System.out.println("이메일 인증 이메일 : " + bvo);
		return mailService.bJoinEmail(bvo);
	}
	
	// 이메일, 닉네임 중복체크
	@RequestMapping(value = "/member/signup/{str}", method = RequestMethod.GET)
	public ResponseEntity<String> emchk(@PathVariable String str, MemberVO mvo) {
		System.out.println(str);
		try {
			if (str.contains("@")) {
				mvo.setEmail(str);
				return new ResponseEntity<>(ms.select_email(mvo).getEmail(), HttpStatus.OK);
			} else {
				mvo.setN_name(str);
				return new ResponseEntity<>(ms.select_n_name(mvo).getN_name(), HttpStatus.OK);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 사업자 번호 조회
	@RequestMapping(value = "/buisnesscheck", produces = "application/text; charset=UTF-8", method = RequestMethod.GET)
	public ResponseEntity<String> buisnessCheck(String num,HttpServletResponse response) {
		System.out.println(num);
		GetBuisnessInfoService getBuisnessInfoService = new GetBuisnessInfoService();
		String company = getBuisnessInfoService.getUserInfo(num);
		System.out.println(company);
		return new ResponseEntity<>(company,HttpStatus.OK);
	}	
	
	// 닉네임 만들기
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
        	
        	String makename=info;
        	return new ResponseEntity<>(makename,HttpStatus.OK);
        }
	} catch(Exception e) {
        return null;
    }
		 return null; 
	}
	
// 최종 회원가입
	// js에서 확인 절차를 통해 바르게 입력된 (일반/사업자)회원가입 정보를 이곳으로 모두 받아옴.
	@RequestMapping(value = "/member/signup", method = RequestMethod.POST)
	                                     // 회원가입 후 별도의 로그인 절차 없이 바로 로그인
	public String signup(MemberVO mvo, HttpSession session) {
		// 일반 또는 사업자의 정보가 들어왔는지 확인용
		System.out.println("member="+mvo);
		try {
			ms.signUp(mvo); // 가입을 시키고 바로 로그인시킴.
			
			session.setAttribute("userInfo", ms.login(mvo));
			
			
			return "redirect:/nhome";
		} catch (Exception e) {
			e.printStackTrace();
			
			return "member/nsignup";
			
		}
	}
	
	

	


	
	// 로그인
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String login(HttpSession session,Model model) {
		session.invalidate();
		return "member/login";
	}

	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String loginPost(MemberVO member, StoreVO store, HttpSession session) {
		System.out.println(member);
		System.out.println("여기"+ms.login(member));
		session.setAttribute("userInfo", ms.login(member));
		System.out.println(session.getAttribute("userInfo"));
		if (session.getAttribute("userInfo") != null) {
//			if(member.isAdmin()==true) {
//				store.setBno(ms.select(member).getBno());
//				System.out.println(store.getBno());
//				session.setAttribute("storeInfo", ss.select(store));
//				return "redirect:/bhome";
//			}else {
				return "redirect:/nhome";
//			}
		} else {
			return "member/login";
		}
	}
	
	
// 카카오 로그인(js에서 js키 사용해서 서버로 인증코드 보내기 -> 인증코드 사용해서 엑세스 코드 받기 -> 엑세스 토큰 사용해서 사용자 정보 받기)
// login.js 확인, SnsLogin class 확인 , pom.xml의 문자열 json 변환(추가) 확인하기
	@RequestMapping(value = "/kakaologin", method = RequestMethod.GET)
	public String kakaologin(String code,HttpSession session, MemberVO mvo) {

		// js에서 인증 코드를 요청하여 이 주소로 인증 코드를 받았다.
		System.out.println("js에서 요청한 주소로 코드를 받음 : " + code);
		
		// 이제 받은 인증 코드를 사용하여 엑세스 토큰을 받는다.
		// araclass패키지에 있는 SnsLogin클래스(엑세스 토큰 발급, 사용자 정보 추출 기능)
		SnsLogin SnsLogin = new SnsLogin();
		
		
		// SnsLogin클래스에 인증 코드를 보내고 사용자 정보가 담긴 VO를 리턴 받았다.
		mvo = SnsLogin.kakao(code);
		System.out.println("SnsLogin클래스에서 mvo를 리턴 함 : " + mvo);
		
		
		// 먼저 mvo에 담긴 정보를 회원가입 시킨다.(try)
		// 이미 회원일 시 오류가 발생한다.(catch)
		// 마지막으로 mvo에 담긴 정보로 로그인을 한다.(finally)
		try {
			ms.signUp(mvo);
		}catch(Exception e) {
			System.out.println("이미 회원이다.");
		}finally{
			session.setAttribute("userInfo",ms.login(mvo));
		}
		
		// 사용자 메인 화면으로 보냄.
		return "redirect:/nhome";
	}
	

	
	// 구글 로그인(js에서 인증과정을 통해 아이디토큰까지 받음 -> 서버에서 아이디 토큰 검사후 사용자 정보 추출)
	// login.js , pom.xml의 구글 소셜로그인(추가) 부분 확인하기
		@RequestMapping(value="/googlelogin", method= RequestMethod.POST)
		public String googleLogin(String idtoken, Model model, HttpSession session) throws GeneralSecurityException, IOException {
	// 3. js에서 서버로 보낸 아이디 토큰에는 이미 사용자의 정보가 들어있다. 아이디 토큰 확인하기
			System.out.println(idtoken);
			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
				    // Specify the CLIENT_ID of the app that accesses the backend:
				    .setAudience(Collections.singletonList("1021807136832-ee5020m3rjgegr7phn8ki82n3rttnqcd.apps.googleusercontent.com"))
				    // Or, if multiple clients access the backend:
				    //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
				    .build();

				// (Receive idTokenString by HTTPS POST)
	// 4. 확인된 아이디 토큰으로 사용자 정보 추출
				GoogleIdToken idToken = verifier.verify(idtoken);
				if (idToken != null) {
				  Payload payload = idToken.getPayload();

				  // Print user identifier
				  String userId = payload.getSubject();
				  System.out.println("User ID: " + userId);

				  // Get profile information from payload
				  String email = payload.getEmail();
				  String name = (String) payload.get("name");
				  // Use or store profile information
				  // ...

				  
				  
	// 5. MemberVO에 담기 ---- 끝!
				  MemberVO member = new MemberVO();
				  	member.setSns(true);
					member.setEmail("G+"+email);
					member.setPassword(userId);
					member.setN_name("G+"+name);
					System.out.println(member);
					
					try {
						ms.signUp(member);
					}catch(Exception e) {

						e.printStackTrace();
					}
					session.setAttribute("userInfo",ms.login(member));
					System.out.println("session : "+session.getAttribute("userInfo"));
					return "redirect:/nhome";
				  
				  
				} else {
				  System.out.println("Invalid ID token.");
				  return "/member/login";
				}		
			//		return null;

		}//googleLogin
	
	
	// 네이버 로그인(인증부터 사용자 정보받는것까지 js에서 모두 처리)
	// 1번-login.js , 2번3번-naverlogin.jsp 확인하기
	// 4. login.js에서 인증요청할때의 주소. naverlogin.jsp의 js에서 받은 정보를 처리할것이다.
		@RequestMapping(value="/member/naverlogin", method=RequestMethod.GET)
		public String callBack(MemberVO member, HttpSession session){
			
			return null;
		}
	// 5. naverlogin.jsp의 input정보를 post로 받아서 MemberVO에 바로 넣어준다. ---- 끝!
		@RequestMapping(value="/member/naverlogin", method=RequestMethod.POST)
		public String callBackPost(MemberVO member, HttpSession session){
			member.setSns(true);
			System.out.println(member);

			try {
				ms.signUp(member);
			}catch(Exception e) {
				
				e.printStackTrace();
			}
			session.setAttribute("userInfo",ms.login(member));
			return "redirect:/nhome";
		}
	

	
}
