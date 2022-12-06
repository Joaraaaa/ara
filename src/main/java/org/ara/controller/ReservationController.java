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
	
	// 가게 목록 페이지
	@RequestMapping(value = "normal/storelist", method = RequestMethod.GET)
	public String storeList(Model model,StoreVO store) {
		
		// 등록된 가게 목록을 모두 모델에 넣는다.
		model.addAttribute("list",ss.find_s_all());
		
		return "normal/storelist";
	}
	
	// 가게 상세 페이지
	@RequestMapping(value = "normal/storedetail", method = RequestMethod.GET)
	public String storeDetail(Model model, StoreVO svo, ResSetVO rsvo) {
		
		// 예약일 선택을 위한 날짜 만들기
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter d= DateTimeFormatter.ISO_LOCAL_DATE;
		
		// 오늘 날짜
		String date = localDate.format(d);
		
		// 오늘+6 날짜
		String plusdays=localDate.plusDays(6).format(d);
		
		// 날짜 두개 화면으로..
		model.addAttribute("day", date);
		model.addAttribute("pday", plusdays);
		
		// 가게 정보 검색해서 모델에 담는다.
		model.addAttribute("store", ss.find_s_info(svo));
		
		return "normal/storedetail";
	}
	
	// 해당 날짜의 예약 시간표 목록
	@RequestMapping(value = "reservationlist", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<ResSetVO>> reservationlist(Model model, ResSetVO rsvo) {
		
		System.out.println("비동기 확인"+rsvo);
		
		// 특정 가게의 특정 날짜 예약 시간표를 가져온다.
		return new ResponseEntity<>(rs.find_schedule(rsvo), HttpStatus.OK);
	}
	
	// 예약하기 페이지
	@RequestMapping(value = "normal/reservation", method = RequestMethod.GET)
	public String reservation (Model model, ResSetVO rsvo) {
		
		System.out.println(rsvo);
		
		// 가게 고유 번호 모델에 담기
		model.addAttribute("s_no", rsvo.getS_no());
		// 예약 시간표 번호 모델에 담기
		model.addAttribute("dt_no", rsvo.getDt_no());
		
		return "normal/reservation";
	}
	
	// 예약 확정하기
	@RequestMapping(value = "normal/reservation", method = RequestMethod.POST)
	public String reservationPost (ResUserVO ruvo, StoreVO svo, ResSetVO rsvo, Model model) {
		
		// 예약자 정보가 들어오면 
		System.out.println("ruvo확인 : "+ruvo);
		
		// 예약 가능 인원을 가져온다.
		int people = rs.find_people(rsvo);
		System.out.println("가게의 예약 가능 인원 확인 : "+people);
		
		// 예약 가능 최소 인원 찾아오기
		svo.setS_no(ruvo.getS_no());
		svo = ss.find_s_info(svo);
		int p_min = svo.getP_min();
		System.out.println("예약 가능 최소 인원 : "+ p_min);
		
		// 예약 가능 인원에서 예약 인원을 빼준다.
		int new_people = people - ruvo.getR_people();
		
		// 만약 예약 가능 인원이 >= 0 이면, 
		if (new_people >= 0) {
			
			// 예약 가능 인원(people)에 저장
			rsvo.setPeople(new_people);
			System.out.println("수정 후의 예약 가능 인원 : "+ new_people);
					
			// 예약 가능 인원이 예약 가능 최소 인원보다 작으면,
			if (new_people < p_min) {
				// 더이상 예약 불가능.
				rsvo.setR_status(false);
			}else {
				// 여전히 예약 가능
				rsvo.setR_status(true);
			}
			
			// res_set에 예약 가능 인원과 예약 상태 다시 저장하기(update)
			rs.update_schedule(rsvo);
					
			// 예약자 정보도 추가
			rus.add_user_info(ruvo);
			
			return "redirect:/normal/storelist";
			
		}else {
			
			String msg = "예약 가능 인원을 초과했습니다.";
			
			// 화면에서 사용자가 확인할 수 있도록 추가하기
			model.addAttribute("error", msg);
			
			return "normal/reservation";
			
		}
	}
	
	// 나의 예약 목록
	@RequestMapping(value = "normal/myreservation", method = RequestMethod.GET)
	public String myReservation (ResUserVO ruvo, Model model) {
		
		System.out.println(ruvo);
		
		// 해당 아이디의 예약 목록 검색해서 모델에 담는다.
		model.addAttribute("my_r_list", rus.user_r_list(ruvo));
		
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
