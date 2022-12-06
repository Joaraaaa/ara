package org.ara.service;

import java.util.ArrayList;

import org.ara.model.ResUserVO;

public interface ResUserService {
	public int delete(ResUserVO ruvo);
	public ArrayList<ResUserVO> r_select(ResUserVO ruivo);
	public ArrayList<ResUserVO> list(ResUserVO ruvo);
	public int rpselect(ResUserVO ruvo);
	public void addres(ResUserVO ruvo);
	public int upres(ResUserVO ruvo);
}
