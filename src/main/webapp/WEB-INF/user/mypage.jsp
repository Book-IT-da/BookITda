<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel=stylesheet href="<c:url value='/css/logo.css' />" type="text/css">

<title>마이페이지</title>
<style>
.menu {
	margin-left: 10px;
}

ul {
	list-style-type: none;
	padding: 0px;
	margin: 0px;
	width: 200px;
	height: 100%;
	overflow: auto;
	position: fixed;
}

li a {
	text-decoration: none;
	padding: 10px;
	display: block;
	color: #000;
	font-weight: bold;
}

li a:hover {
	background: #333;
	color: #fff;
}

.box {
	margin-left: 30px;
	width: 1300px;
	height: 150px;
	color: white;
	background-color: skyblue;
}

.content {
	border: 1px solid skyblue;
}

h1 {
	padding-top: 40px;
	font-size: 50px;
}
</style>
</head>
<body>
	<div>
		<%@ include file="../top/logo.jsp"%>
	</div>
	<div class="menu">
		<ul>
			<h2>회원정보</h2>
			<c:set var="userId" value="${sessionScope.userId}" />
			<li><a
				href="<c:url value='/user/update'><c:param name='userId' value='${userId}'/></c:url>">회원정보수정</a></li>
			<li><a
				href="<c:url value='/user/delete'><c:param name='userId' value='${userId}'/></c:url>">회원탈퇴</a></li>

			<h2>나의 게시글</h2>
			<li><a href="">리뷰</a></li>
			<li><a
				href="<c:url value='/user/question'><c:param name='userId' value='${userId}'/></c:url>">Q&A
					게시글 - 질문</a></li>
			<li><a
				href="<c:url value='/user/answer'><c:param name='userId' value='${userId}'/></c:url>">Q&A
					게시글 - 댓글</a></li>
		</ul>
	</div>
	<center>
		<div>
			<div class="box">
				<h1>안녕하세요</h1>
			</div>
		</div>
	</center>
</body>
</html>