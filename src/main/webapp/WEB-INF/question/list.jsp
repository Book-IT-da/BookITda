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
	function loginAlert() {
		alert('로그인해야 이용가능한 서비스입니다.')
	}
</script>

<style>
#search_input {
	font-size: 16px;
	width: 325px;
	padding: 10px;
	border: 0.5px;
}

#search_button {
	width: 100px;
	height: 35px;
	background: #1b5ac2;
	outline: none;
	color: #ffffff;
}

li {
	list-style: none;
}

table {
	width: 60%;
	border-collapse: collapse; 
}

tr:nth-child(even) td {
  background: #f0f8ff;
}

th, td {
	height: 50px;
	text-align: center;
}

th {
	font-size: 20px;
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
		<jsp:param name="selected" value="qna" />
	</jsp:include>
	<main>
		<div align="center">
			<div>
				<form name="form" mehtod="GET"
					action="<c:url value='/question/list'/>">
					<label for="type"> </label> <select name="type" id="type">
						<option value="title" selected>제목</option>
						<option value="writer">작성자</option>
					</select> <input id="search_input" name="searchKeyword" type="text"
						placeholder="검색어를 입력하세요">
					<button id="search_button" onClick="search()">검색</button>
				</form>
			</div>
			<hr>
			<div align="right">
				<button id="btn">
					<a href="<c:url value='/question/create'/>">글쓰기</a>
				</button>
			</div>
			<table>
				<tr>
					<th>제목</th>
					<th>조회수</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
				<c:forEach var="ques" items="${questionList}">
					<tr>
						<td>
						<c:if test="${sessionScope.userId!=null}">
						<a href="<c:url value='/question/view'><c:param name='qId' value='${ques.qId}'/></c:url>">${ques.title}</a></c:if>
						<c:if test="${sessionScope.userId==null}">
						<a href="javascript:void(0);" onclick="loginAlert()">${ques.title}</a>
						</c:if>
						</td>
						<td>${ques.views}</td>
						<td>${ques.nickname}</td>
						<td>${ques.createDate}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</main>
</body>
</html>