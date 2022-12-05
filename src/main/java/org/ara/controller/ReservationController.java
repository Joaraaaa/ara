package org.ara.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.ara.model.RUserInfoVO;
import org.ara.model.ResSetVO;
import org.ara.model.ResUserVO;
import org.ara.model.ReservationVO;
import org.ara.model.StoreVO;
import org.ara.service.ResSetService;
import org.ara.service.ResUserService;
import org.ara.service.ReservationService;
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
		
		// 오늘 날짜 rsvo에 set해서
//		rsvo.setDate(date);			
		// 예약 시간표 목록 불러오기
//		GetReservationDate grd = new GetReservationDate();
//		model.addAttribute("rlist", grd.getReservationList(rsvo));
		return "normal/storedetail";
//		return null;
	}
	
	@RequestMapping(value = "reservationlist", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<ResSetVO>> reservationlist(Model model, ResSetVO rsvo) {
		// 
		System.out.println("비동기 확인"+rsvo);
//		GetReservationDate grd = new GetReservationDate();
//		System.out.println(grd.getReservationList(rsvo));
		return new ResponseEntity<>(rs.select(rsvo), HttpStatus.OK);
					
		
//		return null;
		
	}
//	// 해당날짜의 예약 리스트 불러오기
////	public class GetReservationDate {
////		public ArrayList<ResSetVO> getReservationList(ResSetVO rsvo) {
////			ArrayList<ResSetVO> list= rs.select(rsvo);
////			return list;
////		}
////	}
	
	@RequestMapping(value = "normal/reservation", method = RequestMethod.GET)
	public String reservation (Model model, ResSetVO rsvo) {
		System.out.println(rsvo);
		model.addAttribute("s_no", rsvo.getS_no());
		model.addAttribute("dt_no", rsvo.getDt_no());
		return "normal/reservation";
	}
	
	@RequestMapping(value = "normal/reservation", method = RequestMethod.POST)
	public String reservationPost (ResUserVO ruvo, ResSetVO rsvo) {
		// 예약자 정보가 들어오면 
		System.out.println("ruvo확인 : "+ruvo);
		// res_set의 people를 s_no로 select해온뒤
		
		
		
		// 여기부터 확인하기
		int n_p = rs.pselect(rsvo);
		System.out.println("가게의 예약 가능 인원 확인 : "+n_p);
		// people == people - r_people하고
		rsvo.setPeople(n_p - ruvo.getR_people());
		System.out.println("바뀐 people확인 : "+rsvo.getPeople());
		// res_set을 update해준다.
		rs.update(rsvo);
		// r_user_info에 ruivo를 insert해준다.
		rus.addres(ruvo);
//		rs.update(rvo);
//		rvo.setR_status(true);
//		rs.status(rvo);
//		System.out.println(rs.update(rvo));
		return "redirect:/normal/storelist";
//		return null;
	}
//	
//	@RequestMapping(value = "normal/myreservation", method = RequestMethod.GET)
//	public String myReservation () {
//	
//		
//		return "normal/myreservation";
//	}
//	
//	@RequestMapping(value = "findreservation", method = RequestMethod.GET)
//	public ResponseEntity<ArrayList<ReservationVO>> findreservation (RUserInfoVO ruivo,Model model) {
//		System.out.println(ruivo);
//		
//		System.out.println(rs.r_select(ruivo));
//		return new ResponseEntity<>(rs.r_select(ruivo), HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "normal/mypage", method = RequestMethod.GET)
//	public String mypage (Model model) {
//		LocalDate localDate = LocalDate.now();
//		DateTimeFormatter d= DateTimeFormatter.ISO_LOCAL_DATE;
//		String date = localDate.format(d);
//		System.out.println(date);
//		String plusdays=localDate.plusDays(6).format(d);
//		System.out.println(plusdays);
//		model.addAttribute("day", date);
//		model.addAttribute("pday", plusdays);
//		return "normal/mypage";
//	}
//	

}
