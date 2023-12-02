<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <link>
        <meta charset="UTF-8">
        <title> 관심 도서 보기</title>
        <style>     
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
	        input[type="submit"] {
	       		margin: 0 auto; 
    			display: block;
	            margin-top: 30px;
	            width: 200px;
	            height: 30px;
	            border-width: 0px;
	            border-radius: 6px;
	            font-size: 20px;
	            font-weight: bold;
        	}
        </style>
        <script>
	        function updateInterest{  
	            form.submit();
	        }
		</script>
    </head>

    <body>
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
				 			<form id="interestForm" action="<c:url value='/user/get/interest'/>" method="GET">
                                <input type="submit" value="관심사 수정하기" onClick="updateInterest()"/>
                            </form>
	    				</td>
			 		</tr>
                  </table>
             </div>
        </main>
    </body>

</html>