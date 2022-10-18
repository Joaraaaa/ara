package org.ara.service;

import org.ara.service.MemberService;
import org.ara.mapper.MemberMapper;
import org.ara.model.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberMapper mm;

	
	// 사업자 회원가입, 일반 회원가입, 소셜 회원가입
	public void signUp(MemberVO member) {
		mm.signUp(member);
	}
	// 로그인, 닉네임 중복 확인, 이메일 중복 확인
	public MemberVO select(MemberVO member) {
		return mm.select(member);
	}	

}
