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
<link rel=stylesheet href="<c:url value='/css/book/searchList.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/userMenu.css' />"
			type="text/css">
<title>검색 후 결과 화면(도서 리스트)</title>
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
							<c:set var="star" value="${b.averageStar}"/>
							<c:choose>
								<c:when test ="${star >= 0.1}">
									${b.averageStar}
								</c:when>
								<c:otherwise>
									리뷰가 존재하지 않습니다.
								</c:otherwise>
							</c:choose>
						</p>
					</figcaption>
				</figure>
			</a>
		</c:forEach>
	</div>

	<div class="btnMoveToTop" onclick="window.scrollTo(0,0);">TOP   ↑</div>

</body>
</html>