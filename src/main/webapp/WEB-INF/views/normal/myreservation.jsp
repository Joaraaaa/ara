<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>나의 예약 목록</p>

<%-- ${my_r_list}   --%>
<table border="1">
<c:forEach items="${my_r_list}" var="list">

<tr>
<td><a href="/normal/storedetail?s_no=${list.svo.s_no}">${list.svo.s_name}</a></td>
<td>예약일 : ${list.rsvo.r_date}</td>
<td>예약자 : ${list.r_name}</td>
<td>연락처 : ${list.r_phone}</td>
<td>${list.r_people}명</td>
<td>메모 : ${list.r_memo}</td>
</tr>
</c:forEach>
</table>

  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="../resources/js/normal/myreservation.js?ver=1"></script>
</body>
</html>