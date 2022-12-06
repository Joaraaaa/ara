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

// insert
	// 예약자 정보 추가
	public void add_user_info(ResUserVO ruvo) {
		rum.insert(ruvo);
	}
	
// select
	// 예약 시간표의 예약자 목록	
	public ArrayList<ResUserVO> find_r_list(ResUserVO ruvo){
		return rum.l_select(ruvo);
	}
	// 사용자의 예약 목록
	public ArrayList<ResUserVO> user_r_list(ResUserVO ruvo){
		return rum.u_select(ruvo);
	}
	// 예약 인원
	public int find_r_people(ResUserVO ruvo) {
		return rum.p_select(ruvo);
	}
	
// update
	// 예약자 정보 수정
	public int update_user_info(ResUserVO ruvo) {
		return rum.update(ruvo);
	}
	
// delete
	// 예약자 정보 삭제	
	public int delete_user_info(ResUserVO ruvo) {
		return rum.delete(ruvo);		
	}
}
