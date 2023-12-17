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
		<title>사용자 상세보기</title>
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
					<td>${user.userId}</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>${user.name}</td>
				</tr>
				<tr>
					<td>닉네임</td>
					<td>${user.nickname}</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>${user.email}</td>
				</tr>
			</table>
			<input type="hidden" name="userId" value="${user.userId}">
			<a href="<c:url value='/user/update'><c:param name='userId' value='${user.userId}'/></c:url>">수정</a>
			<a href="<c:url value='/admin/user/delete'><c:param name='userId' value='${user.userId}'/></c:url>">삭제</a>
			<a href="<c:url value='/admin/user/list' />">목록</a>
		</div>
	</main>
</body>
</html>