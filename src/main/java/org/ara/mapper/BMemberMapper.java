package org.ara.mapper;

import org.ara.model.BMemberVO;

public interface BMemberMapper {

// insert
	// 사업자 회원가입
	public void insert(BMemberVO bmvo);
	
// select
	// 사업자 로그인
	public BMemberVO select(BMemberVO bmvo);
	
// update
	// 비밀번호 수정
	public void update(BMemberVO bmvo);
}
