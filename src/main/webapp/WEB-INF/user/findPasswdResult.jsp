<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel=stylesheet href="<c:url value='/css/logo.css' />"
	type="text/css">
<title>비밀번호 찾기 결과</title>
</head>
<body>
	<div align="center">
		<h1>비밀번호 찾기</h1>
		<c:choose>
			<c:when test="${param.passwd == 'null'}">
				비밀번호를 찾을 수 없습니다.
			</c:when>
			<c:otherwise>
				회원가입 시 사용한 비밀번호는 <strong>${param.passwd}</strong> 입니다.
			</c:otherwise>
		</c:choose>
		<br>
		<a href="<c:url value='/user/login/form' />">로그인</a>
	</div>
</body>
</html>