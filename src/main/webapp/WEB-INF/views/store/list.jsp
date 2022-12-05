<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약자 목록</title>
</head>
<body>
<p>예약자 목록</p>
${list}
<table>
<c:forEach items="${list}" var="list">

<tr>
<td>${list.email}</td>
<td><input type="hidden" value="${list.dt_no}" id="${list.rno}_rno" ></td>

<td><input type="text" value="${list.r_name}" id="${list.rno}_name" disabled></td>
<td><input type="text" value="${list.r_phone}" id="${list.rno}_phone" disabled></td>
<td><input type="text" value="${list.r_people}" id="${list.rno}_people" disabled></td>
<td><input type="text" value="${list.r_memo}" id="${list.rno}_memo" disabled></td>
<td id="${list.rno}">
	<button class="m_btn">수정</button>
	<button class="d_btn">삭제</button>
</td>
</tr>
</c:forEach>
</table>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="../resources/js/store/list.js?ver=1"></script>
</body>
</html>