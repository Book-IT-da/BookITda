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
			<td>작성한 댓글</td>
		</tr>
	</table>
	<br>
	<table>
		<tr>
			<td>내용</td>
			<td>작성일</td>
		</tr>
		
		<c:forEach var="answer" items="${answerList}">
			<tr>
				<!-- 타이틀에 링크 넣어야 함 -->
				<td>${answer.answer} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>${answer.createDate}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>