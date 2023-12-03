<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	Review review = (Review)request.getAttribute("review");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel=stylesheet href="<c:url value='/css/userMenu.css' />" type="text/css">
		<link rel=stylesheet href="<c:url value='/css/logo.css' />" type="text/css">
		<link rel=stylesheet href="<c:url value='/css/menu.css' />" type="text/css">
		<title>리뷰 수정</title>
		<style>     
            li a {
                color: black;
            } 
            #comment_input {
                font-size: 16px;
                width: 325px;
                padding: 10px;
            }
            .book_box  {
                border-collapse:collapse;
                border-spacing:0;
                margin: auto;
                width: 50%;
                height: 80%;
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
            .input_text {
                width: 80%;
                line-height: 20px;
            }
            .book_box th{
                border-color:black;
                border-style:solid;
                border-width:1px;
                padding:10px 5px;
            }
            .book_multiline_review{
                height: 200px;
                width: 99%;
            }
        </style>
        <script>
			function reviewModify() {
				if (form.title.value == "") {
					alert("제목을 입력하세요.");
					form.title.focus();
					return false;
				}
				if (form.stars.value == "") {
					alert("별점을 선택하세요.");
					form.stars.focus();
					return false;
				}
				if (form.level.value == "") {
					alert("난이도를 선택하세요.");
					form.level.focus();
					return false;
				}
				if (form.purpose.value == "") {
					alert("독서 목적을 입력하세요.");
					form.purpose.focus();
					return false;
				}
				if (form.recommObj.value == "") {
					alert("추천 대상을 입력하세요.");
					form.recommObj.focus();
					return false;
				}
				if (form.keyword.value == "") {
					alert("키워드를 입력하세요.");
					form.keyword.focus();
					return false;
				}
				if (form.onelineReview.value == "") {
					alert("한줄평을 입력하세요.");
					form.onelineReview.focus();
					return false;
				}
				if (form.multilineReview.value == "") {
					alert("상세리뷰를 입력하세요.");
					form.multilineReview.focus();
					return false;
				}
				form.submit();
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
            <div>
                <form name="form" method="post" action="<c:url value='/review/update' />">
                    <div align="right">
                        <input type="button" value="올리기"  onClick="reviewModify()">
                    </div>
                   	 <input type="hidden" name="createDate" value="<%=review.getCreateDate()%>"/>
                   	 <input type="hidden" name="nickname" value="<%=review.getNickname()%>"/>	  
                    <table class="book_box">
                        <tbody>
                            <tr>
                                <td>제목</td>
                                <td>
                                	<%=review.getTitle()%>
                    				<input type="hidden" name="reviewId" value="<%=review.getReviewId()%>"/>
                   	 				<input type="hidden" name="title" value="<%=review.getTitle()%>"/>
                   	 				<input type="hidden" name="author" value="<%=review.getAuthor()%>"/>
                   	 				<input type="hidden" name="publisher" value="<%=review.getPublisher()%>"/>
                                    <%-- <input class="input_text" type="text" value="<%=review.getTitle()%>" placeholder="제목을 검색하세요">
                                    <button>검색하기</button> --%>
                                </td>
                            </tr>
                            <tr>
                                <td>별점</td>
                                <td>
                                    <input type="radio" name="stars" value="0" <% if (review.getStars() == 0) { %> checked <% } %>>☆☆☆☆☆
                                    <input type="radio" name="stars" value="1" <% if (review.getStars() == 1) { %> checked <% } %>>★☆☆☆☆
                                    <input type="radio" name="stars" value="2" <% if (review.getStars() == 2) { %> checked <% } %>>★★☆☆☆
                                    <input type="radio" name="stars" value="3" <% if (review.getStars() == 3) { %> checked <% } %>>★★★☆☆
                                    <input type="radio" name="stars" value="4" <% if (review.getStars() == 4) { %> checked <% } %>>★★★★☆
                                    <input type="radio" name="stars" value="5" <% if (review.getStars() == 5) { %> checked <% } %>>★★★★★
                                </td>
                            </tr>
                            <tr>
                                <td>난이도</td>
                                <td>
                                    <input type="radio" name="level" value="1" <% if (Integer.parseInt(review.getLevel()) == 1) { %> checked <% } %>>초급
                                    <input type="radio" name="level" value="2" <% if (Integer.parseInt(review.getLevel()) == 2) { %> checked <% } %>>중급
                                    <input type="radio" name="level" value="3" <% if (Integer.parseInt(review.getLevel()) == 3) { %> checked <% } %>>고급
                                </td>
                            </tr>
                            <tr>
                                <td>독서 목적</td>
                                <td>
                                    <input class="input_text" name="purpose" type="text" value="<%=review.getPurpose()%>" placeholder="독서 목적을 작성해주세요">
                                </td>
                            </tr>
                            <tr>
                                <td>추천 대상</td>
                                <td>
                                    <input class="input_text" name="recommObj" type="text" value="<%=review.getRecommObj()%>" placeholder="추천 대상을 작성해주세요">
                                </td>
                            </tr>
                            <tr>
                                <td>키워드</td>
                                <td>
                                    <input class="input_text" name="keyword" type="text" value="<%=review.getKeyword()%>" placeholder="키워드를 작성해주세요">
                                </td>
                            </tr>
                            <tr>
                                <td>한줄평</td>
                                <td>
                                    <input class="input_text" name="onelineReview" type="text" value="<%=review.getOnelineReview()%>" placeholder="한줄평을 작성해주세요">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">상세리뷰</td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <textarea class="book_multiline_review" name="multilineReview" type="text" placeholder="상세리뷰를 작성해주세요"><%=review.getMultilineReview()%></textarea>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </main>
</body>
</html>