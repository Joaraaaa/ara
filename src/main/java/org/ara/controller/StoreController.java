package org.ara.controller;

import javax.servlet.http.HttpSession;

import org.ara.model.StoreVO;
import org.ara.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StoreController {
	
	@Autowired
	StoreService ss;

	
	@RequestMapping(value = "/store/restaurant", method = RequestMethod.GET)
	public String restaurantGet() {
		return "store/restaurant";
	}
	
	@RequestMapping(value = "/store/restaurant", method = RequestMethod.POST)
	public String restaurant(StoreVO store, HttpSession session) {
		System.out.println(store);
		if(session.getAttribute("storeInfo")==null) {
			ss.insert(store);
		}else {
			ss.update(store);
		}
		session.setAttribute("storeInfo", ss.select(store));
		return "store/restaurant";
	}
	
	@RequestMapping(value = "/store/reservation", method = RequestMethod.GET)
	public String reservationGet() {
		return "store/reservation";
	}
}
