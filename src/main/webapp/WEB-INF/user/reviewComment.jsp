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
<link rel=stylesheet href="<c:url value='/css/user/mypage_list.css' />" type="text/css">
<title>리뷰 댓글</title>
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
	<div align="center">
		<table>
			<tr>
				<td><h3>${nickname}'s 리뷰 댓글</h3></td>
			</tr>
		</table>
		<br>
		<c:choose>
			<c:when test="${not empty reviewComment}">
				<table>
					<tr>
						<th>댓글</th>
						<th>작성일</th>
					</tr>
					
					<c:forEach var="reviewCmt" items="${reviewComment}">
						<tr>
							<td><a href="<c:url value='/review/view'><c:param name='reviewId' value='${reviewCmt.reviewId}'/></c:url>" id="title">
							${reviewCmt.rContent}</a></td>
							<td>${reviewCmt.createDate}</td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<span id="notice">작성한 리뷰 댓글이 없습니다. 댓글을 작성해보세요!</span>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>