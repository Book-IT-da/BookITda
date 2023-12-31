<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel=stylesheet href="<c:url value='/css/book/search.css' />" type="text/css">
<title>검색 창</title>
</head>
<body>

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