<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>검색 창</title>

<style>
#logo {
	margin: 25px 30px;
	text-align: center;
}

.mainDiv {
	border: 0px;
	width: 510px;
	height: 30px;
}

#searchClick {
	width: 60px;
	height: 46px;
	background-image: url(./img/search_img.PNG);
}

#searchText {
	width: 360px;
	height: 40px;
}

input {
	vertical-align: middle;
}

#showDetailCate {
	width: 70px;
	height: 40px;
}

/* 카테고리 터치 후 나오는 화면 */
.subDiv {
	border: 0px;
	width: 510px;
	height: 300px;
	text-align: left;
}

#searchDetailCate {
	border: 0px;
	height: 25px;
	width: 500px;
}

#detailSearchText {
	width: 100px;
	height: 20px;
}

#checkSearch {
	border: 1px solid;
	background-color: aqua;
	width: 350px;
	height: 230px;
	font-size: 11px;
	text-align: left;
}
</style>
</head>
<body>
</head>

<script type="text/javascript">
	function search() {
		if (searchForm.searchText.value == "") {
			alert("검색어를 입력하세요");
			history.back();
		}
		searchForm.submit();
	}
</script>

<body>
	<%@ include file="./menubar.jsp"%>
	<center>
		<form name="searchForm" method="GET"
			action="<c:url value='/book/searchList' />">
			<div class="mainDiv">
				<input type="text" id="searchText" name="searchText"> <input type="button"
					id="searchClick" onClick="search()">
			</div>

			<br> <br>
		</form>

	</center>


	<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script>
		$(document).ready(function() {
			$("#showDetailCate").click(function() {
				$(".subDiv").toggle();
			});
		});
	</script>
</body>
</html>