package org.ara.mapper;

import org.ara.model.MemberVO;

public interface MemberMapper {
	// 사업자 회원가입, 일반 회원가입, 소셜 회원가입
	public void signUp(MemberVO member);
	// 로그인, 닉네임 중복 확인, 이메일 중복 확인
	public MemberVO select(MemberVO member);
}
