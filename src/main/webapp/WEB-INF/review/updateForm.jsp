<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
		<link rel=stylesheet href="<c:url value='/css/review/updateForm.css' />" type="text/css">
		<title>리뷰 수정</title>
        <script>
	    	function findBook() {
	    		window.name = "parentForm";
	            
	            openWin = window.open("${contextPath}/review/book/search", "childForm", "width=800, height=500, resizable = no, scrollbars = no");  
	    	}
        
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
                        <input type="button" id="buttonSt" value="올리기"  onClick="reviewModify()">
                    </div>
                   	 <input type="hidden" name="createDate" value="<%=review.getCreateDate()%>"/>
                   	 <input type="hidden" name="nickname" value="<%=review.getNickname()%>"/>	  
                    <table class="book_box">
                        <tbody>
                            <tr>
                                <td id="info">제목</td>
                                <td>
                    				<input type="hidden" name="reviewId" value="<%=review.getReviewId()%>"/>
                   					<input type="hidden" id="ISBN" name="ISBN"  value="<%=review.getISBN()%>"/>
                                    <input class="input_text" id="title" name="title" type="text" value="<%=review.getTitle()%>" placeholder="제목을 검색하세요" disabled>
                        			<input type="button" value="검색하기" onClick="findBook()">
                                </td>
                            </tr>
                            <tr>
                                <td id="info">별점</td>
                                <td>
                                    <input type="radio" name="stars" value="1" <% if (review.getStars() == 1) { %> checked <% } %>>★☆☆☆☆
                                    <input type="radio" name="stars" value="2" <% if (review.getStars() == 2) { %> checked <% } %>>★★☆☆☆
                                    <input type="radio" name="stars" value="3" <% if (review.getStars() == 3) { %> checked <% } %>>★★★☆☆
                                    <input type="radio" name="stars" value="4" <% if (review.getStars() == 4) { %> checked <% } %>>★★★★☆
                                    <input type="radio" name="stars" value="5" <% if (review.getStars() == 5) { %> checked <% } %>>★★★★★
                                </td>
                            </tr>
                            <tr>
                                <td id="info">난이도</td>
                                <td>
                                    <input type="radio" name="level" value="1" <% if (Integer.parseInt(review.getLevel()) == 1) { %> checked <% } %>>초급
                                    <input type="radio" name="level" value="2" <% if (Integer.parseInt(review.getLevel()) == 2) { %> checked <% } %>>중급
                                    <input type="radio" name="level" value="3" <% if (Integer.parseInt(review.getLevel()) == 3) { %> checked <% } %>>고급
                                </td>
                            </tr>
                            <tr>
                                <td id="info">독서 목적</td>
                                <td>
                                    <input class="input_text" name="purpose" type="text" value="<%=review.getPurpose()%>" placeholder="독서 목적을 작성해주세요">
                                </td>
                            </tr>
                            <tr>
                                <td id="info">추천 대상</td>
                                <td>
                                    <input class="input_text" name="recommObj" type="text" value="<%=review.getRecommObj()%>" placeholder="추천 대상을 작성해주세요">
                                </td>
                            </tr>
                            <tr>
                                <td id="info">키워드</td>
                                <td>
                                    <input class="input_text" name="keyword" type="text" value="<%=review.getKeyword()%>" placeholder="키워드를 작성해주세요">
                                </td>
                            </tr>
                            <tr>
                                <td id="info">한줄평</td>
                                <td>
                                    <input class="input_text" name="onelineReview" type="text" value="<%=review.getOnelineReview()%>" placeholder="한줄평을 작성해주세요">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" id="info">상세리뷰</td>
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