<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel=stylesheet href="<c:url value='/css/logo.css' />" type="text/css">
		<link rel=stylesheet href="<c:url value='/css/menu.css' />" type="text/css">
		<title>도서 리뷰</title>
		<style>     
            li a {
                color: black;
            } 
            #top {
                margin: 10px 0 15px 30px;
            }        
            #logo {
                margin: 25px 30px;
                text-align: center;
            }
            #reviewTitle_input {
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
            .book_box  {
                border-collapse:collapse;
                border-spacing:0;
                margin: auto;
                width: 60%;
                height: 225px;
                margin-top: 30px;
                margin-bottom: 30px;
            }
            .book_box td{
                border-color:black;
                border-style:solid;
                border-width:1px;
                padding:10px 5px;
                word-break:normal;
            }
            .book_box th{
                border-color:black;
                border-style:solid;
                border-width:1px;
                padding:10px 5px;
            }
            .tg .tg-0pky{
                border-color:inherit;
                text-align:left;
                vertical-align:top
            }
            .book_pic{
                width: 20%;
            }
            .book_title{
                font-size: 18px;
                font-weight: bold;
            }
            .book_author{
                font-size: 16px;
            }
            .book_stars{
                font-size: 16px;
            }
            .book_oneline_review{
                font-size: 16px;
            }
        </style>
	<script type="text/javascript">
		function search() {
			if (searchReviewForm.reviewTitle_input.value == "") {
				alert("책 제목을 입력하세요");
				searchReviewForm.reviewTitle_input.focus();
				return false;
			}
			searchReviewForm.submit();
		}
	</script>
</head>


<body>
	<header>
		<div>
			<jsp:include page="../top/logo.jsp" /> 
		</div>
	</header>
	<jsp:include page="../top/menubar.jsp">
    	<jsp:param name="selected" value="review" />
	</jsp:include>
	<main>
		<div align="center">
			<form name="searchReviewForm" method="GET"
				action="<c:url value='/review/list' />">
				<input id="reviewTitle_input" name="reviewTitle_input" type="text" placeholder="책 제목을 입력하세요">
                <input type="button" id="search_button" onClick="search()" value="검색하기">
            </form>
                <hr/>
            </div>
            <div align="right">
                <button onclick="location.href='./bookReviewDetail.html'">작성하기</button>
            </div>
            <div>                
            	<c:forEach var="review" items="${reviewList}"> 
	                <div>
	                    <table class="book_box">
	                        <thead>
	                        <tr>
	                            <td class="book_pic" rowspan="4"> 사진 </td>
	                            <td class="book_title">${review.title}</td>
	                        </tr>
	                        <tr>
	                            <td class="book_author">${review.nickname}</td>
	                        </tr>
	                        <tr>
	                            <td class="book_stars">${review.stars}</td>
	                        </tr>
	                        <tr>
	                            <td class="book_oneline_review">${review.onelineReview}</td>
	                        </tr>
	                        </thead>
	                    </table>
	                </div>
	  			</c:forEach>
            </div>
<!--             <div align="center">
                <hr/>
                <button> &lt; </button>
                <a>1</a>
                <a>2</a>
                <a>3</a>
                <button> &gt; </button>
            </div> -->
        </main>
</body>
</html>