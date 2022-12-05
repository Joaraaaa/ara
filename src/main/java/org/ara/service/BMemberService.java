package org.ara.service;

import org.ara.model.BMemberVO;

public interface BMemberService {

// insert
	// 사업자 회원가입
	public void signUp(BMemberVO bmvo);
		
// select
	// 로그인
	public BMemberVO login(BMemberVO bmvo);
	
// update
	// 비밀번호 수정
	public void update(BMemberVO bmvo);
}
