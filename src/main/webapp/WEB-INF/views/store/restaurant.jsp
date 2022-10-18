<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가게 정보 설정 페이지</title>
</head>
<body>
bno : ${userInfo.bno}
사업자 등록번호 : ${userInfo.crno}
상호명 : ${userInfo.bname}
가게 전화 : ${userInfo.phone}
<form action="/bmemberpage/restaurant" method="post">
<input type="hidden" name="bno" value="${userInfo.bno}">
<h6>가게이름</h6>
<input type="text" name="store" value="${storeInfo.store}">
<h6>가게 주소</h6>
<input type="text" name="address" value="${storeInfo.address}">
<h6>오픈시간</h6>
<input type="text" name="open_time" value="${storeInfo.open_time}">
<h6>마감시간</h6>
<input type="text" name="close_time" value="${storeInfo.close_time}">
<h6>첫 예약 시간</h6>
<input type="text" name="first" value="${storeInfo.first}">
<h6>마지막 예약 시간</h6>
<input type="text" name="last" value="${storeInfo.last}">
<h6>테이블 회전 시간</h6>
<input type="text" name="cycle" value="${storeInfo.cycle}">
<h6>예약 가능 테이블 수</h6>
<input type="text" name="table_no" value="${storeInfo.table_no}">
<input type="submit" value="등록하기">
</form>
</body>
</html>