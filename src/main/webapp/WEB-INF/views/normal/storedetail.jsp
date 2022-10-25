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
${store.store}의 예약목록
<table border="1">
	<thead>
		<tr>
			<td>예약 시간</td>
			<td>테이블 번호</td>
			<td>예약</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${rlist}" var="list">
		<tr>
			<td>${list.r_time}시</td>
			<td>${list.tno}</td>
			
			<c:choose>
			<c:when test="${list.r_status eq true}">
			<td>예약불가</td>
			</c:when>
			<c:otherwise>
			<td><a href="/normal/reservation?rno=${list.rno}">예약하기</a></td>
			</c:otherwise>
			</c:choose>
		</tr>
		</c:forEach>
		
	</tbody>
</table>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="../resources/js/normal/storedetail.js?ver=1"></script>
</body>
</html>