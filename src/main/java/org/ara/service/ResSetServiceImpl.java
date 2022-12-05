package org.ara.service;

import java.util.ArrayList;

import org.ara.mapper.ResSetMapper;
import org.ara.model.ResSetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResSetServiceImpl implements ResSetService{
	@Autowired
	ResSetMapper rm;
	
	public int insert(ResSetVO rsvo) {
		return rm.insert(rsvo);
	}
	
	public ArrayList<ResSetVO> select(ResSetVO rsvo) {
		return rm.select(rsvo);
	}
	public int update(ResSetVO rsvo) {
		return rm.update(rsvo);
	}
	public int pselect(ResSetVO rsvo) {
		return rm.pselect(rsvo);
	}
//	public void status(ResSetVO rsvo) {
//		rm.status(rsvo);
//	}
}
