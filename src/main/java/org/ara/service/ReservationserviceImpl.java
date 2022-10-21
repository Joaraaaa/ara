package org.ara.service;

import java.util.ArrayList;

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
	public ArrayList<ReservationVO> select(int bno) {
		return rm.select(bno);
	}
	public int delete(ReservationVO rvo) {
		return rm.delete(rvo);
	}
	public int update(ReservationVO rvo) {
		return rm.update(rvo);
	}
}
