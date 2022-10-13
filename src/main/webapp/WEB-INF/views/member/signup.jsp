<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 회원 가입</title>
</head>
<body>
	<form action="/member/signup" method="post">
				<div id="signup_box">
					<div id="signup_box_text">
						<h4 class="signup_text" id="text_email">이메일</h4>
						<input type="text" name="admin" value="false">
						<input type="text" name="email">
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
						
						<h4 class="signup_text" id="text_name">닉네임</h4>
						<input class="signup_input" type="text" name="name" id="nachk">
						<button id="make_name">닉네임 만들기</button>
						<p class="signup_msg" id="namsg"></p>
						
						

						

						
						
						<div id="signsub_box">
							<input type="submit" value="회원가입" id="signsub">
						</div>
					</div>
				</div>		
	</form>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="../resources/js/member/signup.js?ver=1"></script>
</body>
</html>