package org.ara.service;

import org.ara.model.MemberVO;

public interface MemberService {

	public void signUp(MemberVO member);

	public MemberVO login(MemberVO member);

}
