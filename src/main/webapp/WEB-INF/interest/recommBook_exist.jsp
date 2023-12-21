<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="java.util.List"%>
<%@ page import="model.RecommBook"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="userId" value="${userId}" />
<%
String userId = (String) request.getSession().getAttribute("userId");
List<RecommBook> recommBookList = (List<RecommBook>) request.getSession().getAttribute("recommBookList");
request.setAttribute("recommBookList", recommBookList);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/userMenu.css' />"
	type="text/css">
<link rel=stylesheet href="<c:url value='/css/logo.css' />"
	type="text/css">
<link rel=stylesheet href="<c:url value='/css/menu.css' />"
	type="text/css">
<link rel=stylesheet
	href="<c:url value='/css/interest/recommBook_exist.css' />"
	type="text/css">
<title>추천 도서 보기</title>
<script>
	function updateInterest() {
		document.getElementById('interestForm').submit();
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
	<div class="menubar">
		<jsp:include page="../top/menubar.jsp">
			<jsp:param name="selected" value="recomm" />
		</jsp:include>
	</div>
	<div align="right">
		<form action="<c:url value='/get/categoryList'/>" method="POST" id="interestForm">
			<input type="button" value="관심사 재설정" onClick="updateInterest()">
		</form>
	</div>
	<center>
	<div class="bigContainer" align="center">
		<h2 class="question">${userId}님께어울리는 책을 추천할게요</h2>
		<br>
		<div class="question-info">사용자의 수준에 일치하는 책 중 관심 분야 및 언어에 더 일치하는
			순서로 6권을 제시하였습니다.</div>
		<div class="container">
			<table class="book_box">
				<c:forEach var="recommBook" items="${recommBookList}"
					varStatus="loop">
					<c:if test="${loop.index % 3 == 0}">
						<tr>
					</c:if>
					<td><c:url var="bookInfoURL" value="../book/bookInfo">
							<c:param name="ISBN" value="${recommBook.isbn}" />
						</c:url> <a href="${bookInfoURL}">
							<div class="book">
								<img class="bookImg"
									src="<c:url value='${recommBook.cover_path}' />">
								<h2 class="title">${recommBook.title}</h2>
								<p class="author">${recommBook.author}</p>
								<c:set var="star" value="${recommBook.avgstar}"/>
	                            <c:choose>
	                                <c:when test ="${star >= 0.1}">
	                                    ${recommBook.avgstar}
	                                </c:when>
	                                <c:otherwise>
	                                    리뷰가 존재하지 않습니다.
	                                </c:otherwise>
	                            </c:choose>
							</div>
					</a></td>
					<c:if test="${loop.index % 3 == 2 or loop.last}">
						</tr>
					</c:if>
				</c:forEach>
			</table>
		</div>
	</div>
	</center>
</body>

</html>