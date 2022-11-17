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

<input type="text" name="rno" value="${rno}">
<input type="text" name="email" value="${userInfo.email}">
<input type="text" placeholder="예약자 이름" name="r_name">
<input type="text" placeholder="예약자 번호" name="r_phone">
<input type="text" placeholder="예약인원" name="r_people">
<input type="text" placeholder="가게 전달 메모" name="r_memo">
<input type="submit" value="예약확정하기">
</form>
</body>
</html>