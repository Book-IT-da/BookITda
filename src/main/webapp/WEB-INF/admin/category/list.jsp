<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 - 카테고리 리스트</title>

<style>
table {
	border: 1px solid;
	border-collapse: collapse;
	text-align: center;
}

th {
	width: 200px;
}

tr {
	height: 40px;
	border: 1px solid;
}

#first {
	background-color: antiquewhite;
}

.number {
	width: 50px
}

#mainDiv {
	width: 280px;
}

.textType {
	width: 90px;
	height: 20px;
}

#subDiv {
	text-align: left;
}
</style>

</head>

<script>
	function modifyCate() {
		modifyCateForm.submit();
	}

	function registerCate() {
		registerCateForm.submit();
	}

</script>

<body>
	<link rel=stylesheet href="<c:url value='/css/logo.css' />"
		type="text/css">
	<header>
		<div>
			<jsp:include page="../../top/logo.jsp" />
		</div>
	</header>
	<hr>
	<br>

	<center>
		<h3>&lt; 카테고리 리스트 &gt;</h3>
	</center>

	<center>
		<div id="mainDiv">
			<center>
				<table>
					<tr id="first">
						<th class="number">no.</th>
						<th>카테고리</th>
						<c:set var="i" value="1" />
						<c:forEach var="c" items="${categories}">
							<tr>
								<td class="number"><c:out value="${i}" /></td>
								<td>${c.category}</td>
							</tr>
							<c:set var="i" value="${i+1}" />
						</c:forEach>
				</table>
				<br> <br>

			</center>

			<div id="subDiv">
				<form name="modifyCateForm" method="GET"
					action="<c:url value='/admin/category/modify' />">
					<input type="text" class="textType" name="originalCate"> ->
					<input type="text" class="textType" name="renew">
					<button type="button" class="endButton" onClick="modifyCate()">수정</button>
				</form>

				<br>
				<form name="registerCateForm" method="GET"
					action="<c:url value='/admin/category/register' />">
					<input type="text" class="textType" name="newCate">
					<button type="button" class="endButton" onClick="registerCate()">추가</button>
				</form>

				<br>
				<form name="deleteCategoryForm" method="GET"
					action="<c:url value='/admin/category/delete' />">
					<input type="text" class="textType" name="deleteCate">
					<button type="submit" class="endButton">삭제</button>
				</form>

			</div>

		</div>
	</center>



</body>
</html>