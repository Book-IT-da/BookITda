<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 책 정보 추가</title>

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

textarea {
	width: 595px;
	height: 115px;
}

#longInfo {
	height: 150px;
}

textarea {
	width: 590px;
	border: 0;
	resize: none;
}

#longTextarea {
	height: 100px;
}

#btn_modify {
	background-color: green;
	height: 50px;
	width: 100px;
}

#btn_delete {
	background-color: red;
	height: 50px;
	width: 100px;
}

.bookType_cate {
	height: 90px;
}

.bookType {
	height: 50px;
}

a, button{
	cursor: pointer;
}

</style>

<script>
function bookRegister() {
	registerForm.submit();
}

//function bookDelete(targetUri){
//	updateForm.action = targetUri;
//	updateForm.submit();
//}
</script>

</script>

</head>
<body>
	<header>
		<center>
			<h3>&lt; 도서 정보 추가 &gt;</h3>
		</center>
	</header>

	<center>
		<form name="registerForm" method="POST" action="<c:url value='/admin/book/register' />">
			<table>
				<tr>
					<th>표지(경로)</th>
					<td>&nbsp;<input type="text" name="cover_path"
						value="${book.cover_path}"></td>
				</tr>
				<tr>
					<th>ISBN</th>
					<td>&nbsp;<input type="text" name="ISBN" value="${book.ISBN}"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td>&nbsp;<input type="text" name="title"
						value="${book.title}"></td>
				</tr>
				<tr>
					<th>저자</th>
					<td>&nbsp;<input type="text" name="author"
						value="${book.author}"></td>
				</tr>
				<tr>
					<th>출판사</th>
					<td>&nbsp;<input type="text" name="publisher"
						value="${book.publisher}"></td>
				</tr>
				<tr>
					<th>출판 날짜</th>
					<td>&nbsp;<input type="text" name="publicationDate"
						value="${book.publicationDate }">
					</td>
				</tr>
				<tr>
				<tr id="longInfo">
					<th>책 소개</th>
					<td><textarea id="longinput type=" text"" name="bookInfo"> ${book.bookInfo} </textarea></td>
				</tr>
				<tr id="longInfo">
					<th>목차</th>
					<td><textarea id="longinput type=" text"" name="bookIndex"> ${book.bookIndex} </textarea></td>
				</tr>
				<tr id="longInfo">
					<th>저자 소개</th>
					<td><textarea id="longinput type=" text"" name="authorInfo"> ${book.authorInfo} </textarea></td>
				</tr>
			</table>

			<br> <br>

			<table>
				<tr class="bookType_cate">
					<th>카테고리</th>
					<td>&nbsp;<input type="text" name="category"
						value="${book.category}">
					</td>
				</tr>
				<tr class="bookType">
					<th>수준</th>
					<td>&nbsp;<input type="text" name="itLevel"
						value="${book.itLevel}"></td>
				</tr>
				<tr class="bookType">
					<th>언어</th>
					<td>&nbsp;<input type="text" name="language"
						value="${book.language}">
					</td>
				</tr>
				<tr class="bookType">
					<th>키워드 <br> (최대 3개)
					</th>
					<td>1. <input type="text" name="keyword1"
						value="${book.keyword1}"> 2. <input type="text"
						name="keyword2" value="${book.keyword2}"> 3. <input
						type="text" name="keyword3" value="${book.keyword3}">
					</td>
				</tr>
			</table>

			<p />			
			<button type="button" id="btn_modify" onClick="bookRegister()">추가</button>
			
		</a>
		</form>
	</center>

</body>
</html>