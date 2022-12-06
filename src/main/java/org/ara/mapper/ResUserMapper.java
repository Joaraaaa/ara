package org.ara.mapper;

import java.util.ArrayList;

import org.ara.model.ResUserVO;

public interface ResUserMapper {

// insert
	// 예약자 정보 추가
	public void insert(ResUserVO ruvo);

// select
	// 예약 시간표의 예약자 목록
	public ArrayList<ResUserVO> l_select(ResUserVO ruvo);
	// 사용자의 예약 목록
	public ArrayList<ResUserVO> u_select(ResUserVO ruivo);
	// 예약 인원
	public int p_select(ResUserVO ruvo);

// update
	// 예약자 정보 수정
	public int update(ResUserVO ruvo);

// delete
	// 예약자 정보 삭제
	public int delete(ResUserVO ruvo);
}
