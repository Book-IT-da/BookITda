<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.function.Function"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="model.Category"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
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

String[] languageData = {"1", "Java", "2", "Python", "3", "C", "4", "C++", "5", "Kotlin", "6", "Lua", "7", "CSS", "8",
		"JavaScript", "9", "Swift", "10", "C#", "11", "Node.js", "12", "TypeScript"};

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

String[] levelData = {"1", "초보자", "2", "중급자", "3", "상급자"};

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
session.setAttribute("userId2", "lulu2");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/userMenu.css' />"
	type="text/css">
<link rel=stylesheet href="<c:url value='/css/logo.css' />"
	type="text/css">
<link rel=stylesheet href="<c:url value='/css/menu.css' />"
	type="text/css">
<link rel=stylesheet
	href="<c:url value='/css/interest/createForm.css' />" type="text/css">
<title>관심사 설정</title>

<script>
	function createInterest() {
		var categoryCheckboxes = document.getElementsByName("interestCategory");
		var languageCheckboxes = document
				.getElementsByName("interestLanguages");
		var levelCheckboxes = document.getElementsByName("userLevel");

		if (!isAtLeastOneChecked(categoryCheckboxes)
				|| !isAtLeastOneChecked(languageCheckboxes)
				|| !isAtLeastOneChecked(levelCheckboxes)) {
			alert("관심사를 모두 설정해주세요.");
			return false;
		}
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

	function skip() {
		window.location.href = "${contextPath}/book/searchList";
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
		<form name="form" method="POST"
			action="<c:url value='/user/interest/create'/>">
			<table>
				<tr>
					<td colspan="2">&nbsp;&nbsp;&nbsp;관심사를 설정하세요!</td>
				</tr>
				<tr>
					<td class="td1">관심 분야</td>
					<td class="td2"><c:forEach var="category"
							items="${interestCategories}" varStatus="loop">
							<c:if test="${loop.index % 4 == 0 && loop.index != 0}">
								<br>
								<br>
							</c:if>
							<input type="checkbox" name="interestCategory"
								value="${category.value}"> ${category.label}
					</c:forEach></td>
				</tr>
				<tr>
					<td class="td1">관심 언어</td>
					<td class="td2"><c:forEach var="language"
							items="${interestLanguages}" varStatus="loop">
							<c:if test="${loop.index % 4 == 0 && loop.index != 0}">
								<br>
								<br>
							</c:if>
							<input type="checkbox" name="interestLanguages"
								value="${language.value}">
					    ${language.label}
					</c:forEach></td>
				</tr>
				<tr>
					<td class="td1">it 수준</td>
					<td class="td2"><c:forEach var="level" items="${userLevel}"
							varStatus="loop">
							<c:if test="${loop.index % 4 == 0 && loop.index != 0}">
								<br>
								<br>
							</c:if>
							<input type="checkbox" name="userLevel" value="${level.value}">
					    ${level.label}
					</c:forEach></td>
				</tr>
			</table>
			<div class="button_container">
				<input type="button" value="설정 완료" onClick="createInterest()">
				<input type="button" value="건너뛰기" onClick="skip()">
			</div>
		</form>
	</center>
</body>
</html>