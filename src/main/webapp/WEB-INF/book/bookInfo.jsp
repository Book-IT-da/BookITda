<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<link rel=stylesheet href="<c:url value='/css/menu.css' />"
	type="text/css">
	<link rel=stylesheet href="<c:url value='/css/logo.css' />" type="text/css">
	<link rel=stylesheet href="<c:url value='/css/review/bookInfo_reviewList.css' />" type="text/css">
<title>도서 정보 열람</title>

<style>

/*도서 정보*/
#first {
	margin-left: 55px;
}

table {
	border: 0px;
	border-collapse: collapse;
	margin-top: 15px;
	margin-left: 50px;
}

td {
	width: 500px;
	text-align: left;
}

p {
	font-size: 20px;
	text-align: left;
	margin-left: 50px;
}

#bookTotalInfo {
	border: 0px;
	text-align: center;
	height: 180px;
}

#book_cover {
	width: 250px;
	height: 300px;
}

#bookInfo {
	height: 180px;
	margin-left: 50px;
	margin-left: 50px;
}

#bookIndex {
	height: 180px;
	margin-left: 50px;
}

#writerInfo {
	height: 180px;
	margin-left: 50px;
}

textarea {
	width: 95%;
	height: 175px;
	border: none;
}

hr {
	width: 98%;
}

#btn_bookInfo_modify {
	width: 120px;
	height: 50px;
	background-color: yellow;
}

#btn_bookInfo_delete {
	width: 120px;
	height: 50px;
	background-color: red;
}

a, button {
	text-decoration: none;
	color: black;
	cursor: pointer;
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
	
	<br>
	<!--도서 정보-->
	<div id="first">| &nbsp; ${book.category} &nbsp; | &nbsp;
		${book.itLevel} &nbsp; | &nbsp; ${book.language} &nbsp; |</div>

	<div id="bookTotalInfo">
		<table>
			<tr>
				<th rowspan="9"><img id="book_cover"
					src="<c:url value='${book.cover_path}' />" /></th>
				<td>&nbsp; ISBN : ${book.ISBN}</td>
			</tr>

			<tr>
				<td>&nbsp; 제목 : ${book.title}</td>
			</tr>

			<tr>
				<td>&nbsp; 저자 : ${book.author}</td>
			</tr>

			<tr>
				<td>&nbsp; 출판사 : ${book.publisher}</td>
			</tr>

			<tr>
				<td>&nbsp; 출판 날짜 : ${book.publicationDate}</td>
			</tr>

			<tr>
				<td>&nbsp <img src="<c:url value='/images/search_star.PNG' />" />
					${book.averageStar}
				</td>
			</tr>
		</table>

		<hr>

		<p>[책소개]</p>
		<div id="bookInfo">
			<textarea readonly> ${book.bookInfo} </textarea>
		</div>

		<hr>

		<p>[목차]</p>
		<div id="bookIndex">
			<textarea readonly> ${book.bookIndex} </textarea>
		</div>

		<hr>

		<p>[저자 소개]</p>
		<div id="writerInfo">
			<textarea readonly>${book.authorInfo}</textarea>
		</div>

		<hr>

		<p>#${book.keyword1} &nbsp; &nbsp; #${book.keyword2} &nbsp; &nbsp;
			#${book.keyword3}</p>


		<jsp:include page="../review/bookInfo_reviewList.jsp" /> 
		
		<a
			href="<c:url value='/admin/book/modify'>
					   <c:param name='ISBN' value='${book.ISBN}'/>
			 		   </c:url>">
			<button type="button" id="btn_bookInfo_modify">도서 정보 변경</button>
		</a>

		<!-- <button type="button" id="btn_bookInfo_delete">삭제</button> -->
		<br> <br>

	</div>

</body>
</html>