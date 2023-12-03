<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel=stylesheet href="<c:url value='/css/userMenu.css' />"
	type="text/css">
<link rel=stylesheet href="<c:url value='/css/logo.css' />"
	type="text/css">
<link rel=stylesheet href="<c:url value='/css/menu.css' />"
	type="text/css">

<title>질문 글 작성</title>

<script>
	function questionUpdate() {
		if (form.title.value == "") {
			alert("제목을 입력하세요.");
			form.title.focus();
			return false;
		}
		if (form.content.value == "") {
			alter("내용을 입력하세요.");
			form.content.focus();
			return false;
		}
		form.submit();
	}
</script>
<style>
table {
	margin-top: 30px;
	width: 900px;
	border: 1px solid black;
}

th, td {
	height: 50px;
	margin: auto;
	border: 1px solid #444444;
	border-left: 1px solid #444444;
}

th {
	text-align: center;
}

td:nth-child(2n+1) {
	width: 800px;
	text-align: center;
}

tr:last-child {
	text-align: center;
}

textarea {
	width: 800px;
	height: 500px;
}

input[type="submit"] {
	width: 80px;
	height: 40px;
	border: none;
	border-radius: 6px;
	background-color: skyblue;
	font-size: 25px;
	color: white;
}
</style>
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
	    	<jsp:param name="selected" value="review" />
	</jsp:include>
	<center>
		<hr>
		<form name="form" method="POST"
			action="<c:url value='/question/update'/>">
			<input type="hidden" name="qId" value="${ques.qId}">
			<table>
				<tr>
					<th colspan="2">글쓰기</th>
				</tr>
				<tr>
					<td>작성자 닉네임</td>
					<td><input type="text" name="nickname"
						value="${ques.nickname}" required readonly></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" value="${ques.title}"
						required></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="content" required>${ques.qContent}</textarea></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" value="수정"
						onClick="questionUpdate()"></td>
				</tr>
			</table>
		</form>
	</center>
</body>

</html>
