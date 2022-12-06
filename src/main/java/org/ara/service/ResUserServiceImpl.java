package org.ara.service;

import java.util.ArrayList;

import org.ara.mapper.ResUserMapper;
import org.ara.model.ResUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ResUserServiceImpl implements ResUserService {
	@Autowired
	ResUserMapper rum;
	
	public ArrayList<ResUserVO> list(ResUserVO ruvo){
		return rum.list(ruvo);
	}
	public void addres(ResUserVO ruvo) {
		rum.addres(ruvo);
	}
	public ArrayList<ResUserVO> r_select(ResUserVO ruvo){
		return rum.r_select(ruvo);
	}
	public int delete(ResUserVO ruvo) {
		return rum.delete(ruvo);		
	}
	public int rpselect(ResUserVO ruvo) {
		return rum.rpselect(ruvo);
	}
	public int upres(ResUserVO ruvo) {
		return rum.upres(ruvo);
	}
}
