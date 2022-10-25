package org.ara.controller;

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
		System.out.println(store);
		model.addAttribute("list",ss.selectAll());
		System.out.println(ss.select(store));
		return "normal/storelist";
	}
	
	@RequestMapping(value = "normal/storedetail", method = RequestMethod.GET)
	public String storeDetail(Model model, int bno, StoreVO store) {
		store.setBno(bno);
		model.addAttribute("store", ss.select(store));
		model.addAttribute("rlist", rs.select(bno));
		
		return "normal/storedetail";
		
	}
	
	@RequestMapping(value = "normal/reservation", method = RequestMethod.GET)
	public String reservation (ReservationVO rvo) {
		System.out.println(rvo);
		return "normal/reservation";
	}
	
	@RequestMapping(value = "normal/reservation", method = RequestMethod.POST)
	public String reservationPost (ReservationVO rvo) {
		rvo.setR_status(true);
		System.out.println(rvo);
		rs.update(rvo);
		System.out.println(rs.update(rvo));
		return "redirect:/normal/storedetail";
	}
	
}
