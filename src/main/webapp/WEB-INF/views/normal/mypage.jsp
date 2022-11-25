<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
아이디:${userInfo.name}
<input type="text" value="${userInfo.name}" name='name'>
${pday}
${day}
<input type="date" value="${day}" min="${day}" max="${pday}">

</body>
</html>