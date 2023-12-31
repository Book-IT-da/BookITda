<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel=stylesheet href="<c:url value='/css/userMenu.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/logo.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/user/loginForm.css' />" type="text/css">
<title>아이디 찾기</title>

<script>
function find() {
	if (form.name.value == "") {
		alert("이름을 입력하세요.");
		form.name.focus();
		return false;
	}
	if (form.email.value == "") {
		alert("이메일을 입력하세요.");
		form.email.focus();
		return false;
	}
	form.submit();
}
</script>
<style>

</style>
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
		<div class="box2">
			<form name="form" method="POST" action="<c:url value='/user/findId'/>">
				<h2>아이디 찾기</h2>
				<div>
					<input type="text" placeholder=" 이름" name="name" required>
					<p/> <input type="email" placeholder=" 이메일" name="email"
						required> <br>
					<input type="button" value=찾기 id="loginBtn" onClick="find()"> <br>
				</div>
			</form>
		</div>
	</div>
</body>
</html>