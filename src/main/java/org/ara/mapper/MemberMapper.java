package org.ara.mapper;

import org.ara.model.MemberVO;

public interface MemberMapper {
	// 회원가입
	public void signUp(MemberVO member);

	public MemberVO login(MemberVO member);
	// 아이디 중복확인
	public MemberVO idchk(String str);
	// 이메일 중복확인
	public MemberVO emchk(String str);
}
