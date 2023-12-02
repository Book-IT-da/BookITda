<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.function.Function" %>

	<%
     String[] categoryData = {
        "1", "프로그래밍",
        "2", "OS",
        "3", "네트워크",
        "4", "해킹",
        "5", "보안",
        "6", "모바일",
        "7", "DB",
        "8", "시스템 서버",
        "9", "컴퓨터 공학",
        "10", "게임",
        "11", "WEB",
        "12", "그래픽"
    };
    
    List<Map<String, String>> interestCategories = new ArrayList<>();

    for (int i = 0; i < categoryData.length; i += 2) {
        String value = categoryData[i];
        String label = categoryData[i + 1];

        Map<String, String> categoryMap = new HashMap<>();
        categoryMap.put("value", value);
        categoryMap.put("label", label);

       interestCategories.add(categoryMap);
    }
    request.setAttribute("interestCategories", interestCategories);

    String[] languageData = {
            "1", "Python",
            "2", "Java",
            "3", "C",
            "4", "C++",
            "5", "Kotlin",
            "6", "Lua",
            "7", "CSS",
            "8", "JavaScript",
            "9", "Swift",
            "10", "C#",
            "11", "Node.js",
            "12", "TypeScript"
        };
        
        List<Map<String, String>> interestLanguages = new ArrayList<>();

        for (int i = 0; i < languageData.length; i += 2) {
            String value = languageData[i];
            String label = languageData[i + 1];

            Map<String, String> languageMap = new HashMap<>();
            languageMap.put("value", value);
            languageMap.put("label", label);

            interestLanguages.add(languageMap);
        }

        request.setAttribute("interestLanguages", interestLanguages);
        
        String[] levelData = {
                "1", "초보자",
                "2", "중급자",
                "3", "상급자"
            };

            List<Map<String, String>> userLevel = new ArrayList<>();

            for (int i = 0; i < levelData.length; i += 2) {
                String value = levelData[i];
                String label = levelData[i + 1];

                Map<String, String> levelMap = new HashMap<>();
                levelMap.put("value", value);
                levelMap.put("label", label);

                userLevel.add(levelMap);
            }

            request.setAttribute("userLevel", userLevel);
            session.setAttribute("userId2", "lulu");
	%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>관심사 설정</title>
	<style>
        hr {
            margin-top: 30px;
            margin-bottom: 25px;
            width: 580px;
            height: 2px;
            background-color: black;
        }
        tr, td {
        	   border: 1px solid black;
        
        }
        .td1 {
        	width: 100px;
        }
        td {
       	 	width: 500px;
            height: 55px;
            padding: 30px 0 30px 0;
            font-size: 20px;
            text-align: center;
        }
        input {
        	width: 15px;
        	height: 15px;
            border-radius: 3px;
            border-width: 1px;
            border-color: #CCCCCC;
        }
        .input-container input {
		    margin-right: 10px; 
		}
        input[type="submit"] {
            margin-top: 30px;
            width: 100px;
            height: 30px;
            border-width: 0px;
            border-radius: 6px;
            font-size: 20px;
            font-weight: bold;
        }
    </style>
<script>
function createInterest() {	
	form.submit();
}
</script>
</head>
<body>
	<center>
        <h1>관심사 생성</h1>
        <hr>
	<form method="POST" action="<c:url value='/user/interest/create' />">
        <table>
			<tr>
            	<td class="td1">관심 분야</td>
				<td class="td2">
					<c:forEach var="category" items="${interestCategories}" varStatus="loop">
						<c:if test="${loop.index % 4 == 0 && loop.index != 0}">
							<br><br>
						</c:if>
						<input type="checkbox" name="interestCategory" value="${category.value}"> ${category.label}
					</c:forEach>
				</td>
			</tr>
            <tr>
            	<td class="td1">관심 언어</td>
                    <td class="td2">
					<c:forEach var="language" items="${interestLanguages}" varStatus="loop">
						<c:if test="${loop.index % 4 == 0 && loop.index != 0}">
					        <br><br>
					    </c:if>
					    <input type="checkbox" name="interestLanguages" value="${language.value}">
					    ${language.label}
					</c:forEach>
					</td>
                </tr>
                <tr>
                    <td class="td1">it 수준</td>
                    <td class="td2">
					<c:forEach var="level" items="${userLevel}" varStatus="loop">
						<c:if test="${loop.index % 4 == 0 && loop.index != 0}">
					        <br><br>
					    </c:if>
					    <input type="checkbox" name="userLevel" value="${level.value}">
					    ${level.label}
					</c:forEach>
					</td>
                </tr>
        </table>
	    <div class="button_container">
		    <input type="submit" value="설정 완료" onClick="createInterest()">
		    <input type="submit" value="건너뛰기">
	   </div>
	</form>
	</center>
</body>
</html>