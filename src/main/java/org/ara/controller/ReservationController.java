package org.ara.controller;

import java.util.ArrayList;

import org.ara.model.ReservationVO;
import org.ara.model.StoreVO;
import org.ara.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReservationController {
	@Autowired
	ReservationService rs;
	
	
	@RequestMapping(value = "/reslist", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<ReservationVO>> reslist(String bno) {
		System.out.println(bno);
		return null;
	}
	
	@RequestMapping(value = "/reservationsetting", method = RequestMethod.GET)
	public ResponseEntity<ReservationVO> reservationsetting(ReservationVO rvo) {
		try {
			rs.insert(rvo);
			
		}catch(Exception e){
			
		}

//		System.out.println(rvo);

		
		return null;
	}
}
