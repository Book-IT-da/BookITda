<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
	background-image: url('/images/search_img.PNG');
	cursor: pointer;
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

a, button {
	text-decoration: none;
	color: black;
	cursor: pointer;
}
</style>
</head>
<body>
</head>

<script type="text/javascript">
	function search() {
		if (searchForm.searchText.value == "") {
			alert("검색어를 입력하세요");
			//history.back();
		}
		searchForm.submit();
	}
</script>

<body>
	<form name="searchForm" method="GET"
		action="<c:url value='/book/searchList' />">
		<center>
			<!-- 
			<a
				href="<c:url value='/admin/book/register/form'>
			 		   </c:url>">
				<button type="button" id="btn_bookInfo_add">도서 추가</button>
			</a>
			
			<a
				href="<c:url value='/admin/category/list'>
			 		   </c:url>">
				<button type="button" id="btn_category">카테고리 관리</button>
			</a>
			
			
			 -->

			<div class="mainDiv">
				<input type="text" id="searchText" name="searchText"> <input
					type="button" id="searchClick" onClick="search()">
			</div>
			<br> <br>
		</center>

		<input type="radio" id="searchType" name="searchType"
			value="titleType" checked>제목 <input type="radio"
			id="searchType" name="searchType" value="authorType">저자 <input
			type="radio" id="searchType" name="searchType" value="publisherType">
		출판사 <input type="radio" id="searchType" name="searchType"
			value="keywordType">키워드
	</form>


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