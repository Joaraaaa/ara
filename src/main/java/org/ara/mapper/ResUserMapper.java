package org.ara.mapper;

import java.util.ArrayList;

import org.ara.model.ResUserVO;

public interface ResUserMapper {
	public ArrayList<ResUserVO> list(ResUserVO ruvo);
	public void addres(ResUserVO ruvo);
	public ArrayList<ResUserVO> r_select(ResUserVO ruivo);
	public int delete(ResUserVO ruvo);
	public int rpselect(ResUserVO ruvo);
	public int upres(ResUserVO ruvo);
}
