<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<h1 id="title">login</h1>
	<form action="/member/login" method="post">
		<div>
			<input type="radio" class="member" name="admin" value="false" checked>
			<label for="huey">일반 회원 로그인</label>
		</div>

		<div>
			<input type="radio" class="member" name="admin" value="true">
			<label for="dewey">사업자 회원 로그인</label>
		</div>
		<div id="login_box">
			<table id="login_table">
				<tr>
					<td><input type="text" placeholder="이메일" name="email"
						class="loginInput" id="email"></td>
				</tr>
				<tr>
					<td><input type="password" placeholder="비밀번호" name="password"
						class="loginInput" id="password"></td>
				</tr>
				<tr>
					<td><input type="text" placeholder="사업자 등록번호" name="name"
						class="loginInput" id="buisness_num" style="display:none"></td>
				</tr>
				<tr>
					<td>
						<div id="loginsub_box">
							<p id="login_msg"></p>
							<input type="submit" value="로그인" id="loginsub">
						</div>
					</td>
				</tr>
			</table>
			<!-- 	 				<div id="social_login_box"> -->
			<!-- 						네이버로그인 -->
			<!-- 						<div id="naver_img_box" class="social_btn"> -->
			<!-- 							<a id="naverIdLogin_loginButton" href="#"> -->
			<!-- 								<img src="http://vonpat01.cafe24.com/wp-content/uploads/2020/08/naver.png" height="60"> -->
			<!-- 							</a> -->
			<!-- 						</div>	 -->
			<!-- 						<div id="naverIdLogin" ></div> -->
			<!-- 						구글로그인	 -->
			<!-- 						<div id="google_img_box" class="social_btn"> -->
			<!-- 							<img id="google_img" src="https://littledeep.com/wp-content/uploads/2020/09/google-icon-styl.png" height="60"> -->
			<!-- 						</div> -->
			<!-- 						<div id="buttonDiv"></div>  -->
			<!-- 						카카오톡로그인 -->
			<!-- 						<div id="kakao_img_box" class="social_btn"> -->
			<!-- 							<div class="kakaobtn"> -->
			<!-- 								<a id="kakao-login-btn" href="javascript:loginWithKakao()"> -->
			<!-- 								<img src="https://www.ssghero.com/assets/images/login/kakao_login_circle.png"  -->
			<!-- 									width="60" alt="카카오 로그인 버튼" /> -->
			<!-- 								</a> -->
			<!-- 							</div> -->
			<!-- 						</div> -->
			<!-- 					</div>   -->
			<div id="link_box">
				<div id="link_findId">
					<a href="/member/findId"><h5>아이디/비밀번호 찾기</h5></a>
				</div>
				<div id="link_signup">
					<a href="/member/signup"><h5>회원가입</h5></a>
				</div>
			</div>
		</div>
	</form>
	<!-- <script src="https://t1.kakaocdn.net/kakao_js_sdk/2.0.0/kakao.min.js" -->
<!--   integrity="sha384-PFHeU/4gvSH8kpvhrigAPfZGBDPs372JceJq3jAXce11bVA6rMvGWzvP4fMQuBGL" crossorigin="anonymous"></script> -->

<!-- <script> -->
<!-- //    Kakao.init('5293487c7cb7f1e2d87c3a1ee57bcf63'); // 사용하려는 앱의 JavaScript 키 입력 -->
<!-- </script> -->

<!-- <script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script> -->
<!-- <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script> -->



<!-- <script src="https://accounts.google.com/gsi/client" async defer></script> -->
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="../resources/js/member/login.js?ver=1"></script>
</body>
</html>