package org.ara.mapper;

import java.util.ArrayList;

import org.ara.model.ResSetVO;

public interface ResSetMapper {
	public int insert(ResSetVO rsvo);
	public int update(ResSetVO rsvo);
//	public void status(ResSetVO rsvo);
	public ArrayList<ResSetVO> select(ResSetVO rsvo);
	public int pselect(ResSetVO rsvo);	
}
