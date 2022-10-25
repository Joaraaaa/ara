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
<p>가게목록</p>
<table border="1">
	<thead>
		<tr>
			<td>가게이름</td>
			<td>위치</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="store">
		<tr>
			<td><a href="/normal/storedetail?bno=${store.bno}">${store.store}</a></td>
			<td>${store.address}</td>
		</tr>
		</c:forEach>
		
	</tbody>
</table>
</body>
</html>