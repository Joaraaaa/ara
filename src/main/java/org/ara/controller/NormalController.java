package org.ara.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.ara.araclass.GetBuisnessInfoService;
import org.ara.model.ResSetVO;
import org.ara.model.ReservationVO;
import org.ara.model.StoreVO;
import org.ara.service.ReservationService;
import org.ara.service.StoreService;
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
public class NormalController {
	
	@Autowired
	StoreService ss;
	@Autowired
	ReservationService rs;
	
	@RequestMapping(value = "normal/storelist", method = RequestMethod.GET)
	public String storeList(Model model,StoreVO store) {
		model.addAttribute("list",ss.selectAll());
		return "normal/storelist";
	}
	
	@RequestMapping(value = "normal/storedetail", method = RequestMethod.GET)
	public String storeDetail(Model model, int bno, StoreVO store, ResSetVO rsvo) {
		// 가게 상세 페이지 로드
		System.out.println(bno);
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
		model.addAttribute("store", ss.select(store));
		
		// 오늘 날짜 rsvo에 set해서
//		rsvo.setDate(date);			
		// 예약 시간표 목록 불러오기
//		GetReservationDate grd = new GetReservationDate();
//		model.addAttribute("rlist", grd.getReservationList(rsvo));
		return "normal/storedetail";
		
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
	// 해당날짜의 예약 리스트 불러오기
//	public class GetReservationDate {
//		public ArrayList<ResSetVO> getReservationList(ResSetVO rsvo) {
//			ArrayList<ResSetVO> list= rs.select(rsvo);
//			return list;
//		}
//	}
	
	@RequestMapping(value = "normal/reservation", method = RequestMethod.GET)
	public String reservation (Model model, ReservationVO rvo) {
		System.out.println(rvo);
		model.addAttribute("rno", rvo.getRno());
		
		return "normal/reservation";
	}
	
	@RequestMapping(value = "normal/reservation", method = RequestMethod.POST)
	public String reservationPost (ReservationVO rvo, StoreVO store) {
		System.out.println("rvo확인 : "+rvo);
		rs.update(rvo);
		rvo.setR_status(true);
		rs.status(rvo);
//		System.out.println(rs.update(rvo));
		return "redirect:/normal/storelist";
	}
	
	@RequestMapping(value = "normal/myreservation", method = RequestMethod.GET)
	public String myReservation () {
	
		
		return "normal/myreservation";
	}
	
	@RequestMapping(value = "findreservation", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<ReservationVO>> findreservation (ReservationVO rvo,Model model) {
		System.out.println(rvo);
		System.out.println(rs.r_select(rvo));
//		model.addAttribute("reservation", rs.r_select(rvo));
		return new ResponseEntity<>(rs.r_select(rvo), HttpStatus.OK);
//		return "normal/myreservation";
	}
	
	@RequestMapping(value = "normal/mypage", method = RequestMethod.GET)
	public String mypage (Model model) {
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter d= DateTimeFormatter.ISO_LOCAL_DATE;
		String date = localDate.format(d);
		System.out.println(date);
		String plusdays=localDate.plusDays(6).format(d);
		System.out.println(plusdays);
		model.addAttribute("day", date);
		model.addAttribute("pday", plusdays);
		return "normal/mypage";
	}
	
}
