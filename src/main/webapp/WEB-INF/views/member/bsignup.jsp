<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업자 회원가입</title>
</head>
<body>

	<form action="/member/bsignup" method="post">
	
		<div id="signup_box">
		
			<div id="signup_box_text">
			
			<!-- 사업자 인증, 이메일 인증 -->
			
				<!-- 사업자등록번호 입력 -->
				<h4 class="signup_text" id="c_text">사업자 정보 입력</h4>
				<input class="signup_input" type="text" placeholder="사업자등록번호" name="c_no" id="c_input">
				
				<!-- 상호명 입력 -->
				<input class="signup_input" type="text" placeholder="상호명" name="c_name" id="cn_input">
				
				<!-- 이메일 입력 -->
				<h4 class="signup_text" id="e_text">이메일</h4>
				<input type="hidden" name="email">
				<input class="e_input" type="text" id="e_input1">@<input class="e_input" type="text" id="e_input2"> 
				<select class="e_select" id="e_select">
					<option id="user_email" value="@user">직접입력</option>
					<option class="NG_email" value="@gmail.com">gmail.com</option>
					<option class="NG_email" value="@naver.com">naver.com</option>
				</select> 
				
				<!-- 이메일 인증 -->
				<div class="mail_check_box">
					<input type="submit" value="인증번호받기" id="e_num_btn">
					<input class="e_num_input" disabled="disabled" placeholder="인증번호 6자리를 입력해주세요" maxlength="6" id="e_num_input">
				</div>
				
				<!-- 이메일 메세지 -->
				<p class="signup_msg" id="e_msg"></p>
				
						
			<!-- 비밀번호 -->
				
				<!-- 비밀번호 입력 -->
				<h4 class="signup_text" id="pw_text">비밀번호</h4>
				<input class="signup_input" type="password" name="password" id="pw_input">
				
				<!-- 비밀번호 메세지 -->
				<p class="signup_msg" id="pw_msg"></p>
		
				<!-- 비밀번호 재확인	 -->
				<h4 class="signup_text" id="pw_text2">비밀번호 재확인</h4>
				<input class="signup_input" type="password" id="pw_input2">
				
				<!-- 비밀번호 일치 확인 메세지 -->
				<p class="signup_msg" id="pw_msg2"></p>
				
			<!-- 대표자명 -->
			
				<!-- 대표자명 입력 -->
				<h4 class="signup_text" id="n_text">대표자명</h4>
				<input class="signup_input" type="text" name="name" id="n_input">
				
				<!-- 대표자명 메세지 -->
				<p class="signup_msg" id="n_msg"></p>	
				
			<!-- 전화번호 -->
			
				<!-- 전화번호 입력 -->
				<h4 class="signup_text" id="p_text">연락처</h4>
				<input class="signup_input" type="text" name="phone" id="p_input">
				
				<!-- 전화번호 메세지 -->
				<p class="signup_msg" id="p_msg"></p>				
						
			<!-- 회원가입 버튼 -->
			
				<div id="signsub_box">
					<input type="submit" value="회원가입" id="signsub">
				</div>
						
			</div>
			
		</div>	
			
	</form>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="../resources/js/member/bsignup.js?ver=1"></script>
	
</body>
</html>