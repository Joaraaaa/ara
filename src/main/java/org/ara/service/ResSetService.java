package org.ara.service;

import java.util.ArrayList;

import org.ara.model.RUserInfoVO;
import org.ara.model.ResSetVO;
import org.ara.model.ReservationVO;

public interface ResSetService {
	public int insert(ResSetVO rsvo);
	public int update(ResSetVO rsvo);
//	public void status(ResSetVO rsvo);
	public ArrayList<ResSetVO> select(ResSetVO rsvo);
	public int pselect(ResSetVO rsvo);
//	public int bselect(ResSetVO rsvo);
	
	
	
//	public void rnoInsert(ReservationVO rvo);
//	public int delete(RUserInfoVO ruivo);
////	public int update(ReservationVO rvo);
////	public void insert(ReservationVO rvo);
//	public ArrayList<ReservationVO> r_select(RUserInfoVO ruivo);
//	public ArrayList<RUserInfoVO> list(RUserInfoVO ruivo);
//	public int rpselect(RUserInfoVO ruivo);
//	public void addres(RUserInfoVO ruivo);
//	public int upres(RUserInfoVO ruivo);
}
