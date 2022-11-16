package org.ara.service;

import java.util.ArrayList;

import org.ara.model.RUserInfoVO;
import org.ara.model.ResSetVO;
import org.ara.model.ReservationVO;

public interface ReservationService {
	public void insert(ResSetVO rsvo);
//	public void insert(ReservationVO rvo);
	public ArrayList<ResSetVO> select(ResSetVO rsvo);
	public int delete(ReservationVO rvo);
	public int update(ReservationVO rvo);
	public void rnoInsert(ReservationVO rvo);
	public void status(ReservationVO rvo);
	public ArrayList<ReservationVO> r_select(ReservationVO rvo);
	public ArrayList<RUserInfoVO> list(RUserInfoVO ruivo);
	public int rpselect(RUserInfoVO ruivo);
	public int pselect(ResSetVO rsvo);
}
