package org.ara.service;

import java.util.ArrayList;

import org.ara.model.ResUserVO;

public interface ResUserService {
	
// insert
	// 예약자 정보 추가
	public void add_user_info(ResUserVO ruvo);
	
// select
	// 예약 시간표의 예약자 목록
	public ArrayList<ResUserVO> find_r_list(ResUserVO ruvo);
	// 사용자의 예약 목록
	public ArrayList<ResUserVO> user_r_list(ResUserVO ruivo);
	// 예약 인원
	public int find_r_people(ResUserVO ruvo);
	
// update
	// 예약자 정보 수정
	public int update_user_info(ResUserVO ruvo);
	
// delete
	// 예약자 정보 삭제
	public int delete_user_info(ResUserVO ruvo);
}
