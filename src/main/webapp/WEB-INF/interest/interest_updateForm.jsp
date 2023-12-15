<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.function.Function" %>
<%@ page import="model.Interest" %>
<%@ page import="model.Category" %>

	<%
    /* List<String> selectedCategories = new ArrayList<>();
    selectedCategories.add("1");
    selectedCategories.add("2"); */

    /*  String[] categoryData = {
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
    }; */
    
 // categoryTable에 있는 categoryList 불러오기
    List<Category> categoryList = (List<Category>) session.getAttribute("categoryList");   
    String[] categoryData = new String[categoryList.size() * 2]; 
    
    int index = 0;

    for (Category category : categoryList) {
        categoryData[index++] = String.valueOf(category.getCategoryId());
        categoryData[index++] = category.getCategory();
    }
    
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
    /* request.setAttribute("selectedCategories", selectedCategories); */

    String[] languageData = {
            "1", "Java",
            "2", "Python",
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

        /* List<String> selectedLanguages = new ArrayList<>();
        selectedLanguages.add("3");
        selectedLanguages.add("4"); */
        
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
        /* request.setAttribute("selectedLanguages", selectedLanguages); */
        
        String[] levelData = {
                "1", "초보자",
                "2", "중급자",
                "3", "상급자"
            };

            /* List<String> selectedLevel = new ArrayList<>();
            selectedLevel.add("3"); */
            
            List<Map<String, String>> userLevel = new ArrayList<>();

            for (int i = 0; i < levelData.length; i += 2) {
                String value = levelData[i];
                String label = levelData[i + 1];

                Map<String, String> levelMap = new HashMap<>();
                levelMap.put("value", value);
                levelMap.put("label", label);

                userLevel.add(levelMap);
            }
            
            Interest userLastInterest = (Interest) session.getAttribute("userLastInterest");
            List<String> selectedCategories;
            List<String> selectedLanguages;
            int selectedLevel;
            
            if (userLastInterest != null) {
                List<Integer> selectedCategoryList = userLastInterest.getCategoryList();
                selectedCategories = selectedCategoryList != null
                    ? selectedCategoryList.stream().map(String::valueOf).collect(java.util.stream.Collectors.toList())
                    : new ArrayList<>();
                List<Integer> selectedLanguageList = userLastInterest.getLanguageList();
                selectedLanguages = selectedLanguageList != null
                    ? selectedLanguageList.stream().map(String::valueOf).collect(java.util.stream.Collectors.toList())
                    : new ArrayList<>();
                selectedLevel = userLastInterest.getLevelId();              
                
            } else {
                selectedCategories = new ArrayList<>();
                selectedLanguages = new ArrayList<>();
                selectedLevel = 0; 
            }
            
            request.setAttribute("selectedCategories", selectedCategories);
            request.setAttribute("selectedLanguages", selectedLanguages);
            request.setAttribute("userLevel", userLevel);
            request.setAttribute("selectedLevel", selectedLevel);
            /* session.setAttribute("userId2", "lulu"); */
	%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/userMenu.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/logo.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/menu.css' />" type="text/css">
<title>관심사 수정</title>
<style>
        hr {
            margin-top: 30px;
            margin-bottom: 25px;
            width: 580px;
            height: 2px;
            background-color: black;
        }
        tr, td {
        	   border: 1px solid gray;
        	   border-radius: 10px;
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
        input[type="button"] {
            margin-top: 30px;
            width: 80px;
            height: 30px;
            border-width: 0px;
            border-radius: 6px;
            font-size: 15px;
            font-weight: bold;
        }
    </style>
<script>
function updateInterestCat() {	
    var categoryCheckboxes = document.getElementsByName("interestCategory");

    if (!isAtLeastOneChecked(categoryCheckboxes)) {
        alert("관심 분야를 설정해주세요.");
        return false;
    }
    var form = document.getElementById("interestCatForm");
    form.submit();
}

function updateInterestLang() {	
    var categoryCheckboxes = document.getElementsByName("interestLanguages");

    if (!isAtLeastOneChecked(categoryCheckboxes)) {
        alert("관심 언어를 설정해주세요.");
        return false;
    }
    var form = document.getElementById("interestLangForm");
    form.submit();
}

function updateUserLevel() {	
    var form = document.getElementById("userLevelForm");
    form.submit();
}

function isAtLeastOneChecked(checkboxes) {
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            return true;
        }
    }
    return false;
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
	<center>
		<br>
        <table>
        	<form id="interestCatForm" method="POST" action="<c:url value='/user/update/interest/category'/>">
                <tr>
        			<td colspan="2">&nbsp;&nbsp;&nbsp;관심사를 수정하세요!</td>
        		</tr>
                <tr>
                    <td class="td1">관심 분야 <input type="button" value="수정 완료" onClick="updateInterestCat()"></td>
					<td class="td2">
					    <c:forEach var="category" items="${interestCategories}" varStatus="loop">
					        <c:if test="${loop.index % 4 == 0 && loop.index != 0}">
					            <br><br>
					        </c:if>
					        <input type="checkbox" name="interestCategory" value="${category.value}" 
					               ${selectedCategories.contains(category.value) ? 'checked' : ''}>
					        ${category.label}
					    </c:forEach>
					</td>
                </tr>
             </form>
             <form id="interestLangForm" action="<c:url value='/user/update/interest/language'/>">
                <tr>
                    <td class="td1">관심 언어<input type="button" value="수정 완료" onClick="updateInterestLang()"></td>
                    <td class="td2">
					<c:forEach var="language" items="${interestLanguages}" varStatus="loop">
						<c:if test="${loop.index % 4 == 0 && loop.index != 0}">
					        <br><br>
					    </c:if>
					    <input type="checkbox" name="interestLanguages" value="${language.value}" 
					           ${selectedLanguages.contains(language.value) ? 'checked' : ''}>
					    ${language.label}
					</c:forEach>
					</td>
                </tr>
               </form>
               <form id="userLevelForm" action="<c:url value='/user/update/interest/level'/>" ">
                <tr>
                    <td class="td1">it 수준<input type="button" value="수정 완료" onClick="updateUserLevel()"></td>
                    <td class="td2">
					<c:forEach var="level" items="${userLevel}" varStatus="loop">
						<c:if test="${loop.index % 4 == 0 && loop.index != 0}">
					        <br><br>
					    </c:if>
					    <input type="radio" name="userLevel" value="${level.value}" 
       								${level.value == selectedLevel ? 'checked' : ''}>
					    ${level.label}
					</c:forEach>
					</td>
                </tr>
                </form>
        </table>
	</center>
</body>
</html>