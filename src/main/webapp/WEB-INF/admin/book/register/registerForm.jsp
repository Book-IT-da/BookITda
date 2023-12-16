<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<link rel=stylesheet href="<c:url value='/css/menu.css' />"
	type="text/css">
<link rel=stylesheet href="<c:url value='/css/logo.css' />"
	type="text/css">
<title>관리자 책 정보 추가</title>

<style>
table {
	border: 1px solid;
	border-collapse: collapse;
}

th {
	border: 1px solid;
	width: 100px;
}

td {
	border: 1px solid;
	width: 600px;
}

textarea {
	width: 595px;
	height: 115px;
}

#longInfo {
	height: 150px;
}

textarea {
	width: 590px;
	border: 0;
	resize: none;
}

#longTextarea {
	height: 100px;
}

#btn_modify {
	background-color: green;
	height: 50px;
	width: 100px;
}

#btn_delete {
	background-color: red;
	height: 50px;
	width: 100px;
}

.bookType_cate {
	height: 50px;
}

.bookType {
	height: 50px;
}

a, button {
	cursor: pointer;
}
</style>

<script>
	function bookRegister() {
		if (confirm("정말로 추가하시겠습니까?")) {
			registerForm.submit();
		}
	}
</script>

</head>
<body>
	<%
	String[] level = { "초보자", "중급자", "상급자" };

	String[] lan = { "java", "python", "c", "c++", "kotlin", "lua", "css", "javaScript", "swift", "c#", "node.js",
			"typeScript", };
	
	String[] keywords = {"X", "자바 입문", "클라우드 서비스","하드웨어", "알고리즘", "학습 사이트 제공", "비전공자", "게임"};
	%>
	<header>
		<link rel=stylesheet href="<c:url value='/css/userMenu.css' />"
			type="text/css">

		<div>
			<jsp:include page="../../../top/userMenubar.jsp" />
		</div>
		<div>
			<jsp:include page="../../../top/logo.jsp" />
		</div>
	</header>
	<jsp:include page="../../../top/menubar.jsp">
		<jsp:param name="selected" value="bookman" />
	</jsp:include>

	<br>

	<center>
		<h3>&lt; 도서 정보 추가 &gt;</h3>
		<form name="registerForm" method="POST"
			action="<c:url value='/admin/book/register' />">
			<table>
				<tr>
					<th>표지(경로)</th>
					<td>&nbsp;<input type="text" name="cover_path"></td>
				</tr>
				<tr>
					<th>ISBN</th>
					<td>&nbsp;<input type="text" name="ISBN"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td>&nbsp;<input type="text" name="title"></td>
				</tr>
				<tr>
					<th>저자</th>
					<td>&nbsp;<input type="text" name="author"></td>
				</tr>
				<tr>
					<th>출판사</th>
					<td>&nbsp;<input type="text" name="publisher"></td>
				</tr>
				<tr>
					<th>출판 날짜</th>
					<td>&nbsp;<input type="text" name="publicationDate"
						placeholder="ex)2023-01-10">
					</td>
				</tr>
				<tr>
				<tr id="longInfo">
					<th>책 소개</th>
					<td><textarea id="longinput" type="text" name="bookInfo"></textarea></td>
				</tr>
				<tr id="longInfo">
					<th>목차</th>
					<td><textarea id="longinput" type="text" name="bookIndex"></textarea></td>
				</tr>
				<tr id="longInfo">
					<th>저자 소개</th>
					<td><textarea id="longinput" type="text" name="authorInfo"></textarea></td>
				</tr>
			</table>

			<br> <br>

			<table>
				<tr class="bookType_cate">
					<th>카테고리</th>
					<td>
						&nbsp;	
						<select name="category">
							<c:forEach var="c" items="${categories}">
								<option value="${c.category}">${c.category}
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr class="bookType">
					<th>수준</th>
					<td>
						<%
						for (int i = 0; i < level.length; i++) { %> 
						&nbsp;<input type="radio" name="itLevel" value="<%=level[i]%>"><%=level[i]%> 
						<%} %>
					</td>
				</tr>
				<tr class="bookType">
					<th>언어</th>
					<td>
					<br>
						<% for (int i = 0; i < lan.length; i++) { %>
							&nbsp;<input type="radio" name="language" value="<%=lan[i]%>"><%=lan[i]%> &emsp;&emsp;
							<% if(i!=0 && i%5==0) { %>
								<br>
							<%} %>
						<%} %>
						<br><br>
					</td>
				</tr>
				<tr class="bookType">
					<th>키워드 <br> (최대 3개)
					</th>
					<td>1. <select name="keyword1">
						<% for (int i = 0; i < keywords.length; i++) { 
							if (i==0) {%>
								<option value="<%=keywords[i]%>" selected><%=keywords[i]%>
							<%} else {%>
							<option value="<%=keywords[i]%>"><%=keywords[i]%>
							<%} %>
						<%}%>
						</select> 
						&nbsp;&nbsp;
						
						2. <select name="keyword2">
						<% for (int i = 0; i < keywords.length; i++) { 
							if (i==0) {%>
								<option value="<%=keywords[i]%>" selected><%=keywords[i]%>
							<%} else {%>
							<option value="<%=keywords[i]%>"><%=keywords[i]%>
							<%} %>
						<%}%>
						</select> 
						&nbsp;&nbsp;
						
						3. <select name="keyword3">
						<% for (int i = 0; i < keywords.length; i++) { 
							if (i==0) {%>
								<option value="<%=keywords[i]%>" selected><%=keywords[i]%>
							<%} else {%>
							<option value="<%=keywords[i]%>"><%=keywords[i]%>
							<%} %>
						<%}%>
						</select>
					</td>
				</tr>
			</table>

			<p />
			<button type="button" id="btn_modify" onClick="bookRegister()">추가</button>

			</a>
		</form>
	</center>

</body>
</html>