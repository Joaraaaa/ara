package org.ara.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.ara.model.ResSetVO;
import org.ara.model.ResUserVO;
import org.ara.model.StoreVO;
import org.ara.service.ResSetService;
import org.ara.service.ResUserService;
import org.ara.service.StoreService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReservationController {
	@Autowired
	StoreService ss;
	@Autowired
	ResSetService rs;
	@Autowired
	ResUserService rus;
	
	@RequestMapping(value = "normal/storelist", method = RequestMethod.GET)
	public String storeList(Model model,StoreVO store) {
		model.addAttribute("list",ss.find_s_all());
		return "normal/storelist";
	}
	
	
	@RequestMapping(value = "normal/storedetail", method = RequestMethod.GET)
	public String storeDetail(Model model, StoreVO svo, ResSetVO rsvo) {
		// 가게 상세 페이지 로드
		System.out.println(svo);
		System.out.println(rsvo);
		// 날짜 선택 만들기
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter d= DateTimeFormatter.ISO_LOCAL_DATE;
		// 오늘 날짜
		String date = localDate.format(d);
		// 오늘+6 날짜
		String plusdays=localDate.plusDays(6).format(d);
		// 날짜 두개 화면으로..
		model.addAttribute("day", date);
		model.addAttribute("pday", plusdays);
		
		// 가게정보 검색해서 화면으로..
		model.addAttribute("store", ss.find_s_info(svo));
		
		return "normal/storedetail";
	}
	
	@RequestMapping(value = "reservationlist", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<ResSetVO>> reservationlist(Model model, ResSetVO rsvo) {
		// 
		System.out.println("비동기 확인"+rsvo);
		return new ResponseEntity<>(rs.select(rsvo), HttpStatus.OK);
					
		
		
	}
	
	@RequestMapping(value = "normal/reservation", method = RequestMethod.GET)
	public String reservation (Model model, ResSetVO rsvo) {
		System.out.println(rsvo);
		model.addAttribute("s_no", rsvo.getS_no());
		model.addAttribute("dt_no", rsvo.getDt_no());
		return "normal/reservation";
	}
	
	@RequestMapping(value = "normal/reservation", method = RequestMethod.POST)
	public String reservationPost (ResUserVO ruvo, ResSetVO rsvo, Model model) {
		// 예약자 정보가 들어오면 
		System.out.println("ruvo확인 : "+ruvo);
		// res_set의 people를 s_no로 select해온뒤
		int n_p = rs.pselect(rsvo);
		System.out.println("가게의 예약 가능 인원 확인 : "+n_p);
		// people == people - r_people하고
		rsvo.setPeople(n_p - ruvo.getR_people());
		System.out.println("바뀐 people확인 : "+rsvo.getPeople());
		if(rsvo.getPeople()>=0) {
			// res_set을 update해준다.
			rs.update(rsvo);
			// 예약자 정보도 추가
			rus.addres(ruvo);
			return "redirect:/normal/storelist";
		}else {
			System.out.println("예약 가능 인원 초과");
			String msg = "예약 가능 인원을 초과했습니다.";
			// 화면에서 사용자가 확인할 수 있도록 추가하기
			model.addAttribute("error", msg);
			return "normal/reservation";
		}
	}
	
	@RequestMapping(value = "normal/myreservation", method = RequestMethod.GET)
	public String myReservation (ResUserVO ruvo, Model model) {
		System.out.println(ruvo);
	
		model.addAttribute("my_r_list", rus.r_select(ruvo));
		return "normal/myreservation";
	}
	

	
	@RequestMapping(value = "normal/mypage", method = RequestMethod.GET)
	public String mypage (Model model) {
		// 개인정보 수정 페이지
		// 닉네임과 비밀번호 수정
		// 출생년도, 성별 추가
		return "normal/mypage";
	}
	

}
