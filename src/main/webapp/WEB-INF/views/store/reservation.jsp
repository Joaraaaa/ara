<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<input type="text" value="${storeInfo.bno}" id="bno">
<input type="hidden" value="${storeInfo.first}" id="first">
<input type="hidden" value="${storeInfo.last}" id="last">
<input type="hidden" value="${storeInfo.cycle}" id="cycle">
<input type="hidden" value="${storeInfo.table_no}" id="table_no">
<input type="date" id="date">
<table border="1">
	<thead>
		<tr>
			<th>예약 상태</th>
			<th>예약번호</th>
			<th>예약날짜</th>
			<th>예약시간</th>
			<th>테이블 번호</th>
			<th>예약자 이메일</th>
			<th>예약자 이름</th>
			<th>예약자 번호</th>
			<th>수정/취소/삭제</th>
		</tr>
	</thead>
	<tbody id="r_list">
	</tbody>
</table>

<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="../resources/js/store/reservation.js?ver=1"></script>
</body>
</html>