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
<p style="margin-top:-12px">
    <em class="link">
        <a href="javascript:void(0);" onclick="window.open('http://fiy.daum.net/fiy/map/CsGeneral.daum', '_blank', 'width=981, height=650')">
            혹시 주소 결과가 잘못 나오는 경우에는 여기에 제보해주세요.
        </a>
    </em>
</p>
<div id="map" style="width:100%;height:350px;"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5293487c7cb7f1e2d87c3a1ee57bcf63&libraries=services"></script>


${store.s_name}의 예약목록
<input type="text" value="${store.address}" id="address">
<input type="text" value="${store.s_name}" id="store">
<input type="text" value="${store.s_no}" id="s_no">
<button id="r_btn">예약 검색</button>
${pday}
${day}
<input type="date" id="r_date" value="${day}" min="${day}" max="${pday}">

<table border="1">
	<thead>
		<tr>
			<td>예약 시간</td>
			<td>예약 날짜</td>
			<td>예약</td>
		</tr>
	</thead>
	<tbody id="r_tbody">
<%-- 		<c:forEach items="${rlist}" var="list"> --%>
<!-- 		<tr> -->
<%-- 			<td>${list.r_time}시</td> --%>
<%-- 			<td>${list.r_date}</td> --%>
			
<%-- 			<c:choose> --%>
<%-- 			<c:when test="${list.r_status eq false}"> --%>
<!-- 			<td>예약불가</td> -->
<%-- 			</c:when> --%>
<%-- 			<c:otherwise> --%>
<%-- 			<td><a href="/normal/reservation?s_no=${list.s_no}&dt_no=${list.dt_no}">예약하기</a></td> --%>
<%-- 			</c:otherwise> --%>
<%-- 			</c:choose> --%>
<!-- 		</tr> -->
<%-- 		</c:forEach> --%>
		
	</tbody>
</table>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="../resources/js/normal/storedetail.js?ver=1"></script>
</body>
</html>