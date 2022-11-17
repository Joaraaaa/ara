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
<p>ddd</p>
${list}
<table>
<c:forEach items="${list}" var="list">

<tr>
<td>${list.email}</td>
<td><input type="hidden" value="${list.rno}" id="${list.cno}_rno" ></td>

<td><input type="text" value="${list.r_name}" id="${list.cno}_name" disabled></td>
<td><input type="text" value="${list.r_phone}" id="${list.cno}_phone" disabled></td>
<td><input type="text" value="${list.r_people}" id="${list.cno}_people" disabled></td>
<td>${list.r_memo}</td>
<td id="${list.cno}">
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