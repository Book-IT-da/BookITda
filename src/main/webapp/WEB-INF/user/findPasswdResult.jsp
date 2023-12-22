<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel=stylesheet href="<c:url value='/css/userMenu.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/logo.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/user/loginForm.css' />" type="text/css">

<title>비밀번호 찾기 결과</title>
</head>
<body>
	<header>
		<div>
			<jsp:include page="../top/userMenubar.jsp" />
		</div>
		<div>
			<%@ include file="../top/logo.jsp"%>
		</div>
	</header>
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
	</div>
</body>
</html>