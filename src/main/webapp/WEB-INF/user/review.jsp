<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>리뷰</title>
</head>
<body>
	<table>
		<tr>
			<td>작성한 리뷰</td>
		</tr>
	</table>
	<br>
	<table>
		<tr>
			<td>책 제목</td>
			<td>별점</td>
			<td>작성일</td>
			<td>한줄평</td>
		</tr>
		
		<c:forEach var="review" items="${review}">
			<tr>
				<td><a
					href="<c:url value='/review/view'>
					   <c:param name='reviewId' value='${review.reviewId}'/>
			 		 </c:url>">
						${review.title}</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>${review.stars}</td>
				<td>${review.createDate}</td>
				<td>${review.onelineReview}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>