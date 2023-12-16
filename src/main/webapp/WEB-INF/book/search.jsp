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

#searchClick {
	width: 100px;
	height: 35px;
	background: #1b5ac2;
	outline: none;
	color: #ffffff;
	
}

#searchText {
	font-size: 16px;
	width: 325px;
	padding: 10px;
	border: 0.5px;
}

input {
	vertical-align: middle;
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
			<div class="mainDiv">
				<select name="searchType">
					<option id="searchType" value="titleType" selected>제목 </option>
					<option id="searchType" value="authorType">저자 </option>
					<option id="searchType" value="publisherType">출판사</option>
					<option id="searchType" value="keywordType">키워드</option>
				</select>
			
				<input type="text" id="searchText" name="searchText" placeholder="검색어를 입력하세요"> 
				<input type="button" id="searchClick" onClick="search()" value="검색">
			</div>
		</center>
		<hr>
	</form>
	<br>
</body>
</html>