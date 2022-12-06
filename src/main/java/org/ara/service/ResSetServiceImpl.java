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

// insert
	// 예약 시간표 추가
	public int add_schedule(ResSetVO rsvo) {
		return rm.insert(rsvo);
	}
	
// select
	// 예약 시간표 검색
	public ArrayList<ResSetVO> find_schedule(ResSetVO rsvo) {
		return rm.s_select(rsvo);
	}
	// 예약 인원 검색
	public int find_people(ResSetVO rsvo) {
		return rm.p_select(rsvo);
	}

//update
	// 예약 시간표 수정
	public int update_schedule(ResSetVO rsvo) {
		return rm.update(rsvo);
	}
}
