<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<meta charset="UTF-8">
<link rel=stylesheet href="<c:url value='/css/menu.css' />"
	type="text/css">
<title>검색 후 결과 화면(도서 리스트)</title>

<style>
#showListCate {
	width: 70px;
	height: 40px;
}


.btnMoveToTop {
	height:40px;
    width:40px;
    color: #555;
    font-size:14px;
    position:fixed;
    bottom:10px;
    right: 10px;
    z-index:999;
    -webkit-box-shadow: 0 1px 2px 0 #777;
    box-shadow: 0 1px 2px 0 #777;
    background-color:#ccc;
    text-align : center;
    cursor:pointer;
}


#bookInfoList {
	border: 0px;
	text-align: center;
}

.book {
	border: 1px solid;
	width: 360px;
	height: 370px;
	margin: 10px;
	padding: 7px;
	display: inline-block;
	vertical-align: top;
}

#book_cover {
	width: 150px;
	height: 200px;
	border: 1px solid;
}

figure {
	cursor: pointer;
}

figure>* {
	flex: 1;
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

b{
	font-size: large;
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
		<jsp:param name="selected" value="search" />
	</jsp:include>
	<%@ include file="./search.jsp"%>
	&nbsp; &nbsp;&nbsp; &nbsp;
	<br>

	<div id="bookInfoList">
		<c:forEach var="b" items="${books}">
			<a
				href="<c:url value='/book/bookInfo'>
					   <c:param name='ISBN' value='${b.ISBN}'/>
			 		   </c:url>">
				<figure class="book">
					<br>
					<img id="book_cover"
						src="<c:url value='${b.cover_path}' />" />
					<figcaption>
					<br>
						<b>${b.title}</b> <br><br>
						[ ${b.author} ] &nbsp; &lt; ${b.publisher} &gt;
						<p class="starScore">
							<img src="<c:url value='/images/search_star.PNG' />" />
							${b.averageStar}
						</p>
					</figcaption>
				</figure>
			</a>
		</c:forEach>
	</div>

	<div class="btnMoveToTop" onclick="window.scrollTo(0,0);">TOP   ↑</div>

</body>
</html>