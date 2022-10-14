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
	
	public void signUp(MemberVO member) {
		mm.signUp(member);
	}
	// 로그인
	public MemberVO login(MemberVO member) {
		return mm.login(member);
	}	
	// 닉네임 중복확인
	public MemberVO nachk(String str) {
		return mm.nachk(str);
	}
	// 이메일 중복확인
	public MemberVO emchk(String str) {
		return mm.emchk(str);
	}
	public MemberVO snsCheck(String email) {
		return mm.snsCheck(email);
	}
}
