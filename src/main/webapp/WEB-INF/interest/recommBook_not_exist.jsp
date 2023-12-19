<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<link rel=stylesheet href="<c:url value='/css/userMenu.css' />"
	type="text/css">
<link rel=stylesheet href="<c:url value='/css/logo.css' />"
	type="text/css">
<link rel=stylesheet href="<c:url value='/css/menu.css' />"
	type="text/css">
<link rel=stylesheet
	href="<c:url value='/css/interest/recommBook_not_exist.css' />"
	type="text/css">
<meta charset="UTF-8">
<title>추천 도서 불러오기 실패</title>
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
		<jsp:param name="selected" value="recomm" />
	</jsp:include>
	<main>
		<div>
			<table class="book_box">
				<tr>
					<td>
						<h2 class="ment">아직 추천 도서를 찾지 못했어요!</h2>
					</td>
				</tr>
				<tr>
					<!-- 관심사를 설정-> 추천 책이 무조건 있음 but 설정을 skip하면 -> this page -->
					<td><c:if test="${sessionScope.userId != null}">
							<form id="createForm" action="<c:url value='/get/categoryList'/>"
								method="get">
								<button class="custom-button" type="button"
									onclick="createForm()">관심사 생성하기</button>
							</form>

							<script>
								function createForm() {
									document.getElementById("createForm")
											.submit();
								}
							</script>
						</c:if> <c:if test="${sessionScope.userId == null}">
							<a class="custom-button" href="<c:url value='/user/login/form'/>">로그인하러
								가기</a>
						</c:if></td>
				</tr>
			</table>
		</div>
	</main>
</body>
</html>