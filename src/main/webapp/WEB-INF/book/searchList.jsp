<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
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
	width: 240px;
	height: 250px;
	margin: 10px;
	padding: 7px;
	display: inline-block;
}

#book_cover {
	width: 130px;
	height: 130px;
}

figcaption {
	text-align: left;
}

.starScore {
	color: red;
}
</style>
</head>
<body>
	<%@ include file="./search.jsp"%>
	&nbsp; &nbsp;&nbsp; &nbsp;
	<form>
		<input type="checkbox" name="showCheck" value="book_title" checked>제목
		<input type="checkbox" name="showCheck" value="author">저자 <input
			type="checkbox" name="showCheck" value="publisher"> 출판사 <input
			type="checkbox" name="showCheck" value="keyword">키워드 &nbsp;
		&nbsp;
		<button type="button" id="checkSetting">설정</button>
	</form>
	<br>

	<div id="bookInfoList">

		<c:forEach var="b" items="${books}">
			<figure class="book">
				<a href="<c:url value='/book/info'>
					<img id="book_cover" src="./images/book_cover.PNG">
				</c:url>"></a>
				<figcaption>
					<p>[ "${b.title}" } ]</p>
					<p>[ "${b.author}" ] &nbsp; &lt; "${b.publisher}" &gt;</p>
					<p class="starScore">
						<img src="./img/search_star.PNG"> "{b.averageStar}"
					</p>
				</figcaption>
			</figure>
		</c:forEach>

	</div>

	<div class="btnMoveToTop" />

</body>
</html>