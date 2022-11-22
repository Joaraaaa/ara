package org.ara.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;

import org.ara.model.RUserInfoVO;
import org.ara.model.ResSetVO;
import org.ara.model.ReservationVO;
import org.ara.model.StoreVO;
import org.ara.service.ReservationService;
import org.ara.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StoreController {
	
	@Autowired
	StoreService ss;
	@Autowired
	ReservationService rs;
	
	

	
	@RequestMapping(value = "/store/restaurantsetting", method = RequestMethod.GET)
	public String restaurantGet() {
		return "store/restaurantsetting";
	}
	
	@RequestMapping(value = "/store/restaurantsetting", method = RequestMethod.POST)
	public String restaurant(StoreVO svo, ResSetVO rsvo, HttpSession session) {
		System.out.println(svo);
		int bno=svo.getBno();
		int first=svo.getFirst();
		int last=svo.getLast();
		int cycle=svo.getCycle();
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter d= DateTimeFormatter.ISO_LOCAL_DATE;
		// 가게 설정 정보가 없다면 입력받아 저장하기(insert)
		if(session.getAttribute("storeInfo")==null) {
			ss.insert(svo);
			// 예약 시간표 14일치 저장하기
			for(int j=0;j<14;j+=1) {
				String date=localDate.plusDays(j).format(d);
				System.out.println(date);
				for(int i=first; i<=last; i+=cycle) {
					System.out.println(i+"시 예약");
					rsvo.setR_time(i);
					rsvo.setDate(date);
					rsvo.setPeople(svo.getP_setting());
					rsvo.setRno('D'+date+'T'+i+'N'+bno);
					System.out.println(rsvo);
					rs.insert(rsvo);
				}
			}
		// 가게 설정 정보가 있다면 변경하기(update)
		}else {
			System.out.println("이미 있다.");
			ss.update(svo);
		}
		
		session.setAttribute("storeInfo", ss.select(svo));
		
		return null;
		
	}
	
	// 매일 업데이트 돼야한다.
	@RequestMapping(value = "/updatesetting", method = RequestMethod.PUT)
	public ResponseEntity<String> updateSetting(StoreVO svo, ResSetVO rsvo, HttpSession session) {
		// 날짜는 +13으로 저장한다.
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter d= DateTimeFormatter.ISO_LOCAL_DATE;
		String date=localDate.plusDays(14).format(d);
		int result = 0;
		// 가입된 모든 가게의 bno의 최대값을 가져온다.
		int max_bno = ss.bno_s();
		// for문으로 bno가 1번인것부터 끝번인것까지 반복한다.
		for (int j=1; j<=max_bno; j+=1) {
			// i번째 bno로 가게 정보를 가져온다.
			svo.setBno(j);
			svo = ss.select(svo);
			// 가져온 정보를 변수에 저장하고
			System.out.println(svo);
			int first=svo.getFirst();
			int last=svo.getLast();
			int cycle=svo.getCycle();
			// 해당 날짜의 해당 가게정보를 for문으로 insert한다.
			System.out.println(date);
			for(int i=first; i<=last; i+=cycle) {
				System.out.println(i+"시 예약");
				rsvo.setBno(j);
				rsvo.setR_time(i);
				rsvo.setDate(date);
				rsvo.setPeople(svo.getP_setting());
				rsvo.setRno('D'+date+'T'+i+'N'+j);
				System.out.println(rsvo);
				result = rs.insert(rsvo);
			}
		}
//		return null;
		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
//	public String restaurant(StoreVO store, HttpSession session,ReservationVO rvo) {
//		System.out.println(store);
//		int first=store.getFirst();
//		int last=store.getLast();
//		int cycle=store.getCycle();
//		int table_no=store.getTable_no();
//		int bno=store.getBno();
//		String rno="";
//		LocalDate localDate = LocalDate.now();
//		DateTimeFormatter d= DateTimeFormatter.BASIC_ISO_DATE;
//		String date = localDate.format(d);
//		System.out.println(date);
//		System.out.println(rvo);
//		if(session.getAttribute("storeInfo")==null) {
//			ss.insert(store);
//			for(int i=first; i<=last; i+=cycle) {
//				System.out.println(i+"시 예약");
//				for(int j=1; j<=table_no; j++) {
//					rno= date+Integer.toString(bno)+i+j;
//					System.out.println(rno);
//					rvo.setDate(date);
//					rvo.setRno(rno);
//					rvo.setR_time(i);
//					rvo.setTno(j);
//					rs.insert(rvo);
//					rs.rnoInsert(rvo);
//				}
//			}
//		}else {
//			System.out.println("바꾼다");
//			ss.update(store);
//			rs.delete(rvo);
//			System.out.println("지운다");
//			for(int i=first; i<=last; i+=cycle) {
//				System.out.println(i+"시 예약");
//				for(int j=1; j<=table_no; j++) {
//					rno= date+Integer.toString(bno)+i+j;
//					System.out.println(rno);
//					rvo.setDate(date);
//					rvo.setRno(rno);
//					rvo.setR_time(i);
//					rvo.setTno(j);
//					rs.insert(rvo);
//					rs.rnoInsert(rvo);
//				}
//			}
//		}
//		session.setAttribute("storeInfo", ss.select(store));
//		return "redirect:/store/reservationsetting";
//	}
	
	
}
