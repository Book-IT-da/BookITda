<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">	
<title>책 제목 검색</title>
	<style>
	#logo {
		margin: 25px 30px;
		text-align: center;
		}
	
	#logo a {
	    text-decoration: none;
	    color: black;
	}  
	#searchText {
    	font-size: 16px;
        width: 325px;
        padding: 10px;
        border: 0.5px;
    }
    #search_button {
        width: 100px;
        height: 35px;
        background: #1b5ac2;
        outline: none;
        color: #ffffff;
    }
    table {
        width: 800px;
    }
    th, td {
        height: 50px;
        text-align: center;
    }
    th {
        border-bottom: 1.5px solid black;
    }
    a {
        text-decoration-line: none;
         color: black;
    }
	</style>
	<script type="text/javascript">
		function searchBook() {
			if (searchBookForm.searchText.value == "") {
				alert("책 제목을 입력하세요");
				searchBookForm.searchText.focus();
				return false;
			}
			searchBookForm.submit();
		}
		function closeChild(isbn, title) {
			opener.document.getElementById("ISBN").value = isbn;
			opener.document.getElementById("title").value = title;
			window.close();
		}
	</script>
</head>
<body>
	<header>
		<div>
			<jsp:include page="../top/logo.jsp" /> 
		</div>
	</header>
	<main>
		<div align="center">
			<form name="searchBookForm" method="GET"
				action="<c:url value='/review/book/search' />">
				<input id="searchText" name="searchText" type="text" placeholder="책 제목을 입력하세요">
                <input type="button" id="search_button" onClick="searchBook()" value="검색하기">
            </form>
            <hr/>
        </div>
        <div align="center">
	        <table>
	            <tr>
	                <th>제목</th>
	                <th>ISBN</th>
	                <th>저자</th>
	                <th>출판사</th>
	            </tr>
	            <c:forEach var="book" items="${bookList}"> 
		            <tr>
		                <td><a href="#" onclick="closeChild('${book.ISBN}', '${book.title}')">${book.title}</a></td>
		                <td>${book.ISBN}</td>
		                <td>${book.author}</td>
		                <td>${book.publisher}</td>
		            </tr>
	  			</c:forEach>
	        </table>
        </div>
	</main>
</body>
</html>