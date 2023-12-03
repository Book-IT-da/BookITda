<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel=stylesheet href="<c:url value='/css/userMenu.css' />"
	type="text/css">
<link rel=stylesheet href="<c:url value='/css/logo.css' />"
	type="text/css">
<link rel=stylesheet href="<c:url value='/css/menu.css' />"
	type="text/css">
<title>Q&A 게시판</title>
<script>
	function search() {
		if (form.searchKeyword.value == "") {
			alert("검색어를 입력하세요.");
			return false;
		}
		form.submit();
	}
	function questionCreate(targetUri) {
		action = targetUri;
		form.method = "GET";
		form.submit();
	}
</script>

<style>
#logo {
	margin: 25px 30px;
	text-align: center;
}

li {
	list-style: none;
}

form {
	margin-top: 50px;
	margin-left: 850px;
}

table {
	width: 1200px;
}

th, td {
	height: 50px;
	text-align: center;
}

th {
	border-bottom: 1.5px solid black;
}

a {
	text-decoration-line: none;
	color: black;
}
</style>
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
	    	<jsp:param name="selected" value="review" />
	</jsp:include>
	<center>
		<form name="form" mehtod="GET"
			action="<c:url value='/question/list'/>">
			<label for="type"> </label> <select name="type" id="type">
				<option value="title" selected>제목</option>
				<option value="writer">작성자</option>
			</select>
			<div>
				<input name="searchKeyword" type="text" placeholder="검색어를 입력하세요">
				<button onClick="search()">검색</button>
			</div>
		</form>
		<div>
			<button id="btn">
				<a href="<c:url value='/question/create'/>">글쓰기</a>
			</button>
		</div>
		<hr>
		<table>
			<tr>
				<td>제목</td>
				<td>조회수</td>
				<td>작성자</td>
				<td>작성일</td>
			</tr>
			<c:forEach var="ques" items="${questionList}">
				<tr>
					<td><a
						href="<c:url value='/question/view'>
					   <c:param name='qId' value='${ques.qId}'/>
			 		 </c:url>">
							${ques.title}</a></td>
					<td>${ques.views}</td>
					<td>${ques.nickname}</td>
					<td>${ques.createDate}</td>
				</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>