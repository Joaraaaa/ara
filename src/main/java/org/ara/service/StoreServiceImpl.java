package org.ara.service;

import org.ara.service.StoreService;

import java.util.ArrayList;

import org.ara.mapper.StoreMapper;
import org.ara.model.StoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService{
	@Autowired
	StoreMapper sm;
	
	public void insert(StoreVO store) {
		sm.insert(store);
	}
	public StoreVO select(StoreVO store) {
		return sm.select(store);
	}
	public void update(StoreVO store) {
		sm.update(store);
	}
	public ArrayList<StoreVO> selectAll(){
		return sm.selectAll();
	}
	
}
