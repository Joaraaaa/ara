package org.ara.mapper;

import java.util.ArrayList;

import org.ara.model.ResSetVO;

public interface ResSetMapper {
	
// insert
	// 예약 시간표 추가
	public int insert(ResSetVO rsvo);

// select
	// 예약 시간표 검색
	public ArrayList<ResSetVO> s_select(ResSetVO rsvo);
	// 예약 인원 검색
	public int p_select(ResSetVO rsvo);

// update
	// 예약 시간표 수정
	public int update(ResSetVO rsvo);

}
