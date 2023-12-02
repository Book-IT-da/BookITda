<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.RecommBook" %>

<%
	List<RecommBook> recommBookList = (List<RecommBook>) request.getSession().getAttribute("recommBookList");
	request.setAttribute("recommBookList", recommBookList);
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>추천 도서 보기</title>
    <style>     
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
    <main>
        <div align="right">
            <a href="./interest_updateForm.html"><button>관심사 재설정</button></a>
        </div>
        <div>
            <table class="book_box">
                <c:forEach var="recommBook" items="${recommBookList}">
                    <tr>
                        <td>
                            <div class="book">
                                <%-- <img class="bookImg" src="${recommBook.bookImage}" alt="도서 표지 이미지"> --%>
                                <h2 class="title">${recommBook.title}</h2>
                                <p class="author">${recommBook.author}</p>
                                <p class="avgStar">${recommBook.avgstar}</p>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </main>
</body>

</html>