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
		<link rel=stylesheet href="<c:url value='/css/logo.css' />" type="text/css">
		<link rel=stylesheet href="<c:url value='/css/menu.css' />" type="text/css">
		<title>상세보기</title>
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
            .book_box th{
                border-color:black;
                border-style:solid;
                border-width:1px;
                padding:10px 5px;
            }
            .book_pic{
                width: 20%;
            }
            .book_multiline_review{
                height: 100px;
            }
            .comment_box  {
                border-collapse:collapse;
                border-spacing:0;
                margin: auto;
                width: 50%;
                height: 80%;
                margin-top: 30px;
                margin-bottom: 30px;
            }
            .comment_box td{
                border-color:black;
                border-style:solid;
                border-width:1px;
                padding:10px 5px;
            }
            .comment_box th{
                border-color:black;
                border-style:solid;
                border-width:1px;
                padding:10px 5px;
            }
            .comment_detail{
                height: 100px;
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
            <div align="right">
			<form name="updateReviewForm" method="GET"
				action="<c:url value='/review/update' />">
    			<input type="hidden" name="reviewId" value="<%=review.getReviewId()%>">
    			<button type="submit">수정하기</button>
            </form>
                <button>삭제하기</button>
            </div>
            <div>
                <table class="book_box">
                    <tbody>
                      <tr>
                        <td>작정자</td>
                        <td colspan="4"><%=review.getNickname()%></td>
                      </tr>
                      <tr>
                        <td colspan="5"><%=review.getTitle()%></td>
                      </tr>
                      <tr>
                        <td class="book_pic" rowspan="6">사진</td>
                        <td>저자</td>
                        <td colspan="3"><%=review.getAuthor()%></td>
                      </tr>
                      <tr>
                        <td>출판사</td>
                        <td colspan="3"><%=review.getPublisher()%></td>
                      </tr>
                      <tr>
                        <td>별점</td>
                        <td><%=review.getStars()%></td>
                        <td>난이도</td>
                        <td><%=review.getLevel()%></td>
                      </tr>
                      <tr>
                        <td>독서 목적</td>
                        <td><%=review.getPurpose()%></td>
                        <td>추천 대상</td>
                        <td><%=review.getRecommObj()%></td>
                      </tr>
                      <tr>
                        <td>키워드</td>
                        <td colspan="3"><%=review.getKeyword()%></td>
                      </tr>
                      <tr>
                        <td>한줄평</td>
                        <td colspan="3"><%=review.getOnelineReview()%></td>
                      </tr>
                      <tr>
                        <td colspan="5"></td>
                      </tr>
                      <tr>
                        <td colspan="5">상세 리뷰</td>
                      </tr>
                      <tr>
                        <td class="book_multiline_review" colspan="5"><%=review.getMultilineReview()%></td>
                      </tr>
                    </tbody>
                </table>
            </div>
            <div align="center">
                <input id="comment_input" type="text" placeholder="댓글을 입력하세요">
                <button>댓글달기</button>
                <div>
                    <table class="comment_box">
                        <thead>
                          <tr>
                            <td class="tg-0pky">닉네임</td>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            <td class="comment_detail">댓글 내용</td>
                          </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
</body>
</html>