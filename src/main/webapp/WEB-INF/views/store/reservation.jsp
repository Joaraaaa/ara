<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<input type="hidden" value="${storeInfo.bno}" id="bno">
<input type="hidden" value="${storeInfo.first}" id="first">
<input type="hidden" value="${storeInfo.last}" id="last">
<input type="hidden" value="${storeInfo.cycle}" id="cycle">
<input type="hidden" value="${storeInfo.table_no}" id="table_no">
<input type="date" id="date">
<div id="r_list">

</div>

<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="../resources/js/store/reservation.js?ver=1"></script>
</body>
</html>