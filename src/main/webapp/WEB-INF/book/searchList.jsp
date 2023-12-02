<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>검색 후 결과 화면(도서 리스트)</title>

<style>
#showListCate {
	width: 70px;
	height: 40px;
}

.btnMoveToTop {
	position: fixed;
	width: 30px;
	height: 50px;
	background-image: url(./img/moveToTop.PNG);
}

#bookInfoList {
	border: 0px;
	text-align: center;
}

.book {
	border: 1px solid;
	width: 290px;
	height: 270px;
	margin: 10px;
	padding: 7px;
	display: inline-block;
}

#book_cover {
	width: 130px;
	height: 130px;
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

a{
	text-decoration: none;
	color: black;
}

</style>
</head>

<body>
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
					<img id="book_cover" src="../../images/book_cover.PNG">
					<figcaption>
						<p>[ ${b.title} ]</p>
						<p>[ ${b.author} ] &nbsp; &lt; ${b.publisher} &gt;</p>
						<p class="starScore">
							<img src="../../images/search_star.PNG"> {b.averageStar}
						</p>
					</figcaption>
				</figure>
			</a>
		</c:forEach>
	</div>

	<div class="btnMoveToTop" />

</body>
</html>