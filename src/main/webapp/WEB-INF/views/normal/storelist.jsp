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
<div id="map" style="width:100%;height:350px;"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5293487c7cb7f1e2d87c3a1ee57bcf63&libraries=services"></script>
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
			<td><div class="bno">${store.s_no}</div><a href="/normal/storedetail?s_no=${store.s_no}"><div class="store">${store.s_name}</div></a></td>
			<td><div class="address">${store.address}</div></td>
		</tr>
		</c:forEach>
		
	</tbody>
</table>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="../resources/js/normal/storelist.js?ver=1"></script>
</body>
</html>