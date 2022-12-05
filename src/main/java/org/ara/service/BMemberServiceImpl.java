package org.ara.service;

import org.ara.mapper.BMemberMapper;
import org.ara.model.BMemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BMemberServiceImpl implements BMemberService {
	@Autowired
	BMemberMapper bm;

// insert
	// 사업자 회원가입
	public void signUp(BMemberVO bmvo) {
		bm.insert(bmvo);
	}
			
// select
	// 로그인
	public BMemberVO login(BMemberVO bmvo) {
		return bm.select(bmvo);
	}
		
// update
	// 비밀번호 수정
	public void update(BMemberVO bmvo) {
		bm.update(bmvo);
	}
}
