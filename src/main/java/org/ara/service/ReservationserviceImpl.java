package org.ara.service;

import java.util.ArrayList;

import org.ara.mapper.ReservationMapper;
import org.ara.model.RUserInfoVO;
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
	public void update(ResSetVO rsvo) {
		rm.update(rsvo);
	}
	public ArrayList<ResSetVO> select(ResSetVO rsvo) {
		return rm.select(rsvo);
	}
	public int delete(RUserInfoVO ruivo) {
		return rm.delete(ruivo);
	}
//	public int update(ReservationVO rvo) {
//		return rm.update(rvo);
//	}
	public void rnoInsert(ReservationVO rvo) {
		rm.rnoInsert(rvo);
	}
	public void status(ReservationVO rvo) {
		rm.status(rvo);
	}
	public ArrayList<ReservationVO> r_select(ReservationVO rvo){
		return rm.r_select(rvo);
	}
	public ArrayList<RUserInfoVO> list(RUserInfoVO ruivo){
		return rm.list(ruivo);
	}
	public int rpselect(RUserInfoVO ruivo) {
		return rm.rpselect(ruivo);
	}
	public int pselect(ResSetVO rsvo) {
		return rm.pselect(rsvo);
	}
	public void addres(RUserInfoVO ruivo) {
		rm.addres(ruivo);
	}
	public int upres(RUserInfoVO ruivo) {
		return rm.upres(ruivo);
	}
}
