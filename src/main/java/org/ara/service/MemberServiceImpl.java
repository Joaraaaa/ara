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
}
