<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>나의 예약 목록</p>

<input type="text" id='r_name' name="r_name" placeholder="예약자 이름">
<input type="text" id="r_phone" name="r_phone" placeholder="예약자 번호">
<button id="find_r">예약 조회하기</button>
<div id="r_list"></div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="../resources/js/normal/myreservation.js?ver=1"></script>
</body>
</html>