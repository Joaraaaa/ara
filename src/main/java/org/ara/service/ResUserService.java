package org.ara.service;

import java.util.ArrayList;

import org.ara.model.ResUserVO;

public interface ResUserService {
//	public int delete(RUserInfoVO ruivo);
//	public ArrayList<ReservationVO> r_select(RUserInfoVO ruivo);
	public ArrayList<ResUserVO> list(ResUserVO ruvo);
//	public int rpselect(RUserInfoVO ruivo);
	public void addres(ResUserVO ruvo);
//	public int upres(RUserInfoVO ruivo);
}
