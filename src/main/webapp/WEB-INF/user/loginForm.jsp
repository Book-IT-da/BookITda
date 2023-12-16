<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel=stylesheet href="<c:url value='/css/logo.css' />"
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
<style>
input[type="text"], input[type="password"] {
	margin-bottom: 10px;
	width: 300px;
	height: 45px;
	border-radius: 3px;
	border-width: 1px;
	border-color: #CCCCCC;
}

input[type="submit"] {
	margin-top: 25px;
	margin-bottom: 15px;
	width: 300px;
	height: 50px;
	border: 2px solid skyblue;
	border-radius: 6px;
	background-color: skyblue;
	font-size: 30px;
	font-weight: bold;
	color: white;
}

a {
	text-decoration-line: none;
}

.loginFailure {
	width: 300px;
	height: 30px;
	padding-top: 7px;
	background-color: skyblue;
}
</style>
</head>

<body>
	<div>
		<%@ include file="../top/logo.jsp"%>
	</div>
	<div align="center">
		<form name="form" method="POST" action="<c:url value='/user/login'/>">
			<h2>로그인</h2>
			<div>
				<input type="text" placeholder=" 아이디" name="userId" required>
				<br> <input type="password" placeholder=" 비밀번호" name="password"
					required> <br>
			</div>
			<div>
				<input type="button" value="로그인" onClick="login()"> <br>
			</div>
		</form>
		<c:if test="${loginFailed}">
			<p class="loginFailure">${msg}</p>
		</c:if>
		<div class="links">
			<a href="">아이디 찾기</a> | <a href="">비밀번호 찾기</a> | <input type="button"
				value="회원가입"
				onClick="userCreate(
								'<c:url value='/user/register'/>')">
		</div>
	</div>
</body>
</html>