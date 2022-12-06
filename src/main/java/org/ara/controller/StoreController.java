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
	

	// 가게 정보 수정 화면
	@RequestMapping(value = "/store/restaurantsetting", method = RequestMethod.GET)
	public void restaurantGet() {

	}
	
	// 사업자 회원에게 가게 정보를 입력 받음
	@RequestMapping(value = "/store/restaurantsetting", method = RequestMethod.POST)
	public String restaurant(StoreVO svo, ResSetVO rsvo, HttpSession session) {
		
		// 입력한 가게 정보 확인
		System.out.println("가게 정보"+svo);
		
		// 예약 시간표를 만들기 위한 계산
		int s_no=svo.getS_no(); // 가게 고유 번호
		int first=svo.getF_time(); // 첫 예약 시간
		int last=svo.getL_time(); // 마지막 예약 시간
		int cycle=svo.getCycle(); // 예약 시간표를 만들기 위한 예약 주기

		// 오늘 날짜를 불러오기
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter d= DateTimeFormatter.ISO_LOCAL_DATE;
		
		// 만약 가게 정보 설정이 처음이라면,
		if(session.getAttribute("storeInfo")==null) {
			
			// 가게 정보를 추가하기
			ss.add_s_info(svo);
			
			// 동시에 예약 시간표 14일치 저장하기
			for(int j=0;j<14;j+=1) {
				
				// 날짜 = 1일 ~ 14일
				String date=localDate.plusDays(j).format(d);
				System.out.println(date);
				
				// ex) 12시(first)부터 18시(last)까지 1시간(cycle)의 간격을 두고 시간표를 생성한다.
				for(int i=first; i<=last; i+=cycle) {
					
					rsvo.setR_time(i); // 예약 시간
					rsvo.setR_date(date); // 예약 날짜
					rsvo.setPeople(svo.getP_set()); // 예약 가능 인원
					rsvo.setDt_no('D'+date+'T'+i+'N'+s_no); // 예약 시간표의 고유 번호(날짜+시간+가게고유번호)
					System.out.println("예약 시간표 확인"+rsvo);
					
					// 예약 시간표 추가
					rs.add_schedule(rsvo);
				}
			}
			
		// 저장된 가게 정보가 이미 있다면,
		}else {
			
			System.out.println("저장된 가게 정보가 이미 있다.");
			
			// 가게 정보 수정하기. 이미 만들어진 예약 시간표는 그대로 둔다.
			ss.modify_s_info(svo);
		}
		
		// 세션에 추가 또는 수정된 가게 정보 저장하기
		session.setAttribute("storeInfo", ss.find_s_info(svo));
		
		// 예약 시간표 수정 페이지로 이동
		return "redirect:/store/reservationsetting";
		
	}
	
	
	// 이미 저장 된 예약 시간표에 새로운 날짜의 시간표 추가 하기.
	@RequestMapping(value = "/updatesetting", method = RequestMethod.PUT)
	public ResponseEntity<String> updateSetting(StoreVO svo, ResSetVO rsvo, HttpSession session) {
		
		// 날짜는 +13으로 저장한다.
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter d= DateTimeFormatter.ISO_LOCAL_DATE;
		String date=localDate.plusDays(13).format(d);
		System.out.println("예약 시간표에 추가 할 날짜 : "+date);
		
		int result = 0;
		
		// 가입된 모든 가게의 수를 가져온다.
		int max_no = ss.find_s_num();
		
		// 가게 고유번호 1번부터 마지막 번호까지 반복한다.
		for (int j=1; j<=max_no; j+=1) {
			
			// j번째 s_no로 가게 정보를 가져온다.
			svo.setS_no(j);
			svo = ss.find_s_info(svo);
			
			// 가져온 가게 정보를 변수에 저장한다.
			System.out.println("가게 정보 : "+svo);
			int first=svo.getF_time(); // 첫 예약 시간 
			int last=svo.getL_time(); // 마지막 예약 시간
			int cycle=svo.getCycle(); // 예약 시간표를 만들기 위한 예약 주기
			
			// ex) 12시(first)부터 18시(last)까지 1시간(cycle)의 간격을 두고 시간표를 생성한다.
			for(int i=first; i<=last; i+=cycle) {
				rsvo.setS_no(j); // 가게 고유 번호
				rsvo.setR_time(i); // 예약 시간
				rsvo.setR_date(date); // 예약 날짜
				rsvo.setPeople(svo.getP_set()); // 예약 가능 인원
				rsvo.setDt_no('D'+date+'T'+i+'N'+j); // 예약 시간표 고유 번호
				
				System.out.println("예약 시간표 확인 : "+rsvo);
				
				// 예약 시간표 추가하기
				result = rs.add_schedule(rsvo);
			}
		}
		
		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}


	
	// 예약 시간표를 검색하기 위한 날짜 선택 범위 정하기 
	@RequestMapping(value = "/store/reservationsetting", method = RequestMethod.GET)
	public void reservationGet(Model model) {
		
		// 날짜 선택 범위 정하기
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
	
	// 특정 날짜의 예약 시간표 목록
	@RequestMapping(value = "/reslist", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<ResSetVO>> reslist(ResSetVO rsvo) {
		
		// 가게 고유 번호와 날짜를 받아온다.
		System.out.println("검색 할 예약 시간표의 정보 : "+rsvo);
		
//		System.out.println(rs.select(rsvo));
		
		// 예약 시간표를 검색해 보낸다.
		return new ResponseEntity<>(rs.find_schedule(rsvo), HttpStatus.OK);
	}
	
	// 예약 시간표의 예약 가능 인원 수정하기
	@RequestMapping(value = "reservation/peopleupdate", method = RequestMethod.PUT)
	public ResponseEntity<String> modifyPeople(@RequestBody ResSetVO rsvo) {
		
		// 가게 고유 번호와 예약 시간표 고유 번호를 받아온다.
		System.out.println("수정할 시간표 정보 : "+rsvo);
		
		// 만약 예약 가능 인원이 0명이면,
		if(rsvo.getPeople()==0) {
			
			// 예약 불가능
			rsvo.setR_status(false);

		}else {
			
			// 여전히 예약 가능
			rsvo.setR_status(true);
			
		}
		
		// 예약 가능 인원과 예약 상태를 수정한다.
		int result = rs.update_schedule(rsvo);
		
	return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
	: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	// 예약자 목록
	@RequestMapping(value = "/store/list", method = RequestMethod.GET)
	public String list(ResUserVO ruvo, ResSetVO rsvo, Model model) {
		
		// 가게 고유 번호와 예약 시간표 고유번호를 받아온다.
		System.out.println(ruvo);
		
		// 가게 고유 번호를 모델에 담는다.
		model.addAttribute("s_no", ruvo.getS_no());
		
		// 예약자 목록을 모델에 담는다
		model.addAttribute("list", rus.find_r_list(ruvo));
		
		return "store/list";
	}
	
	
	// 예약자 정보 삭제
	@RequestMapping(value = "/reservation/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@RequestBody ResUserVO ruvo, StoreVO svo, ResSetVO rsvo) {
		
		System.out.println("삭제 컨트롤러 : "+ruvo);
		
		rsvo.setDt_no(ruvo.getDt_no()); // 예약 시간표 번호
		rsvo.setS_no(ruvo.getS_no()); // 가게 고유 번호
		// 예약 가능 인원을 찾아온다.
		int people = rs.find_people(rsvo);
		System.out.println(people);
		
		// 예약 가능 최소 인원 찾아오기
		svo.setS_no(ruvo.getS_no());
		svo = ss.find_s_info(svo);
		int p_min = svo.getP_min();
		System.out.println("예약 가능 최소 인원 : "+ p_min);
		
		// 예약 가능 인원에 삭제하는 예약 인원을 더한다
		int new_people = people + ruvo.getR_people();
		
		rsvo.setPeople(new_people); // 새로운 예약 가능 인원
		rsvo.setR_status(true); // 무조건 예약 가능 상태로 변경
		
		// res_set에 예약 가능 인원과 예약 상태 다시 저장하기(update)
		rs.update_schedule(rsvo);
		
		// 해당 r_no와 dt_no의 데이터를 삭제한다.
		int result = rus.delete_user_info(ruvo);
		
		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 예약자 정보 수정
	@RequestMapping(value = "reservation/update", method = RequestMethod.PUT)
	public ResponseEntity<String> rmodify(@RequestBody ResUserVO ruvo, StoreVO svo, ResSetVO rsvo) {
		
		System.out.println("수정 컨트롤러"+ruvo);
		
		// 수정 할 예약 인원
		int new_rp = ruvo.getR_people();
		System.out.println(new_rp);
		
		// 기존의 예약 인원
		int rp = rus.find_r_people(ruvo);
		System.out.println(rp);
		
		// 해당 dt_no의 예약 가능 인원을 가져온다.
		rsvo.setDt_no(ruvo.getDt_no());
		rsvo.setS_no(ruvo.getS_no());
		System.out.println(rsvo);
		int people = rs.find_people(rsvo);
		System.out.println("수정 전의 예약 가능 인원 : "+people);
		
		
		// 예약 가능 최소 인원 찾아오기
		svo.setS_no(ruvo.getS_no());
		svo = ss.find_s_info(svo);
		int p_min = svo.getP_min();
		System.out.println("예약 가능 최소 인원 : "+ p_min);
		
		int result = 0;
		// 가게의 예약 가능 인원에서 기존의 예약 인원을 더하고, 수정 할 예약 인원을 빼준다.
		int new_people = people + rp - new_rp;
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
			
			// 수정된 예약자 정보 저장
			System.out.println("update전 확인 : "+ruvo);
			result = rus.update_user_info(ruvo);
			
			
		}else {
			System.out.println("예약 가능 인원을 초과했습니다.");
		}
	return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
	: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
