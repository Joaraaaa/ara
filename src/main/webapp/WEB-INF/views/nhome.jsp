<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반회원 home</title>
</head>
<body>
<h1>일반회원 페이지</h1>
아이디:${userInfo.name}
<input type="text" value="${userInfo.name}" name='name'>
<a href="/">초기화면</a>
<a href="/normal/storelist">가게목록</a>
<a href="/normal/myreservation">예약조회</a>
<a href="/normal/mypage">회원 페이지</a>
</body>
</html>