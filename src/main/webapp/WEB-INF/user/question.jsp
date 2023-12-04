<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Q&A 게시글</title>
</head>
<body>
	<table>
		<tr>
			<td>작성한 질문 글들</td>
		</tr>
	</table>
	<br>
	<table>
		<tr>
			<td>제목</td>
			<td>조회수</td>
			<td>작성일</td>
		</tr>
		
		<c:forEach var="question" items="${questionList}">
			<tr>
				<td><a
					href="<c:url value='/question/view'>
					   <c:param name='qId' value='${question.qId}'/>
			 		 </c:url>">
						${question.title}</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>${question.views}</td>
				<td>${question.createDate}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>