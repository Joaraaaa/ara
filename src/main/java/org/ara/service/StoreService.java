package org.ara.service;

import java.util.ArrayList;

import org.ara.model.StoreVO;

public interface StoreService {

// insert
	// 가게 정보 추가
	public void add_s_info(StoreVO svo);

// select
	// 가게 정보 검색
	public StoreVO find_s_info(StoreVO svo);
	// 전체 가게 리스트
	public ArrayList<StoreVO> find_s_all();
	// 총 가게 수 찾기
	public int find_s_num();
	
// update
	// 가게 정보 수정	
	public void modify_s_info(StoreVO svo);
	
}
