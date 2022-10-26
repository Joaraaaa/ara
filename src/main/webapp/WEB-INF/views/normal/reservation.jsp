<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/normal/reservation" method="post">

<input type="hidden" name="rno" value="${rno}">
<input type="hidden" name="email" value="${userInfo.email}">
<input type="text" placeholder="예약자 이름" name="r_name">
<input type="text" placeholder="예약자 번호" name="r_phone">
<input type="submit" value="예약확정하기">
</form>
</body>
</html>