<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>탈퇴</title>
</head>
<body>	
	<!-- 작성한 글이 있는 경우는 삭제 불가능 ... 해결 해야 함. -->
	<h2>탈퇴</h2>
	<form name="form" method="POST" action="<c:url value='/user/delete'/>">
		<input type="submit" value="탈퇴">
	</form>
</body>
</html>