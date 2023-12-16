<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>리뷰 댓글</title>
</head>
<body>
	<table>
		<tr>
			<td>작성한 리뷰 댓글</td>
		</tr>
	</table>
	<br>
	<table>
		<tr>
			<td>댓글</td>
			<td>작성일</td>
		</tr>
		
		<c:forEach var="reviewCmt" items="${reviewComment}">
			<tr>
				<td><a
					href="<c:url value='/review/view'>
					   <c:param name='reviewId' value='${reviewCmt.reviewId}'/>
			 		 </c:url>">
						${reviewCmt.rContent}</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>${reviewCmt.createDate}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>