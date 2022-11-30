package org.ara.service;

import org.ara.model.MemberVO;

public interface MemberService {
	
// insert
	// 일반 회원가입, 소셜 회원가입
	public void signUp(MemberVO mvo);
		
// select
	// 로그인
	public MemberVO login(MemberVO mvo);
	// 비밀번호 확인
	public MemberVO select_pw(MemberVO mvo);
	// 닉네임 중복 확인
	public MemberVO select_n_name(MemberVO mvo);
	// 이메일 중복 확인
	public MemberVO select_email(MemberVO mvo);
		
// update
	// 닉네임 수정
	public void update_n_name(MemberVO mvo);
	// 비밀번호 수정
	public void update_pw(MemberVO mvo);
		
// delete
	// 회원 탈퇴
	public void delete_user(MemberVO mvo);
	
	
	
//	// 사업자 회원가입, 일반 회원가입, 소셜 회원가입
//	public void signUp(MemberVO member);
//	// 로그인, 닉네임 중복 확인, 이메일 중복 확인
//	public MemberVO select(MemberVO member);

}
