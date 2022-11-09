package org.ara.service;

import java.util.ArrayList;

import org.ara.mapper.ReservationMapper;
import org.ara.model.ResSetVO;
import org.ara.model.ReservationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationserviceImpl implements ReservationService{
	@Autowired
	ReservationMapper rm;
	
	public void insert(ResSetVO rsvo) {
		rm.insert(rsvo);
	}
//	public void insert(ReservationVO rvo) {
//		rm.insert(rvo);
//	}
	public ArrayList<ReservationVO> select(ReservationVO rvo) {
		return rm.select(rvo);
	}
	public int delete(ReservationVO rvo) {
		return rm.delete(rvo);
	}
	public int update(ReservationVO rvo) {
		return rm.update(rvo);
	}
	public void rnoInsert(ReservationVO rvo) {
		rm.rnoInsert(rvo);
	}
	public void status(ReservationVO rvo) {
		rm.status(rvo);
	}
	public ArrayList<ReservationVO> r_select(ReservationVO rvo){
		return rm.r_select(rvo);
	}
}
