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
	
	@RequestMapping(value = "/store/reservationsetting", method = RequestMethod.GET)
	public void reservationGet() {
	}
	
	@RequestMapping(value = "/reslist", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<ReservationVO>> reslist(int bno) {
		System.out.println(bno);
//		System.out.println(rs.select(bno));
		return new ResponseEntity<>(rs.select(bno), HttpStatus.OK);
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
		}else {
			rvo.setR_status(true);
			
		}
		System.out.println(rvo.isR_status());
		System.out.println("수정할 번호="+rvo);
		int result = rs.update(rvo);
		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	

}
