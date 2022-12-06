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

//insert
	// 일반 회원가입, 소셜 회원가입
	public void signUp(MemberVO mvo){
		mm.insert(mvo);
	}
	
//select
	// 로그인
	public MemberVO login(MemberVO mvo){
		return mm.select(mvo);
	}
	// 비밀번호 확인
	public MemberVO select_pw(MemberVO mvo) {
		return mm.select(mvo);
	}
	// 닉네임 중복 확인
	public MemberVO select_n_name(MemberVO mvo){
		return mm.select(mvo);
	}
	// 이메일 중복 확인
	public MemberVO select_email(MemberVO mvo){
		return mm.select(mvo);
	}
	
//update
	// 닉네임 수정
	public void update_n_name(MemberVO mvo){
		mm.update(mvo);
	}
	// 비밀번호 수정
	public void update_pw(MemberVO mvo){
		mm.update(mvo);
	}
	
//delete
	// 회원 탈퇴
	public void delete_user(MemberVO mvo){
		mm.delete(mvo);
	}

}
