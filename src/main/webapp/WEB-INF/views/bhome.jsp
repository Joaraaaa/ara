<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업자 Home</title>
</head>
<body>
<h1>사업자 페이지</h1>
아이디:${userInfo.bname}
<input type="text" value="${userInfo.bno}">
<a href="/">초기화면</a>
<a href="/store/restaurantsetting">가게 설정</a>
<a href="/store/reservationsetting">예약 관리</a>
</body>
</html>