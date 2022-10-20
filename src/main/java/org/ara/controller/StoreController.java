package org.ara.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;

import org.ara.model.ReservationVO;
import org.ara.model.StoreVO;
import org.ara.service.ReservationService;
import org.ara.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StoreController {
	
	@Autowired
	StoreService ss;
	@Autowired
	ReservationService rs;

	
	@RequestMapping(value = "/store/restaurant", method = RequestMethod.GET)
	public String restaurantGet() {
		return "store/restaurant";
	}
	
	@RequestMapping(value = "/store/restaurant", method = RequestMethod.POST)
	public String restaurant(StoreVO store, HttpSession session,ReservationVO rvo) {
		System.out.println(store);
		int first=store.getFirst();
		int last=store.getLast();
		int cycle=store.getCycle();
		int table_no=store.getTable_no();
		int bno=store.getBno();
		String rno="";
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter d= DateTimeFormatter.BASIC_ISO_DATE;
		String date = localDate.format(d);
		System.out.println(date);
		System.out.println(rvo);
		if(session.getAttribute("storeInfo")==null) {
			ss.insert(store);
			for(int i=first; i<=last; i+=cycle) {
				System.out.println(i+"시 예약");
				for(int j=1; j<=table_no; j++) {
					rno= date+Integer.toString(bno)+i+j;
					System.out.println(rno);
					rvo.setDate(date);
					rvo.setRno(rno);
					rvo.setR_time(i);
					rvo.setTno(j);
					rs.insert(rvo);
				}
			}
		}else {
			ss.update(store);
//			rvo.setBno(store.getBno());
			rs.delete(rvo);
			for(int i=first; i<=last; i+=cycle) {
				System.out.println(i+"시 예약");
				for(int j=1; j<=table_no; j++) {
					rno= date+Integer.toString(bno)+i+j;
					System.out.println(rno);
					rvo.setDate(date);
					rvo.setRno(rno);
					rvo.setR_time(i);
					rvo.setTno(j);
					rs.insert(rvo);
				}
			}
		}
		session.setAttribute("storeInfo", ss.select(store));
		return "store/restaurant";
	}
	
	
}
