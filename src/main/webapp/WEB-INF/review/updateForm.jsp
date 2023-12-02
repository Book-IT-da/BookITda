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
		<meta charset="UTF-8">
		<link rel=stylesheet href="<c:url value='/css/logo.css' />" type="text/css">
		<link rel=stylesheet href="<c:url value='/css/menu.css' />" type="text/css">
		<title>리뷰 수정</title>
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
            <div>
                <form name="reviewUpdateForm" method="post" action="<c:url value='/review/update' />">
                    <div align="right">
                        <input type="submit" value="올리기">
                    </div>
                    <input type="hidden" name="reviewId" value="<%=review.getReviewId()%>"/>	  
                    <table class="book_box">
                        <tbody>
                            <tr>
                                <td>제목</td>
                                <td>
                                    <input class="input_text" type="text" value="<%=review.getTitle()%>" placeholder="제목을 검색하세요">
                                    <button>검색하기</button>
                                </td>
                            </tr>
                            <tr>
                                <td>별점</td>
                                <td>☆☆☆☆☆</td>
                            </tr>
                            <tr>
                                <td>난이도</td>
                                <td>
                                    <input type="radio" name="book_level">초급
                                    <input type="radio" name="book_level">중급
                                    <input type="radio" name="book_level">고급
                                </td>
                            </tr>
                            <tr>
                                <td>독서 목적</td>
                                <td>
                                    <input class="input_text" type="text" value="<%=review.getPurpose()%>" placeholder="독서 목적을 작성해주세요">
                                </td>
                            </tr>
                            <tr>
                                <td>추천 대상</td>
                                <td>
                                    <input class="input_text" type="text" value="<%=review.getRecommObj()%>" placeholder="추천 대상을 작성해주세요">
                                </td>
                            </tr>
                            <tr>
                                <td>키워드</td>
                                <td>
                                    <input class="input_text" type="text" value="<%=review.getKeyword()%>" placeholder="키워드를 작성해주세요">
                                </td>
                            </tr>
                            <tr>
                                <td>한줄평</td>
                                <td>
                                    <input class="input_text" type="text" value="<%=review.getOnelineReview()%>" placeholder="한줄평을 작성해주세요">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">상세리뷰</td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <textarea class="book_multiline_review" type="text" placeholder="상세리뷰를 작성해주세요"><%=review.getMultilineReview()%></textarea>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </main>
</body>
</html>