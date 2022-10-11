package org.ara.mapper;

import org.ara.model.MemberVO;

public interface MemberMapper {
	// 회원가입
	public void signUp(MemberVO member);

	public MemberVO login(MemberVO member);
}
