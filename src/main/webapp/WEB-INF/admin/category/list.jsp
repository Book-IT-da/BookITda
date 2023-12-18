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
<link rel=stylesheet href="<c:url value='/css/admin/category/list.css' />" type="text/css">
<title>관리자 - 카테고리 리스트</title>

</head>

<script>
	function modifyCate() {
		if (modifyCateForm.originalCate.value == ""){
			alert("수정될 카테고리를 입력해주세요.")
		}
		else if (modifyCateForm.renew.value == ""){
			alert("수정할 카테고리를 입력해주세요.")
		}
		else if (modifyCateForm.originalCate.value.length > 15){
			alert("15자를 초과하였습니다")
		}
		else if (modifyCateForm.renew.value.length > 15){
			alert("15자를 초과하였습니다")
		}
		else if(confirm("정말로 수정하시겠습니까?")== true){
			modifyCateForm.submit();
		}
	}

	function registerCate() {
		if (registerCateForm.newCate.value ==""){
			alert("추가할 카테고리를 입력해주세요.");
		}
		else if (registerCateForm.newCate.value.length > 15){
			alert("15자를 초과하였습니다")
		}
		else if(confirm("정말로 추가하시겠습니까?")== true){
			registerCateForm.submit();
		}
	}
	
	function deleteCategory(){
		if (deleteCategoryForm.deleteCate.value ==""){
			alert("삭제할 카테고리를 입력해주세요.");
		}
		else if(confirm("정말로 삭제하시겠습니까?")== true){
			deleteCategoryForm.submit();
		}
	}

</script>

<body>
	<header>
		<link rel=stylesheet href="<c:url value='/css/userMenu.css' />"
			type="text/css">

		<div>
			<jsp:include page="../../top/userMenubar.jsp" />
		</div>
		<div>
			<jsp:include page="../../top/logo.jsp" />
		</div>
	</header>
	<jsp:include page="../../top/menubar.jsp">
		<jsp:param name="selected" value="cateman" />
	</jsp:include>
	
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
					<button type="button" class="endButton" onClick="deleteCategory()">삭제</button>
				</form>

			</div>

		</div>
	</center>



</body>
</html>