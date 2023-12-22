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
<link rel=stylesheet href="<c:url value='/css/question/createForm.css' />"
	type="text/css">
<title>질문 글 작성</title>

<script>
	function questionCreate() {
		if (form.title.value == "") {
			alert("제목을 입력하세요.");
			form.title.focus();
			return false;
		}
		if (form.content.value == "") {
			alert("내용을 입력하세요.");
			form.content.focus();
			return false;
		}
		form.submit();
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
	    	<jsp:param name="selected" value="qna" />
	</jsp:include>
	<div align="center">
		<hr>
		<form name="form" method="POST"
			action="<c:url value='/question/create'/>">
			<table>
				<tr>
					<th colspan="2">글쓰기</th>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" required></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="content" required></textarea></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" value="작성"
						onClick="questionCreate()"></td>
				</tr>
			</table>
		</form>
	</div>
</body>

</html>
