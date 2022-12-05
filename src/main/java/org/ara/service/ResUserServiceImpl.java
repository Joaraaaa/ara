package org.ara.service;

import java.util.ArrayList;

import org.ara.mapper.ResUserMapper;
import org.ara.model.RUserInfoVO;
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
}
