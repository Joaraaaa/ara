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

// insert
	// 가게 정보 추가
	public void add_s_info(StoreVO svo) {
		sm.insert(svo);
	}
	

// select
	// 가게 정보 검색
	public StoreVO find_s_info(StoreVO svo) {
		return sm.select_one(svo);
	}
	// 전체 가게 리스트
	public ArrayList<StoreVO> find_s_all(){
		return sm.select_all();
	}
	// 총 가게 수 찾기
	public int find_s_num() {
		return sm.select_num();
	}
		
// update
	// 가게 정보 수정	
	public void modify_s_info(StoreVO svo) {
		sm.update(svo);
	}
		
}
