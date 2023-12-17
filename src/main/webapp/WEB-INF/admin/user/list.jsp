<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel=stylesheet href="<c:url value='/css/userMenu.css' />" type="text/css">
		<link rel=stylesheet href="<c:url value='/css/logo.css' />" type="text/css">
		<link rel=stylesheet href="<c:url value='/css/menu.css' />" type="text/css">
		<title>사용자 목록</title>
	<script type="text/javascript">
		
	</script>
</head>


<body>
	<header>
		<div>
			<jsp:include page="../../top/userMenubar.jsp" />
		</div>
		<div>
			<jsp:include page="../../top/logo.jsp" /> 
		</div>
	</header>
	<jsp:include page="../../top/menubar.jsp">
    	<jsp:param name="selected" value="userman" />
	</jsp:include>
	<main>
		<div align="center">
			<table>
				<tr>
					<td>사용자 ID</td>
					<td>닉네임</td>
					<td>이메일</td>
				</tr>
				<c:forEach var="user" items="${userList}" varStatus="status">
					<tr>
						<td>${status.index + 1}</td>
						<td>${user.userId}</td>
						<td><a href="">${user.nickname}</a></td>
						<td>${user.email}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</main>
</body>
</html>