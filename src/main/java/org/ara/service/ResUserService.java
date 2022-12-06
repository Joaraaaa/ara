package org.ara.service;

import java.util.ArrayList;

import org.ara.model.ResUserVO;

public interface ResUserService {
	public int delete(ResUserVO ruvo);
	public ArrayList<ResUserVO> r_select(ResUserVO ruivo);
	public ArrayList<ResUserVO> list(ResUserVO ruvo);
//	public int rpselect(RUserInfoVO ruivo);
	public void addres(ResUserVO ruvo);
//	public int upres(RUserInfoVO ruivo);
}
