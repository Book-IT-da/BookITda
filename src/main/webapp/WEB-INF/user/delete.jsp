<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel=stylesheet href="<c:url value='/css/userMenu.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/logo.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/menu.css' />" type="text/css">
<title>탈퇴</title>
<script>
	function withdraw(formName) {
		var msg = confirm("정말 탈퇴하시겠습니까?");
		if (msg) {
			formName.submit();
		}
		return false;			
	}
</script>
</head>
<body>	
	<header>
		<div>
			<jsp:include page="../top/userMenubar.jsp" />
		</div>
		<div>
			<jsp:include page="../top/logo.jsp" /> 
		</div>
	</header>
	<jsp:include page="../top/menubar.jsp">
    	<jsp:param name="selected" value="" />
	</jsp:include>
	<div align="center">
		<h2>탈퇴</h2>
		<p>탈퇴를 원하면 아래 버튼을 클릭해주세요</p>
		<form name="deleteForm" method="POST" action="<c:url value='/user/delete'/>">
			<input type="hidden" name="userId" value="${user.userId}">
			<input type="button" value="탈퇴" onClick="withdraw(this.form)">
		</form>
	</div>
</body>
</html>