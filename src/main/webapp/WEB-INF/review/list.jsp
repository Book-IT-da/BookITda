<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel=stylesheet href="<c:url value='/css/userMenu.css' />" type="text/css">
		<link rel=stylesheet href="<c:url value='/css/logo.css' />" type="text/css">
		<link rel=stylesheet href="<c:url value='/css/menu.css' />" type="text/css">
		<title>도서 리뷰</title>
		<style>     
            li a {
                color: black;
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
            .book_box td a{
    			text-decoration: none;
    			color: black;
            }
            .book_box th{
                border-color:black;
                border-style:solid;
                border-width:1px;
                padding:10px 5px;
            }
            .book_pic{
                width: 20%;
            }
            .book_cover {
				width: 200px;
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
            .review_date{
                font-size: 12px;
            	
            }
        </style>
	<script type="text/javascript">
		function searchReview() {
			if (searchReviewForm.reviewTitle_input.value == "") {
				alert("책 제목을 입력하세요");
				searchReviewForm.reviewTitle_input.focus();
				return false;
			}
			searchReviewForm.submit();
		}

		function createReview() {
			if (createReviewForm.userId.value == "") {
				alert("로그인해야 이용가능한 서비스입니다.");
				return false;
			}
			createReviewForm.submit();
		}
		
		function loginAlert(){
			alert("로그인해야 이용가능한 서비스입니다.");
		}
	</script>
</head>


<body>
	<header>
		<div>
			<jsp:include page="../top/userMenubar.jsp" />
		</div>
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
                <input type="button" id="search_button" onClick="searchReview()" value="검색하기">
            </form>
                <hr/>
            </div>
            <div align="right">
				<form name="createReviewForm" method="GET"
					action="<c:url value='/review/create' />">
					<input type="hidden" name="userId" value="${userId}">
                	<input type="button" onClick="createReview()" value="작성하기">
            	</form>
            </div>
            <div>                
            	<c:forEach var="review" items="${reviewList}"> 
	                <div>
	                    <table class="book_box">
	                        <thead>
	                        <tr>
	                            <td class="book_pic" rowspan="5"><%-- <img class="book_cover" src="<c:url value='/images/bookCover/${review.cover_path}' />" /> --%></td>
	                            <td class="review_date">${review.createDate }</td>
	                        </tr>
	                        <tr>
	                        	<td class="book_title">
		                        	<c:choose>
										<c:when  test="${sessionScope.userId != null}">
				                            <a href="<c:url value='/review/view'>
											   <c:param name='reviewId' value='${review.reviewId}'/>
									 		 </c:url>">
			                            	${review.title} </a>
		                            	</c:when>
		                            	<c:otherwise>
		                            		<a href="javascript:void(0);" onclick="loginAlert()">
				                            ${review.title} </a>
		                            	</c:otherwise>
	                            	</c:choose>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td class="book_author">${review.nickname}</td>
	                        </tr>
	                        <tr>
	                            <td class="book_stars">
			                        <c:choose> 
										<c:when test="${review.stars eq 0}">☆☆☆☆☆</c:when> 
										<c:when test="${review.stars eq 1}">★☆☆☆☆</c:when> 
										<c:when test="${review.stars eq 2}">★★☆☆☆</c:when> 
										<c:when test="${review.stars eq 3}">★★★☆☆</c:when> 
										<c:when test="${review.stars eq 4}">★★★★☆</c:when> 
										<c:otherwise>
										★★★★★
										</c:otherwise> 
									</c:choose> 
								</td>
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