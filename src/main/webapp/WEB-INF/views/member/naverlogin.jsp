<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>

<html>
  <head>
    <title>네이버로그인</title>
  </head>
  <body>

  <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
 <script type="text/javascript">
 
 
// 2. 네이버 인증 토큰 받고 사용자 정보 바로 받음. 사용자 정보를 jsp에 보냄
 
  var naver_id_login = new naver_id_login("GNxlB6b6TI5o3gXTOcXx", "http://localhost:8080/member/naverlogin");
  // 접근 토큰 값 출력
//   alert(naver_id_login.oauthParams.access_token);
  // 네이버 사용자 프로필 조회
  naver_id_login.get_naver_userprofile("naverSignInCallback()");
  // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
  function naverSignInCallback() {
	  $("#password").val(naver_id_login.getProfileData('id'));
	  $("#email").val("N+"+naver_id_login.getProfileData('email'));
	  $("#name").val("N+"+naver_id_login.getProfileData('nickname'));
	  

  }
</script>
	<form action="/member/naverlogin" method="post">
		<input type="hidden" name="password" id="password"> 
		<input type="hidden" name="email" id="email"> 
		<input type="hidden" name="n_name" id="name"> 
		<input type="submit" id="submit">
	</form>
	
	<script>
	
// 3. 사용자 정보를 jsp에서 서버로 보냄
	
	window.addEventListener('load', function () {
	if($("#id").val()!=""){
		$("#submit").click();
		
	}
	})
	</script>
  </body>
</html>