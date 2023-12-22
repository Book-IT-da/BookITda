<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel=stylesheet href="<c:url value='/css/logo.css' />"
	type="text/css">
<link rel=stylesheet href="<c:url value='/css/user/loginForm.css' />"
	type="text/css">
<title>로그인</title>

<script>
function login() {
	if (form.userId.value == "") {
		alert("아이디를 입력하세요.");
		form.userId.focus();
		return false;
	}
	if (form.password.value == "") {
		alert("비밀번호를 입력하세요.");
		form.password.focus();
		return false;
	}
	form.submit();
}

function userCreate(targetUri) {
	form.action = targetUri;
	form.method = "GET";
	form.submit();
}
function loginAlert() {
	alert('${msg}')
}
</script>
</head>

<body>
	<div>
		<%@ include file="../top/logo.jsp"%>
	</div>
	<div align="center">
		<div class="box">
			<form name="form" method="POST" action="<c:url value='/user/login'/>">
				<h2>로그인</h2>
				<div>
					<input type="text" placeholder=" 아이디" name="userId" required>
					<br> <input type="password" placeholder=" 비밀번호" name="password"
						required> <br>
				</div>
				<div>
					<input id="loginBtn" type="button" value="로그인" onClick="login()"> <br>
				</div>
			</form>
			<c:if test="${loginFailed}">
				<p class="loginFailure">${msg}</p>
			</c:if>
			<div class="links">
				<a href="<c:url value='/user/findId' />" id="resignBtn">아이디 찾기</a> | <a href="<c:url value='/user/findPasswd' />" id="resignBtn">비밀번호 찾기</a> | <input id="resignBtn" type="button"
					value="회원가입"
					onClick="userCreate(
									'<c:url value='/user/register'/>')">
			</div>
		</div>
	</div>
</body>
</html>