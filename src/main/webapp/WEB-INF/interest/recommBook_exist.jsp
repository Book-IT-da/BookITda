<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="model.RecommBook" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="userId" value="${userId}" />
<%
	String userId = (String)request.getSession().getAttribute("userId");
	List<RecommBook> recommBookList = (List<RecommBook>) request.getSession().getAttribute("recommBookList");
	request.setAttribute("recommBookList", recommBookList);
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel=stylesheet href="<c:url value='/css/userMenu.css' />" type="text/css">
    <link rel=stylesheet href="<c:url value='/css/logo.css' />" type="text/css">
	<link rel=stylesheet href="<c:url value='/css/menu.css' />" type="text/css">
    <title>추천 도서 보기</title>
    <style>  
	    html, body {
	    	position: relative;
		    height: 100%;
		    margin: 0;
		    padding: 0;
		}
		li a {
		    color: black;
		}  
	   	   .bigContainer {
			    width: 800px; 
			    height: 800px; 
			    position: relative;
			    top: 5%;
			    left: 25%;
			    background-color: #f7e6ff;
			    border-radius: 10%;
			    flex-direction: column; 
   			 	align-items: center; 
			}
			.question {
			    width: 500px;
			    position: absolute;
			    top: 5%;
			    left: 18%;
			    text-align: center;
			}
		    .question-info {
		    	position: absolute;
			    top: 12.5%;
			    left: 25%;
			    text-align: center;
			    color: gray;
			    font-size: 10px;
		    }
	        .container {
	            width: 600px;
	            height: auto;
	            position: absolute; 
			    top: 15%; 
			    left: 13%; 
	            display: flex;
	            justify-content: center;
	            align-items: center; 
	        }  
            .book_box  {
                border-collapse:collapse;
                border-spacing:0;
                margin: auto;
                width: 50%;
                height: 60%;
                margin-top: 10px;
                margin-bottom: 10px;
            }
            .book_box td{
                border-color:#f7e6ff;
                border-style:solid;
                border-width:1px;
                padding:5px 5px;
                word-break:normal;
            }
            .book {
	        	width: 130px;
	            padding: 5px;
	            margin: 5px;
	            align-items: center;
	        }
	        .bookImg {
	        	width: 120px;
	            height: 150px;
	        }
	        .title {
	            font-weight: bold;
	            font-size: 17px;
	            margin: 5px 0 0 0;
	        }
	        .author {
	            font-style: italic;
	            font-size: 13px;
	            margin: 0 0; 
	        }
	        .grade {
	        	font-size: 7px;
	        	margin: 0 0; 
	        }
	        .menubar {
	        	position: relative;
			    top: 0%;
			    left: 0.5%;
			    z-index: 1;
			}
        </style>
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
		<div class="menubar">
			<jsp:include page="../top/menubar.jsp">
		    	<jsp:param name="selected" value="recomm" />
			</jsp:include>
		</div>
        <div align="right">
	        <form action="${contextPath}/get/categoryList" method="POST">
			    <button type="submit">관심사 재설정</button>
			</form>
           <%-- <a href="${contextPath}/get/categoryList"><button>관심사 재설정</button></a> --%>
        </div>
        <div class = "bigContainer" align="center">
			<h2 class="question">${userId}님께 어울리는 책을 추천할게요</h2><br>
	        <div class="question-info">
	            사용자의 수준에 일치하는 책 중 관심 분야 및 수준에 더 일치하는 순서로 제시하였습니다.
	        </div>
			 <div class="container">
	            <table class="book_box">
	                <c:forEach var="recommBook" items="${recommBookList}" varStatus="loop">
	                   <c:if test="${loop.index % 3 == 0}">
				            <tr>
				        </c:if>
				        <td>
				        <c:url var="bookInfoURL" value="../book/bookInfo">
						   <c:param name="ISBN" value="${recommBook.isbn}"/>
						</c:url>
						<a href="${bookInfoURL}">
				            <div class="book">
				                <img class="bookImg" src="<c:url value='/images/bookCover/${recommBook.cover_path}' />" > 
				                <h2 class="title">${recommBook.title}</h2>
				                <p class="author">${recommBook.author}</p>
				                <p class="avgStar">★${recommBook.avgstar}</p>
				            </div>
				           </a>
				        </td>
				        <c:if test="${loop.index % 3 == 2 or loop.last}">
				            </tr>
				        </c:if>
	                </c:forEach>
	            </table>
	        </div>
	     </div>
</body>

</html>