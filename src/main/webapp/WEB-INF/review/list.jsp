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
            #search_input {
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
			<input id="search_input" type="text" placeholder="검색어 입력">
                <button id="search_button">검색하기</button>
                <hr/>
            </div>
            <div align="right">
                <button onclick="location.href='./bookReviewDetail.html'">작성하기</button>
            </div>
            <div>
                <div>
                    <table class="book_box">
                        <thead>
                        <tr>
                            <td class="book_pic" rowspan="4"> 사진 </td>
                            <td class="book_title">제목</td>
                        </tr>
                        <tr>
                            <td class="book_author">저자</td>
                        </tr>
                        <tr>
                            <td class="book_stars">별점</td>
                        </tr>
                        <tr>
                            <td class="book_oneline_review">한줄평</td>
                        </tr>
                        </thead>
                    </table>
                </div>
                <table class="book_box">
                    <thead>
                    <tr>
                        <td class="book_pic" rowspan="4"> 사진 </td>
                        <td class="book_title">제목</td>
                    </tr>
                    <tr>
                        <td class="book_author">저자</td>
                    </tr>
                    <tr>
                        <td class="book_stars">별점</td>
                    </tr>
                    <tr>
                        <td class="book_oneline_review">한줄평</td>
                    </tr>
                    </thead>
                </table>
            </div>
            <div align="center">
                <hr/>
                <button> &lt; </button>
                <a>1</a>
                <a>2</a>
                <a>3</a>
                <button> &gt; </button>
            </div>
        </main>
</body>
</html>