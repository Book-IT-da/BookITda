<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel=stylesheet href="<c:url value='/css/logo.css' />"
	type="text/css">

<title>회원가입</title>

<script>
function userCreate() {
	if (form.password.value == "") {
		alert("비밀번호를 입력하세요.");
		form.password.focus();
		return false;
	}
	if (form.password.value != form.passwordChk.value) {
		alert("비밀번호가 일치하지 않습니다.");
		form.name.focus();
		return false;
	}
	if (form.name.value == "") {
		alert("이름을 입력하세요.");
		form.name.focus();
		return false;
	}
	if (form.nickname.value == "") {
		alert("닉네임을 입력하세요.");
		form.nickname.focus();
		return false;
	}
	var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if(emailExp.test(form.email.value)==false) {
		alert("이메일 형식이 올바르지 않습니다.");
		form.email.focus();
		return false;
	}
	form.submit();
}
</script>
<style>
hr {
	margin-top: 30px;
	margin-bottom: 25px;
	width: 580px;
	height: 2px;
	background-color: black;
}

td {
	height: 55px;
}

td:nth-child(2n+1) {
	width: 160px;
}

input {
	width: 250px;
	height: 30px;
	border-radius: 3px;
	border-width: 1px;
	border-color: #CCCCCC;
}

input[type="button"] {
	width: 80px;
}

input[type="submit"] {
	margin-top: 50px;
	width: 200px;
	height: 50px;
	border-width: 0px;
	border-radius: 6px;
	background-color: skyblue;
	font-size: 30px;
	font-weight: bold;
	color: white;
}
</style>
</head>

<%-- 아이디, 닉네임 중복 확인 구현 필요함  --%>
<body>
	<div>
		<%@ include file="../../top/logo.jsp"%>
	</div>
	<div align="center">
		<h1>회원수정</h1>
		<hr>
		<form name="form" method="POST"
				action="<c:url value='/user/update'/>">
		<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" placeholder=" 아이디를 입력해주세요"
						name="userId" value="${user.userId}"required></td>
				</tr>

				<tr>
					<td>비밀번호</td>
					<td><input type="password" placeholder=" 비밀번호를 입력해주세요"
						name="password" value="${user.password}"required></td>
				</tr>

				<tr>
					<td>비밀번호 확인</td>
					<td><input type="password" placeholder=" 비밀번호를 한번 더 입력해주세요"
						name="passwordChk" value="${user.password}" required></td>
				</tr>

				<tr>
					<td>이름</td>
					<td><input type="text" placeholder="이름을 입력해주세요" name="name" value="${user.name}"
						required></td>
				</tr>

				<tr>
					<td>닉네임</td>
					<td><input type="text" placeholder="닉네임을 입력해주세요"
						name="nickname" value="${user.nickname}" required></td>
				</tr>

				<tr>
					<td>이메일</td>
					<td><input type="email" placeholder="예: itbook@itbook.com"
						name="email" value="${user.email}" required></td>
				</tr>
		</table>
		</form>
		<input type="button" value="수정" onClick="userCreate()">
	</div>
</body>
</html>