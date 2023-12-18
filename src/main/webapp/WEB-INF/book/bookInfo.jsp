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
<link rel=stylesheet
	href="<c:url value='/css/review/bookInfo_reviewList.css' />"
	type="text/css">
<link rel=stylesheet href="<c:url value='/css/book/bookInfo.css' />"
	type="text/css">
<link rel=stylesheet href="<c:url value='/css/userMenu.css' />"
			type="text/css">
<title>도서 정보 열람</title>

<script>
	function loginAlert() {
		alert("로그인해야 이용가능한 서비스입니다.");
	}
</script>

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
					<c:set var="star" value="${book.averageStar}" /> <c:choose>
						<c:when test="${star >= 0.1}">
							${book.averageStar}
						</c:when>
						<c:otherwise>
							리뷰가 존재하지 않습니다.
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
		<br>
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


		<c:if test="${sessionScope.userId == 'admin'}">
			<a
				href="<c:url value='/admin/book/modify'>
					   <c:param name='ISBN' value='${book.ISBN}'/>
			 		   </c:url>">
				<button type="button" id="btn_bookInfo_modify">도서 정보 변경</button>
			</a>
		</c:if>

		<hr>
		<br>
		<br>

		<jsp:include page="../review/bookInfo_reviewList.jsp" />

		<div class="btnMoveToTop" onclick="window.scrollTo(0,0);">TOP ↑</div>
		<br> <br>

	</div>

</body>
</html>