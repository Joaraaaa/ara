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
<a href="/member/signup">회원가입</a>
아이디:${userInfo.id}
</body>
</html>
