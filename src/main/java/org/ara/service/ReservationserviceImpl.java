package org.ara.service;

import org.ara.mapper.ReservationMapper;
import org.ara.model.ReservationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationserviceImpl implements ReservationService{
	@Autowired
	ReservationMapper rm;
	
	public void insert(ReservationVO rvo) {
		rm.insert(rvo);
	}
	public ReservationVO select(String bno) {
		return rm.select(bno);
	}
}
