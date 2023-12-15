<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Hahmlet&display=swap"
	rel="stylesheet">
<link rel=stylesheet href="<c:url value='/css/menu.css' />"
	type="text/css">
<meta charset="UTF-8">
<title>메인 페이지(랜덤 도서 리스트)</title>

<style>
#showListCate {
	width: 70px;
	height: 40px;
}

/*
.btnMoveToTop {
	position: fixed;
	width: 30px;
	height: 50px;
	background-image: url(./img/moveToTop.PNG);
}
*/

#bookInfoList {
	border: 0px;
	text-align: center;
}

#intro {
	background-color: pink;
	width: 90%;
	height: 90px;
	font-size: 25px;
	font-family: 'Hahmlet', serif;
	display: flex;
	justify-content: center;
	align-items: center;
}

.book {
	border: 1px solid;
	width: 320px;
	height: 340px;
	margin: 10px;
	padding: 7px;
	display: inline-block;
}

#book_cover {
	width: 150px;
	height: 200px;
}

figure {
	cursor: pointer;
}

figcaption {
	text-align: left;
}

.starScore {
	color: red;
}

#bookClick {
	border: 0;
	outline: 0;
	padding: 0;
	background: none;
	font: inherit;
}

a {
	text-decoration: none;
	color: black;
}
</style>
</head>

<body>
	<header>
		<link rel=stylesheet href="<c:url value='/css/userMenu.css' />"
			type="text/css">

		<div>
			<jsp:include page="../top/userMenubar.jsp" />
		</div>
		<div>
			<jsp:include page="../top/logo.jsp" />
		</div>
	</header>
	<jsp:include page="../top/menubar.jsp">
		<jsp:param name="selected" value="random" />
	</jsp:include>

	<%@ include file="./search.jsp"%>
	&nbsp; &nbsp;&nbsp; &nbsp;
	<br>

	<br>
	<center>
		<div id="intro">개발자들의 선택</div>
	</center>
	<br>

	<div id="bookInfoList">
		<c:forEach var="b" items="${books}">
			<a
				href="<c:url value='/book/bookInfo'>
					   <c:param name='ISBN' value='${b.ISBN}'/>
			 		   </c:url>">
				<figure class="book">
					<img id="book_cover"
						src="<c:url value='${b.cover_path}' />" />
					<figcaption>
						<p>[ ${b.title} ]</p>
						<p>[ ${b.author} ] &nbsp; &lt; ${b.publisher} &gt;</p>
						<p class="starScore">
							<img src="<c:url value='/images/search_star.PNG' />" />
							{b.averageStar}
						</p>
						<input type="hidden" id="ISBN" name="ISBN" value="${b.ISBN}">
					</figcaption>
				</figure>
			</a>
		</c:forEach>
	</div>

	<div class="btnMoveToTop" />

</body>
</html>