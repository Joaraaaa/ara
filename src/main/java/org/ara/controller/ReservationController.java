package org.ara.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.ara.model.RUserInfoVO;
import org.ara.model.ResSetVO;
import org.ara.model.ReservationVO;
import org.ara.model.StoreVO;
import org.ara.service.ReservationService;
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
	ReservationService rs;
	
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
	
	@RequestMapping(value = "/store/list", method = RequestMethod.GET)
	public String list(RUserInfoVO ruivo, Model model) {
		System.out.println(ruivo);
		System.out.println(rs.list(ruivo));
		model.addAttribute("list", rs.list(ruivo));
		return "store/list";
	}
	
	@RequestMapping(value = "/reservation/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@RequestBody ReservationVO rvo) {
		System.out.println(rvo);
		int result = rs.delete(rvo);
		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "reservation/update", method = RequestMethod.PUT)
	public ResponseEntity<String> rmodify(@RequestBody RUserInfoVO ruivo, ResSetVO rsvo) {
		System.out.println(ruivo);
		// 221115해야할 일
		// 수정할때 이메일, 메모 안바꾼다.
		// 이름 전화번호 사람 수는 비어있으면 안넘어오도록 js에서 막는다.
		// rno cno를 select해서 rpeople를 가져온다.
		int n_rp = ruivo.getR_people();
		int p_rp = rs.rpselect(ruivo);
		// 원래 저장되어 있던 rpeople와 화면에서 가져온 rpeople를 비교한다.
		System.out.println(n_rp);
		System.out.println(p_rp);
		// 해당 rno의 res_set -> people를 가져온다.
		rsvo.setRno(ruivo.getRno());
		System.out.println(rsvo);
		int n_p = rs.pselect(rsvo);
		System.out.println(n_p);
		// people + 원rp - 화rp후 다시 people에 저장
		rsvo.setPeople(n_p + p_rp - n_rp);
		System.out.println(rsvo.getPeople());
		
		// 221116해야할 일
		// 여기부터 다시하기 
		// res_set에 people다시 저장하기(update)
		// where rno=#{rno} and cno=#{cno}인곳에 이름 전화번호 화rp 저장
		
//		if(rvo.getR_name()==null) {
//			rvo.setR_status(false);
//			rvo.setEmail("예약가능");
//			rvo.setR_name("예약가능");
//			rvo.setR_phone("예약가능");
//			
//		}else {
//			rvo.setR_status(true);	
//			if(rvo.getEmail()==null) {
//				rvo.setEmail("비회원");
//			}
//		}
//		rs.status(rvo);
//		System.out.println(rvo.isR_status());
//		System.out.println("수정할 번호="+rvo);
//		int result = rs.update(rvo);
//		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
//				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		return null;
	}
	

	

}
