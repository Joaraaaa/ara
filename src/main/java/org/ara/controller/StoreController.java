package org.ara.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.ara.model.ResUserVO;
import org.ara.model.ResSetVO;
import org.ara.model.StoreVO;
import org.ara.service.ResSetService;
import org.ara.service.ResUserService;
import org.ara.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StoreController {
	
	@Autowired
	StoreService ss;
	@Autowired
	ResSetService rs;
	@Autowired
	ResUserService rus;
	

	
	@RequestMapping(value = "/store/restaurantsetting", method = RequestMethod.GET)
	public String restaurantGet() {
		return "store/restaurantsetting";
	}
	
	@RequestMapping(value = "/store/restaurantsetting", method = RequestMethod.POST)
	public String restaurant(StoreVO svo, ResSetVO rsvo, HttpSession session) {
		System.out.println(svo);
		int s_no=svo.getS_no();
		int first=svo.getF_time();
		int last=svo.getL_time();
		int cycle=svo.getCycle();
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter d= DateTimeFormatter.ISO_LOCAL_DATE;
		// 가게 설정 정보가 없다면 입력받아 저장하기(insert)
		if(session.getAttribute("storeInfo")==null) {
			ss.add_s_info(svo);
			// 예약 시간표 14일치 저장하기
			for(int j=0;j<14;j+=1) {
				String date=localDate.plusDays(j).format(d);
				System.out.println(date);
				for(int i=first; i<=last; i+=cycle) {
					System.out.println(i+"시 예약");
					rsvo.setR_time(i);
					rsvo.setR_date(date);
					rsvo.setPeople(svo.getP_set());
					rsvo.setDt_no('D'+date+'T'+i+'N'+s_no);
					System.out.println(rsvo);
					rs.insert(rsvo);
				}
			}
		// 가게 설정 정보가 있다면 변경하기(update)
		}else {
			System.out.println("이미 있다.");
			ss.modify_s_info(svo);
		}
		
		session.setAttribute("storeInfo", ss.find_s_info(svo));
		
		return "redirect:/store/reservationsetting";
		
	}
	
	// 매일 업데이트 돼야한다.
	@RequestMapping(value = "/updatesetting", method = RequestMethod.PUT)
	public ResponseEntity<String> updateSetting(StoreVO svo, ResSetVO rsvo, HttpSession session) {
		// 날짜는 +13으로 저장한다.
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter d= DateTimeFormatter.ISO_LOCAL_DATE;
		String date=localDate.plusDays(13).format(d);
		int result = 0;
		// 가입된 모든 가게의 bno의 최대값을 가져온다.
		int max_bno = ss.find_s_num();
		// for문으로 bno가 1번인것부터 끝번인것까지 반복한다.
		for (int j=1; j<=max_bno; j+=1) {
			// i번째 bno로 가게 정보를 가져온다.
			svo.setS_no(j);
			svo = ss.find_s_info(svo);
			// 가져온 정보를 변수에 저장하고
			System.out.println(svo);
			int first=svo.getF_time();
			int last=svo.getL_time();
			int cycle=svo.getCycle();
			// 해당 날짜의 해당 가게정보를 for문으로 insert한다.
			System.out.println(date);
			for(int i=first; i<=last; i+=cycle) {
				System.out.println(i+"시 예약");
				rsvo.setS_no(j);
				rsvo.setR_time(i);
				rsvo.setR_date(date);
				rsvo.setPeople(svo.getP_set());
				rsvo.setDt_no('D'+date+'T'+i+'N'+j);
				System.out.println(rsvo);
				result = rs.insert(rsvo);
			}
		}
//		return null;
		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}


	
	
	@RequestMapping(value = "/store/reservationsetting", method = RequestMethod.GET)
	public void reservationGet(Model model) {
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter d= DateTimeFormatter.ISO_LOCAL_DATE;
		// 오늘 날짜
		String date = localDate.format(d);
		// 오늘+13 날짜
		String plusdays=localDate.plusDays(13).format(d);
		// 날짜 두개 화면으로..
		model.addAttribute("day", date);
		model.addAttribute("pday", plusdays);
	}
	
	@RequestMapping(value = "/reslist", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<ResSetVO>> reslist(ResSetVO rsvo) {
		System.out.println("확인"+rsvo);
		System.out.println(rs.select(rsvo));
		return new ResponseEntity<>(rs.select(rsvo), HttpStatus.OK);
	}
	
	@RequestMapping(value = "reservation/peopleupdate", method = RequestMethod.PUT)
	public ResponseEntity<String> modifyPeople(@RequestBody ResSetVO rsvo) {
		System.out.println("여기"+rsvo);
//		res_set에 people다시 저장하기(update)
		if(rsvo.getPeople()==0) {
			rsvo.setR_status(false);
//			rs.status(rsvo);
		}else {
			rsvo.setR_status(true);
		}
		int result = rs.update(rsvo);
	return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
	: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	
	@RequestMapping(value = "/store/list", method = RequestMethod.GET)
	public String list(ResUserVO ruvo, ResSetVO rsvo, Model model) {
		System.out.println(ruvo);
		model.addAttribute("s_no", ruvo.getS_no());
		System.out.println(rus.list(ruvo));
		model.addAttribute("list", rus.list(ruvo));
		return "store/list";
	}
	
	
	
	@RequestMapping(value = "/reservation/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@RequestBody ResUserVO ruvo, ResSetVO rsvo) {
		System.out.println("삭제 컨트롤러 : "+ruvo);
		
		// 삭제 하기 전에 res_set의 people을  Dt_no로 select해온다.
		rsvo.setDt_no(ruvo.getDt_no());
		rsvo.setS_no(ruvo.getS_no());
		int n_p = rs.pselect(rsvo);
		System.out.println(n_p);
		// people = people + ruvo.getR_people() 로 set하고 update해준다.
		rsvo.setPeople(n_p + ruvo.getR_people());
		System.out.println(rsvo.getPeople());
		rs.update(rsvo);
		// 해당 r_no와 dt_no의 데이터를 삭제한다.
		int result = rus.delete(ruvo);
		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		return null;
	}
	
	@RequestMapping(value = "reservation/update", method = RequestMethod.PUT)
	public ResponseEntity<String> rmodify(@RequestBody ResUserVO ruvo, StoreVO svo, ResSetVO rsvo) {
		System.out.println("수정 컨트롤러"+ruvo);
		
		// 수정 할 예약 인원
		int new_rp = ruvo.getR_people();
		System.out.println(new_rp);
		
		// 기존의 예약 인원
		int rp = rus.rpselect(ruvo);
		System.out.println(rp);
		
		// 해당 dt_no의 예약 가능 인원을 가져온다.
		rsvo.setDt_no(ruvo.getDt_no());
		rsvo.setS_no(ruvo.getS_no());
		System.out.println(rsvo);
		int people = rs.pselect(rsvo);
		System.out.println("수정 전의 예약 가능 인원 : "+people);
		
		
		// 예약 가능 최소 인원 찾아오기
		svo.setS_no(ruvo.getS_no());
		svo = ss.find_s_info(svo);
		int p_min = svo.getP_min();
		System.out.println("예약 가능 최소 인원 : "+ p_min);
		
		int result = 0;
		// 가게의 예약 가능 인원에서 기존의 예약 인원을 더하고, 수정 할 예약 인원을 빼준다.
		int new_people = people + rp - new_rp;
		// 만약 new_p_set >= 0 이면, 
		if (new_people >= 0) {
			// 예약 가능 인원(people)에 저장
			rsvo.setPeople(new_people);
//			int people = rsvo.getPeople();
			System.out.println("수정 후의 예약 가능 인원 : "+ new_people);
			
			// 예약 가능 인원이 예약 가능 최소 인원보다 작으면,
			if (new_people < p_min) {
				// 더이상 예약 불가능.
				rsvo.setR_status(false);
				System.out.println(rsvo);
//				rs.status(rsvo);
			}else {
				// 예약 가능
				rsvo.setR_status(true);
				System.out.println(rsvo);
//				rs.status(rsvo);
			}
			// res_set에 people과 예약 상태 다시 저장하기(update)
			rs.update(rsvo);
			
			// 수정된 예약자 정보 저장
			System.out.println("update전 확인 : "+ruvo);
			result = rus.upres(ruvo);
			
			
		}else {
			System.out.println("예약 가능 인원을 초과했습니다.");
		}
	return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
	: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//return null;
	}
	
}
