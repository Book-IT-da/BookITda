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
		li a {
		    color: black;
		}  
	   	   .bigContainer {
			    width: 800px; 
			    height: 650px; 
			    position: absolute;
			    top: 80%;
			    left: 50%;
			    transform: translate(-50%, -50%);
			    background-color: #f7e6ff;
			    border-radius: 10%;
			    overflow: hidden; 
			    transition: width 0.5s, height 0.5s; 
			}
			
/* 			.bigContainer:hover {
			    width: 800px; 
			    height: 650px; 
			} */
			
			.question {
			    width: 500px;
			    position: absolute;
			    top: 5%;
			    left: 18%;
			    text-align: center;
			}
	        .container {
	            width: 600px;
	            height: 50%;
	            position: absolute; 
			    top: 30%; 
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
                height: 80%;
                margin-top: 30px;
                margin-bottom: 30px;
            }
            .book_box td{
                border-color:#f7e6ff;
                border-style:solid;
                border-width:1px;
                padding:10px 5px;
                word-break:normal;
            }
            .book {
	        	width: 130px;
	            padding: 10px;
	            margin: 10px;
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
	            margin: 0 0; /* 간격을 줄임 */
	        }
	        .grade {
	        	font-size: 7px;
	        	margin: 0 0; /* 간격을 줄임 */
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
	<jsp:include page="../top/menubar.jsp">
    	<jsp:param name="selected" value="recomm" />
	</jsp:include>
    <main>
        <div align="right">
	        <form action="${contextPath}/get/categoryList" method="POST">
			    <button type="submit">관심사 재설정</button>
			</form>
           <%-- <a href="${contextPath}/get/categoryList"><button>관심사 재설정</button></a> --%>
        </div>
        <div class = "bigContainer">
			<h2 class="question">${userId}님께 어울리는 책을 소개할게요</h2><br>
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
    </main>
</body>

</html>