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
<title>리뷰</title>
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
			<td><h3>${nickname}'s 리뷰</h3></td>
		</tr>
	</table>
	<br>
		<c:choose>
			<c:when test="${not empty review}">
				<table>
					<tr>
						<th>책 제목</th>
						<th>별점</th>
						<th>작성일</th>
						<th>한줄평</th>
					</tr>
					
					<c:forEach var="review" items="${review}">
						<tr>
							<td><a href="<c:url value='/review/view'><c:param name='reviewId' value='${review.reviewId}'/></c:url>">${review.title}</a></td>
							<td>${review.stars}</td>
							<td>${review.createDate}</td>
							<td>${review.onelineReview}</td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<span id="notice">작성한 리뷰가 없습니다. 리뷰를 작성해보세요!</span>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>