<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<link rel=stylesheet href="<c:url value='/css/menu.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/logo.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/admin/book/updateForm.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/userMenu.css' />" type="text/css">
<title>관리자 책 정보 수정</title>

<script>
	function bookModify() {
		var pubD = updateForm.publicationDate.value;
		
		if(updateForm.ISBN.value.length != 13){
			alert("[ISBN] 13자리가 아닙니다.")
		}
		else if(updateForm.title.value == ""){
			alert("제목을 입력하세요.")
		}
		else if(updateForm.title.value.length > 40){
			alert("[제목] 40자를 초과했습니다.")
		}
		else if(updateForm.author.value == ""){
			alert("저자를 입력하세요.")
		}
		else if(updateForm.author.value.length > 15){
			alert("[저자] 15자를 초과했습니다.")
		}
		else if(updateForm.publisher.value == ""){
			alert("출판사를 입력하세요.")
		}
		else if(updateForm.publisher.value.length > 15){
			alert("[출판사] 15자를 초과했습니다.")
		}
		else if(updateForm.publicationDate.value.length > 20){
			alert("[출판날짜] 20자를 초과했습니다.")
		}
		else if(pubD.charAt(4) != "-" || pubD.charAt(7) != "-"){
			alert("[출판날짜] 형식이 다릅니다. ex) 2023-01-10")
		}
		else if(updateForm.bookInfo.value == ""){
			alert("책 소개를 입력하세요.")
		}
		else if(updateForm.bookInfo.value.length > 1999){
			alert("[책 소개] 1999자를 초과했습니다.")
		}
		else if(updateForm.bookIndex.value.length > 1999){
			alert("[목차] 1999자를 초과했습니다.")
		}
		else if(updateForm.authorInfo.value.length > 1999){
			alert("[저자 소개] 1999자를 초과했습니다.")
		}
		else if(confirm("정말로 수정하시겠습니까?")) {
			updateForm.submit();
		}
	}

	function bookDelete(){
		if (confirm("정말로 삭제하시겠습니까?")== true){
			deleteForm.submit();
		}
	}
</script>

</head>
<body>
<%
String[] level = { "초보자", "중급자", "상급자" };

String[] lan = { "java", "python", "c", "c++", "kotlin", "lua", "css", "javaScript", "swift", "c#", "node.js",
		"typeScript", "기타", };

String[] keywords = {"X", "게임", "도커", "라즈베리파이", "리눅스/유닉스", "비전공자" , "빅데이터", "서버", "솔루션" , "스레드", "알고리즘", 
		"오라클", "인공지능", "임베디드", "자격증", "자료구조", "자바 입문", "증강현실", "쿠버네티스", "클라우드 서비스", 
		"하드웨어", "학습 사이트 제공"};

