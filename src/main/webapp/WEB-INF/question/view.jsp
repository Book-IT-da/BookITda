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

<title>질문 글 상세보기</title>

<script>
	function questionRemove() {
		return confirm("정말 삭제하시겠습니까?");
	}
</script>
<style>
table {
	margin-top: 30px;
	width: 1000px;
}

td {
	height: 50px;
	border-bottom: 1px solid #444444;
	border-left: 1px solid #444444;
}

td:first-child {
	border-left: none;
}

.button {
	margin-top: 30px;
	width: 100px;
	height: 50px;
	border: none;
	border-radius: 6px;
	background-color: skyblue;
	font-size: 25px;
	color: white;
}

#title {
	text-align: center;
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
		<div>
			<jsp:include page="../top/menubar.jsp" />
		</div>
	</header>
	<center>
		<!-- 질문 글 -->
		<table>
			<tr>
				<td colspan="3">질문내용</td>
			</tr>
			<tr>
				<td id="title" colspan="3">제목: ${ques.title}</td>
			</tr>
			<tr>
				<td>사용자: ${ques.nickname}</td>
				<td>등록일: ${ques.createDate}</td>
				<td>조회수: ${ques.views}</td>
			</tr>
			<tr>
				<td colspan="3">글 내용<br> ${ques.qContent}
				</td>
			</tr>
		</table>
		<a
			href="<c:url value='/question/update'><c:param name='qId' value='${ques.qId}'/></c:url>">수정</a>
		<a
			href="<c:url value='/question/delete'><c:param name='qId' value='${ques.qId}'/></c:url>"
			onclick="return questionRemove();">삭제</a> <a
			href="<c:url value='/question/list'/>" class="button">목록</a>
	</center>
</body>
</html>