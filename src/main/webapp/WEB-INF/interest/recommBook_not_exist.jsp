<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
	<head>
		<link rel=stylesheet href="<c:url value='/css/userMenu.css' />" type="text/css">
    	<link rel=stylesheet href="<c:url value='/css/logo.css' />" type="text/css">
		<link rel=stylesheet href="<c:url value='/css/menu.css' />" type="text/css">  
        <meta charset="UTF-8">
        <title>추천 도서 보기</title>
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
                margin-top: 200px;
                margin-bottom: 30px;
            }
	        .ment {
	            font-weight: bold;
	            font-size: 25px;
	            margin: 5px 0 0 0;
	            text-align: center;
	        }
	        /* input[type="submit"] {
	       		margin: 0 auto; 
    			display: block;
	            margin-top: 30px;
	            width: 200px;
	            height: 30px;
	            border-width: 0px;
	            border-radius: 6px;
	            font-size: 20px;
	            font-weight: bold;
        	} */
        	.custom-button {
	            display: block;
	            margin: 30px auto 0 auto;
	            width: 200px;
	            height: 30px;
	            border: 0;
	            border-radius: 6px;
	            font-size: 20px;
	            font-weight: bold;
	            text-align: center;
	            text-decoration: none;
	            cursor: pointer;
	        }
        </style>
		<script>
		    function updateInterest() {
		        var userId = '<%= session.getAttribute("userId") %>';
		        
		        if (userId == '') {
		        	console.log("userId22!!sms" + userId);
		            window.location.href = '${contextPath}/user/get/interest';
		        } else {
		        	console.log("userId22!!" + userId);
		            window.location.href = '${contextPath}/user/login/form';
		        }
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
	    	<jsp:param name="selected" value="recomm" />
		</jsp:include>
        <main>
            <div>
                <table class="book_box">
                     <tr>
			 			<td> 
						    <h2 class="ment">아직 추천 도서를 찾지 못했어요!</h2>
	    				</td>
			 		</tr>
			 		<tr>
			 			<td> 
                        	<input class="custom-button" type="button" value="관심사 수정하기" onClick="updateInterest()"/>
	    				</td>
			 		</tr>
                  </table>
             </div>
        </main>
    </body>
</html>