package org.ara.service;

import org.ara.model.MemberVO;

public interface MemberService {

	public void signUp(MemberVO member);

	public MemberVO login(MemberVO member);
	// 닉네임 중복확인
	public MemberVO nachk(String str);
	// 이메일 중복확인
	public MemberVO emchk(String str);
	public MemberVO snsCheck(String id);
	public void snsSignup(MemberVO member);
}
