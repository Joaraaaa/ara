package org.ara.mapper;

import java.util.ArrayList;

import org.ara.model.StoreVO;

public interface StoreMapper {

// insert
	// 가게 정보 추가
	public void insert(StoreVO svo);

// select
	// 가게 정보 검색
	public StoreVO select_one(StoreVO svo);
	// 전체 가게 리스트
	public ArrayList<StoreVO> select_all();
	// 총 가게 수 찾기
	public int select_num();
		
// update
	// 가게 정보 수정	
	public void update(StoreVO svo);
		
}