%>
	<header>
		<div>
			<jsp:include page="../../top/userMenubar.jsp" />
		</div>
		<div>
			<jsp:include page="../../top/logo.jsp" />
		</div>
	</header>
	<jsp:include page="../../top/menubar.jsp">
		<jsp:param name="selected" value="search" />
	</jsp:include>
	<br>

	<center>
		<h3>&lt; 도서 정보 수정 &gt;</h3>
		<form name="updateForm" method="POST"
			action="<c:url value='/admin/book/modify' />">
			<table>
				<tr>
					<th>표지(경로)</th>
					<td>&nbsp;<input type="text" class="smallText" name="cover_path"
						value="${book.cover_path}" placeholder="https://image.aladin.co.kr/product/9405/99/cover500/8968487316_1.jpg"></td>
				</tr>
				<tr>
					<th>ISBN *</th>
					<td>&nbsp;<input type="text" class="smallText" name="ISBN" value="${book.ISBN}"></td>
				</tr>
				<tr>
					<th>제목 *</th>
					<td>&nbsp;<input type="text" class="smallText" name="title"
						value="${book.title}" placeholder="최대 40자"></td>
				</tr>
				<tr>
					<th>저자 *</th>
					<td>&nbsp;<input type="text" class="smallText" name="author"
						value="${book.author}" placeholder="최대 15자"></td>
				</tr>
				<tr>
					<th>출판사 *</th>
					<td>&nbsp;<input type="text" class="smallText" name="publisher"
						value="${book.publisher}" placeholder="최대 15자"></td>
				</tr>
				<tr>
					<th>출판 날짜 *</th>
					<td>&nbsp;<input type="text" class="smallText" name="publicationDate"
						value="${book.publicationDate }" placeholder="ex)2023-01-10">
					</td>
				</tr>
				<tr>
				<tr id="longInfo">
					<th>책 소개 *</th>
					<td><textarea id="longinput type=" text"" name="bookInfo" placeholder="최대 1999자"> ${book.bookInfo} </textarea></td>
				</tr>
				<tr id="longInfo">
					<th>목차</th>
					<td><textarea id="longinput type=" text"" name="bookIndex" placeholder="최대 1999자"> ${book.bookIndex} </textarea></td>
				</tr>
				<tr id="longInfo">
					<th>저자 소개</th>
					<td><textarea id="longinput type=" text"" name="authorInfo" placeholder="최대 1999자"> ${book.authorInfo} </textarea></td>
				</tr>
			</table>
			<br><br>

			<table>
				<tr class="bookType_cate">
					<th>카테고리 *</th>
					<td>
						&nbsp
						<select name="category">
							<c:forEach var="c" items="${categories}">
								<c:choose>
									<c:when test="${c.category eq book.category}">
										<option value="${c.category}" selected>${c.category}
									</c:when>
									<c:otherwise>
										<option value="${c.category}">${c.category}
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select> 
					</td>
				</tr>
				<tr class="bookType">
					<th>수준 *</th>
					<td>
					<% for (int i = 0; i < level.length; i++) { %>
						<c:set var="levelT" value="<%=level[i]%>"/> 
						<c:choose>
							<c:when test="${levelT eq book.itLevel}">
								&nbsp;<input type="radio" name="itLevel" value="${levelT }" checked>${levelT}
							</c:when>
							<c:otherwise>
								&nbsp;<input type="radio" name="itLevel" value="${levelT }">${levelT }
							</c:otherwise>
						</c:choose>
					<%} %>
					</td>
				</tr>
				<tr class="bookType">
					<th>언어 *</th>
					<td>
						<br>
						<% for (int i = 0; i < lan.length; i++) { %>
						<c:set var="lanT" value="<%=lan[i]%>"/> 
						<c:choose>
							<c:when test="${lanT eq book.language}">
								&nbsp;<input type="radio" name="language" value="${lanT }" checked>${lanT}&emsp;&emsp;
							</c:when>
							<c:otherwise>
								&nbsp;<input type="radio" name="language" value="${lanT }">${lanT }&emsp;&emsp;
							</c:otherwise>
						</c:choose>
						
						<% if(i!=0 && i%5==0) { %>
								<br>
						<%} %>
					<%} %>
					<br><br>				
					</td>
				</tr>
				<tr class="bookType">
					<th>키워드 <br> (최대 3개)
					</th>
					<td> &nbsp; 
						1. <select name="keyword1">
								<% for (int i = 0; i < keywords.length; i++) { %>
								<c:set var="keyT" value="<%=keywords[i]%>"/> 
								<c:choose>
									<c:when test="${keyT eq book.keyword1}">
										<option value="${ keyT }" selected>${ keyT }
									</c:when>
									<c:otherwise>
										<option value="${ keyT }">${ keyT }
									</c:otherwise>
								</c:choose>
							<%} %>
						</select> 
						&nbsp;&nbsp;
						
						2. <select name="keyword2">
								<% for (int i = 0; i < keywords.length; i++) { %>
								<c:set var="keyT" value="<%=keywords[i]%>"/> 
								<c:choose>
									<c:when test="${keyT eq book.keyword2}">
										<option value="${ keyT }" selected>${ keyT }
									</c:when>
									<c:otherwise>
										<option value="${ keyT }">${ keyT }
									</c:otherwise>
								</c:choose>
							<%} %>
						</select> 
						&nbsp;&nbsp;
						
						3. <select name="keyword3">
								<% for (int i = 0; i < keywords.length; i++) { %>
								<c:set var="keyT" value="<%=keywords[i]%>"/> 
								<c:choose>
									<c:when test="${keyT eq book.keyword3}">
										<option value="${ keyT }" selected>${ keyT }
									</c:when>
									<c:otherwise>
										<option value="${ keyT }">${ keyT }
									</c:otherwise>
								</c:choose>
							<%} %>
						</select> 
					</td>
				</tr>
			</table>

			<p/>
			<button type="button" id="btn_modify" onClick="bookModify()">수정</button>
		</form>

		<form name="deleteForm" method="GET"
			action="<c:url value='/admin/book/delete' />">
			<input type="hidden" name="ISBN" value="${book.ISBN}">
			<button type="button" id="btn_delete" onClick="bookDelete()">삭제</button>
			</a>
		</form>
	</center>

</body>
</html>