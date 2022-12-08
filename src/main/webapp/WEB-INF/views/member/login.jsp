<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="/resources/css/login.css"></link>
</head>
<body>

	<h1 id="title">login</h1>
	
	<form action="/member/login" method="post">
	
	<!-- 일반 회원인지 사업자 회원인지 구분하기 -->
		<div id="admin_box">
			<div id="admin_f_box">
				<input type="radio" class="admin_radio" id="admin_f" name="admin" value="false" checked>
				<label for="admin_f" class="admin_label">일반 회원 로그인</label>
			</div>
			<div id="admin_t_box">
				<input type="radio" class="admin_radio" id="admin_t" name="admin" value="true">
				<label for="admin_t" class="admin_label">사업자 회원 로그인</label>
			</div>
		</div>
		
	<!-- 로그인 입력창 -->
		<div id="login_box">
		
			<table id="login_table">
				<tr>
					<td><!-- 이메일 입력(일반/사업자) -->
						<input type="text" placeholder="이메일" name="email" class="loginInput" id="email">
					</td>
				</tr>
				<tr>
					<td><!-- 비밀번호 입력(일반/사업자) -->
						<input type="password" placeholder="비밀번호" name="password" class="loginInput" id="password">
					</td>
				</tr>
				<tr>
					<td><!-- 사업자등록번호 입력(일반/사업자) -->
						<input type="text" placeholder="사업자등록번호" name="c_no" class="loginInput" id="buisness_num" style="display:none">
					</td>
				</tr>
				<tr>
					<td><!-- 알림 메세지 -->
						<p id="login_msg"><div id="login_fail">${loginFail}</div></p>
					</td>
				</tr>	
				<tr>
					<td><!-- 로그인 버튼 -->
						<input type="submit" value="로그인" id="loginsub">
<!-- 						<div id="loginsub_box"> -->
<!-- 						</div> -->
					</td>
				</tr>
			
			</table>

		<!-- 소셜로그인 -->
			<div id="social_login_box">
			
			<!-- 네이버 로그인 -->
				<div id="naver_img_box" class="social_btn">
					<a id="naverIdLogin_loginButton" href="#"> 
						<img src="http://vonpat01.cafe24.com/wp-content/uploads/2020/08/naver.png" height="60">
					</a>
				</div>
				<div id="naverIdLogin" class="ngk_btn"></div>
			
			<!-- 구글 로그인 -->
				<div id="google_img_box" class="social_btn">
					<img id="google_img" src="https://littledeep.com/wp-content/uploads/2020/09/google-icon-styl.png" height="60">
				</div>
				<div id="buttonDiv" class="ngk_btn"></div>
			
			<!-- 카카오 톡로그인 -->
				<div id="kakao_img_box" class="social_btn">
					<div class="kakaobtn ngk_btn">
						<a id="kakao-login-btn" href="javascript:loginWithKakao()"> 
							<img src="https://www.ssghero.com/assets/images/login/kakao_login_circle.png" width="60" alt="카카오 로그인 버튼" />
						</a>
					</div>
				</div>
				
			</div>
		
		<!-- 링크 연결 -->
			<div id="link_box">
				<div id="link_find">
					<a href="/member/find">비밀번호 찾기</a>
				</div>
				<div id="link_signup">
					<a href="/signupcheck">회원가입</a>
				</div>
			</div>
			
		</div>
		
	</form>
	
	<!-- 네이버 로그인 -->
	<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
	<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
	
	<!-- 카카오 톡로그인 -->
	<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.0.0/kakao.min.js"
  		integrity="sha384-PFHeU/4gvSH8kpvhrigAPfZGBDPs372JceJq3jAXce11bVA6rMvGWzvP4fMQuBGL" crossorigin="anonymous">
	</script>
	<script>
	    Kakao.init('5293487c7cb7f1e2d87c3a1ee57bcf63'); // 사용하려는 앱의 JavaScript 키 입력 
	</script>
	
	<!-- 구글 로그인 -->
	<script src="https://accounts.google.com/gsi/client" async defer></script>



	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="../resources/js/member/login.js?ver=1"></script>

</body>
</html>



