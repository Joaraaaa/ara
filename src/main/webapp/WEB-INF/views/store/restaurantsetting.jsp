<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가게 정보 설정 페이지</title>
</head>
<body>
가게 고유번호 : ${userInfo.s_no}
사업자 등록번호 : ${userInfo.c_no}
상호명 : ${userInfo.c_name}
가게 전화 : ${userInfo.phone}
<form action="/store/restaurantsetting" method="post">
<input type="text" name="s_no" value="${userInfo.s_no}">
<h6>가게이름</h6>
<input type="text" name="s_name" value="${storeInfo.s_name}">
<h6>가게 주소</h6>
<input type="text" name="address" value="${storeInfo.address}">
<h6>오픈시간</h6>
<input type="text" name="o_time" value="${storeInfo.o_time}">
<h6>마감시간</h6>
<input type="text" name="c_time" value="${storeInfo.c_time}">
<h6>첫 예약 시간</h6>
<input type="text" name="f_time" value="${storeInfo.f_time}">
<h6>마지막 예약 시간</h6>
<input type="text" name="l_time" value="${storeInfo.l_time}">
<h6>테이블 회전 시간</h6>
<input type="text" name="cycle" value="${storeInfo.cycle}">
<h6>수용 인원 수</h6>
<input type="text" name="p_set" value="${storeInfo.p_set}">
<h6>예약 최소 인원 수</h6>
<input type="text" name="p_min" value="${storeInfo.p_min}">
<h6>예약 최대 인원 수</h6>
<input type="text" name="p_max" value="${storeInfo.p_max}">
<input type="submit" value="등록하기">
</form>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="../resources/js/store/restaurantsetting.js?ver=1"></script>
</body>
</html>