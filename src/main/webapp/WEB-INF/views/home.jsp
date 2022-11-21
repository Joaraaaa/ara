<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<p>홈이다</p>

    
<a href="/member/login">로그인</a>
<a href="/signupcheck">회원가입</a>
<a href="/nhome">비회원</a>
<button id="update_btn">업데이트 버튼</button>


<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="../resources/js/home.js?ver=1"></script>
</body>
</html>
