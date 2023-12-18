<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel=stylesheet href="<c:url value='/css/userMenu.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/logo.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/menu.css' />" type="text/css">
<title>Q&A 게시글</title>
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
				<td><a
					href="<c:url value='/question/view'>
					   <c:param name='qId' value='${answer.qId}'/>
			 		 </c:url>">
						${answer.answer}</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><fmt:formatDate value="${answer.createDate}" pattern="yyyy-MM-dd" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>