<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel=stylesheet href="<c:url value='/css/logo.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/user/registerForm.css' />" type="text/css">

<title>회원가입</title>

<script>
function userCreate() {
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
</head>

<%-- 아이디, 닉네임 중복 확인 구현 필요함  --%>
<body>
	<div>
		<%@ include file="../top/logo.jsp"%>
	</div>
	<center>
		<h1>회원가입</h1>
		<hr>
		<table>
			<form name="form" method="POST"
				action="<c:url value='/user/register'/>">
				<tr>
					<td>아이디</td>
					<td><input type="text" placeholder=" 아이디를 입력해주세요"
						name="userId" required> </td>
				</tr>

				<tr>
					<td>비밀번호</td>
					<td><input type="password" placeholder=" 비밀번호를 입력해주세요"
						name="password" required></td>
				</tr>

				<tr>
					<td>비밀번호 확인</td>
					<td><input type="password" placeholder=" 비밀번호를 한번 더 입력해주세요"
						name="passwordChk" required></td>
				</tr>

				<tr>
					<td>이름</td>
					<td><input type="text" placeholder="이름을 입력해주세요" name="name"
						required></td>
				</tr>

				<tr>
					<td>닉네임</td>
					<td><input type="text" placeholder="닉네임을 입력해주세요"
						name="nickname" required></td>
				</tr>

				<tr>
					<td>이메일</td>
					<td><input type="email" placeholder="예: itbook@itbook.com"
						name="email" required>
					</td>
				</tr>
			</form>
		</table>
		<input type="button" value="가입하기" onClick="userCreate()">
	</center>
</body>
</html>