package org.ara.mapper;

import org.ara.model.MemberVO;

public interface MemberMapper {
	
// insert
	// 일반 회원가입, 소셜 회원가입
	public void insert(MemberVO mvo);
	
// select
	// 로그인, 비밀번호 확인, 닉네임 중복 확인, 이메일 중복 확인
	public MemberVO select(MemberVO mvo);

// update
	// 닉네임 수정, 비밀번호 수정
	public void update(MemberVO mvo);
		
// delete
	// 회원 탈퇴
	public void delete(MemberVO mvo);
		
		
		
//	// 사업자 회원가입, 일반 회원가입, 소셜 회원가입
//	public void signUp(MemberVO member);
//	// 로그인, 닉네임 중복 확인, 이메일 중복 확인
//	public MemberVO select(MemberVO member);
//	public void insert(StoreVO store);
}
