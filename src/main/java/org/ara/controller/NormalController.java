package org.ara.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

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
	public String storeDetail(Model model, int bno, StoreVO store, ReservationVO rvo) {
		System.out.println("확인"+store);
		System.out.println("확인"+rvo);
		store.setBno(bno);
		model.addAttribute("store", ss.select(store));
		model.addAttribute("rlist", rs.select(rvo));
		
		return "normal/storedetail";
		
	}
	
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
	public String mypage () {
	
		
		return "normal/mypage";
	}
	
}
