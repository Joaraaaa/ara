package org.ara.service;

import java.util.ArrayList;

import org.ara.model.ResSetVO;

public interface ResSetService {

// insert
	// 예약 시간표 추가
	public int add_schedule(ResSetVO rsvo);
	
// select
	// 예약 시간표 검색
	public ArrayList<ResSetVO> find_schedule(ResSetVO rsvo);
	// 예약 인원 검색
	public int find_people(ResSetVO rsvo);
	
// update	
	// 예약 시간표 수정
	public int update_schedule(ResSetVO rsvo);
}
