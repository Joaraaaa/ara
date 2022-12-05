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
					<input type="text" name="admin" value="true">
					<div id="signup_box_text">
						<h4 class="signup_text" id="text_email">사업자 이메일 인증</h4>
<!-- 						<h4 class="signup_text" id="text_b_num">사업자등록번호</h4> -->
						<p>test: 3988701116&emsp;(주)우리금융지주&emsp;2208162517&emsp;네이버(주)</p>
						<input class="signup_input" type="text" placeholder="사업자등록번호" name="c_no" id="crno">
<!-- 						<p class="signup_msg" id="bnomsg"></p> -->
						
<!-- 						<h4 class="signup_text" id="text_b_name">상호명</h4> -->
						<input class="signup_input" type="text" placeholder="상호명" name="c_name" id="bname">
<!-- 						<button id="buisness_num">인증</button> -->
<!-- 						<p class="signup_msg" id="bnamsg"></p> -->
						<input type="hidden" name="email">
						<p>이메일</p>
						<input class="e_input" type="text" id="email">@<input class="e_input" type="text" id="direct"> 
						<select class="e_select" id="email_address">
							<option id="user_email" value="@user">직접입력</option>
							<option class="NG_email" value="@gmail.com">gmail.com</option>
							<option class="NG_email" value="@naver.com">naver.com</option>
						</select> 
						<div class="mail_check_box">
							<input type="submit" value="인증번호받기" id="email_btn">
							<input class="mail_check_input" disabled="disabled"
								placeholder="인증번호 6자리를 입력해주세요" maxlength="6" id="email_num">
						</div>
						<p class="signup_msg" id="email_msg"></p>
						
						<h4 class="signup_text" id="text_pw">비밀번호</h4>
						<input class="signup_input" type="password" name="password" id="pwchk">
						<p class="signup_msg" id="pwmsg"></p>
						
						<h4 class="signup_text" id="text_pwc">비밀번호 재확인</h4>
						<input class="signup_input" type="password" name="pw2" id="pwchk2">
						<p class="signup_msg" id="pwmsg2"></p>
						
						<h4 class="signup_text" id="text_name">대표자명</h4>
						<input class="signup_input" type="text" name="name" id="name">
						<p class="signup_msg" id="namsg"></p>
						
						
						<h4 class="signup_text" id="text_phone">연락처</h4>
						<input class="signup_input" type="text" name="phone" id="phone">
						<p class="signup_msg" id="pmsg"></p>

						

						
						
						<div id="signsub_box">
							<input type="submit" value="회원가입" id="signsub">
						</div>
					</div>
				</div>		
	</form>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- 	<script src="../resources/js/member/signup.js?ver=1"></script> -->
	<script src="../resources/js/member/bsignup.js?ver=1"></script>
</body>
</html>