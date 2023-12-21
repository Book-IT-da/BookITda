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
<link rel=stylesheet href="<c:url value='/css/user/mypage.css' />" type="text/css">
<title>마이페이지</title>
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
	<main>
		<div align="center">
			<div class="box">
				<h1>${nickname}'s MY PAGE</h1>
			</div>
			<div align="center" class="menu">
				<ul>
					<h2>회원정보</h2>
					<c:set var="userId" value="${sessionScope.userId}" />
					<li><a
						href="<c:url value='/user/update'><c:param name='userId' value='${userId}'/></c:url>">회원정보수정</a></li>
					<li><a
						href="<c:url value='/user/delete'><c:param name='userId' value='${userId}'/></c:url>">회원탈퇴</a></li>
				</ul>
				<ul>
					<h2>나의 게시글</h2>
					<li><a href="<c:url value='/user/review'><c:param name='userId' value='${userId}'/></c:url>">리뷰</a></li>
					<li><a href="<c:url value='/user/review/comment'><c:param name='userId' value='${userId}'/></c:url>">리뷰 댓글</a></li>
					<li><a
						href="<c:url value='/user/question'><c:param name='userId' value='${userId}'/></c:url>">Q&A
							게시글 - 질문</a></li>
					<li><a
						href="<c:url value='/user/answer'><c:param name='userId' value='${userId}'/></c:url>">Q&A
							게시글 - 댓글</a></li>
				</ul>
			</div>
		</div>
	</main>
</body>
</html>