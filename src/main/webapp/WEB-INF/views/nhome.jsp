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
닉네임 : ${userInfo.n_name}
<input type="text" value="${userInfo.n_name}" name='n_name'>
<a href="/">초기화면</a>
<a href="/normal/storelist">가게목록</a>
<a href="/normal/myreservation">예약조회</a>
<a href="/normal/mypage">회원 페이지</a>
</body>
</html>