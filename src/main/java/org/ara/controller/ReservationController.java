package org.ara.controller;

import java.util.ArrayList;

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
	
	@RequestMapping(value = "/store/reservation", method = RequestMethod.GET)
	public void reservationGet() {
	}
	
	@RequestMapping(value = "/reslist", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<ReservationVO>> reslist(int bno) {
		System.out.println(bno);
		return new ResponseEntity<>(rs.select(bno), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/store/delete/{rno}", method = RequestMethod.GET)
	public String delete(ReservationVO rvo) {
		System.out.println(rvo);
		rs.delete(rvo);
		return "redirect:/store/reservation";
	}
	
	@RequestMapping(value = "/store/rmodify/{rno}", method = RequestMethod.GET)
	public String rmodify(@RequestBody int rno) {
		return "store/rmodify";
	}
	
	@RequestMapping(value = "/reservationsetting", method = RequestMethod.GET)
	public ResponseEntity<ReservationVO> reservationsetting(ReservationVO rvo) {
		try {

			
		}catch(Exception e){
			
		}

//		System.out.println(rvo);

		
		return null;
	}
}
