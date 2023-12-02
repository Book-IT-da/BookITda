<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 책 정보 수정</title>

<style>
table {
	border: 1px solid;
	border-collapse: collapse;
}

th {
	border: 1px solid;
	width: 100px;
}

td {
	border: 1px solid;
	width: 600px;
}

#longInfo {
	height: 100px;
}

textarea {
	width: 590px;
	border: 0;
	resize: none;
}

#longTextarea {
	height: 100px;
}

#btn_cancel {
	background-color: red;
	height: 50px;
	width: 100px;
}

#btn_add {
	background-color: green;
	height: 50px;
	width: 100px;
}

.bookType_cate {
	height: 90px;
}

.bookType {
	height: 50px;
}
</style>
<script>
function bookAdd(){
	updateForm.commit();
}
</script>
</head>
<body>
	<header>
		<center>
			<h3>&lt; 도서 추가 &gt;</h3>
		</center>
	</header>

	<center>
		<form name="updateForm" method="POST"
			action="<c:url value='/admin/book/register' />">
			<table>
				<tr>
					<th>표지(경로)</th>
					<td>&nbsp;<input type="text" name="cover_path"></td>
				</tr>
				<tr>
					<th>ISBN</th>
					<td>&nbsp;<input type="text" name="ISBN"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td>&nbsp;<input type="text" name="title"></td>
				</tr>
				<tr>
					<th>저자</th>
					<td>&nbsp;<input type="text" name="author""></td>
				</tr>
				<tr>
					<th>출판사</th>
					<td>&nbsp;<input type="text" name="publisher"></td>
				</tr>
				<tr>
					<th>출판 날짜</th>
					<td>&nbsp;<input type="text" name="publicationDate">
					</td>
				</tr>
				<tr>
				<tr id="longInfo">
					<th>책 소개</th>
					<td><textarea id="longinput type=" text"" name="bookInfo"></textarea></td>
				</tr>
				<tr id="longInfo">
					<th>목차</th>
					<td><textarea id="longinput type=" text"" name="bookIndex"></textarea></td>
				</tr>
				<tr id="longInfo">
					<th>저자 소개</th>
					<td><textarea id="longinput type=" text"" name="authorInfo"></textarea></td>
				</tr>
			</table>

			<br> <br>

			<table>
				<tr class="bookType_cate">
					<th>카테고리</th>
					<td>&nbsp;<input type="text" name="category">
					</td>
				</tr>
				<tr class="bookType">
					<th>수준</th>
					<td>&nbsp;<input type="text" name="itLevel"></td>
				</tr>
				<tr class="bookType">
					<th>언어</th>
					<td>&nbsp;<input type="text" name="language"></td>
				</tr>
				<tr class="bookType">
					<th>키워드 <br> (최대 3개)
					</th>
					<td>1. <input type="text" name="keyword1"> 
						2. <input type="text"name="keyword2"> 
						3. <input type="text" name="keyword3">
					</td>
				</tr>
			</table>

			<button type="button" id="btn_add" onClick="bookAdd()">추가</button>
		</form>
	</center>

</body>
</html>