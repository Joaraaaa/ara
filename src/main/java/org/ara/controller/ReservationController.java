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
	public ResponseEntity<ArrayList<RUserInfoVO>> list(RUserInfoVO ruivo) {
		System.out.println(ruivo);
		return null;
	}
	
	@RequestMapping(value = "/reservation/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@RequestBody ReservationVO rvo) {
		System.out.println(rvo);
		int result = rs.delete(rvo);
		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "reservation/update", method = RequestMethod.PUT)
	public ResponseEntity<String> rmodify(@RequestBody ReservationVO rvo) {
		System.out.println(rvo.getR_name());
		if(rvo.getR_name()==null) {
			rvo.setR_status(false);
			rvo.setEmail("예약가능");
			rvo.setR_name("예약가능");
			rvo.setR_phone("예약가능");
			
		}else {
			rvo.setR_status(true);	
			if(rvo.getEmail()==null) {
				rvo.setEmail("비회원");
			}
		}
		rs.status(rvo);
		System.out.println(rvo.isR_status());
		System.out.println("수정할 번호="+rvo);
		int result = rs.update(rvo);
		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	

}
