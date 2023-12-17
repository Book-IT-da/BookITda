<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel=stylesheet href="<c:url value='/css/userMenu.css' />"
	type="text/css">
<link rel=stylesheet href="<c:url value='/css/logo.css' />"
	type="text/css">
<link rel=stylesheet href="<c:url value='/css/menu.css' />"
	type="text/css">

<title>질문 글 상세보기</title>

<script>
	function answerCreate() {
		if (form.content.value == "") {
			alert("답변을 입력하세요.");
			form.content.focus();
			return false;
		}
		form.submit();
	}
	
	function answerContent() {
		var content = document.getElementById('ansContent');
		var form = document.getElementById('ansForm');
		var answer = document.getElementById('answer');
		var answer_form = document.getElementById('content_form');
		if (content.style.display !== 'none') {
			content.style.display = 'none';
			form.style.display = 'block';
			answer.style.display = 'none';
			answer_form.style.display = 'block';
		}
	}
	function ansForm() {
		var content = document.getElementById('ansContent');
		var form = document.getElementById('ansForm');
		var answer = document.getElementById('answer');
		var answer_form = document.getElementById('content_form');
		var updateAns = document.getElementById('updateAns').value;
		if (form.style.display !== 'none') {
			content.style.display = 'block';
			form.style.display = 'none';
			answer.style.display = 'block';
			answer_form.style.display = 'none';
			answer.innerText = updateAns;
		}
		
		form2.submit();
	}
</script>
<style>
table {
	margin-top: 30px;
	width: 1000px;
	border-collapse: collapse;
}

td {
	height: 50px;
	border-top: 1px solid #444444;
	border-bottom: 1px solid #444444;
	border-left: 1px solid #444444;
}

td:first-child {
	border-left: none;
}

textarea {
	width: 500px;
	height: 100px;
}

a {
	text-decoration: none;
}

.button {
	margin-top: 30px;
	width: 100px;
	height: 50px;
	border: none;
	border-radius: 6px;
	font-size: 20px;
	color: blue;
}

#title {
	border-top: 1.0px solid black;
	text-align: center;
	font-size: 20px;
	font-weight: bold;
}

#answer_input {
	font-size: 16px;
	width: 500px;
	height: 100px;
	padding: 10px;
}

#ansForm {
	display: none;
}
#content_form {
	display: none;
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
		<jsp:param name="selected" value="qna" />
	</jsp:include>
	<br>
	<div align="center">
		<!-- 질문 글 -->
		<table>
			<!-- 
			<tr>
				<td colspan="3">질문내용</td>
			</tr>
			 -->
			<tr>
				<td id="title" colspan="3">제목: ${ques.title}</td>
			</tr>
			<tr>
				<td>작성자: ${ques.nickname}</td>
				<td>등록일: ${ques.createDate}</td>
				<td>조회수: ${ques.views}</td>
			</tr>
			<tr>
				<td colspan="3">질문 내용
					<p /> ${ques.qContent}
				</td>
			</tr>
		</table>
		<p />
		<c:if test="${sessionScope.userId == ques.userId}">
			<a
				href="<c:url value='/question/update'><c:param name='qId' value='${ques.qId}'/></c:url>"
				class="button">수정</a>
			<a
				href="<c:url value='/question/delete'><c:param name='qId' value='${ques.qId}'/></c:url>"
				onclick="return questionRemove();" class="button">삭제</a>
		</c:if>
		<a href="<c:url value='/question/list'/>" class="button">목록</a>

		<p />
		<br> <br>

		<!-- 답변 글 작성 -->
		<form name="form" method="POST"
			action="<c:url value='/answer/create'/>">
			<input type="hidden" name="qId" value="${ques.qId}">
			<textarea id="answer_input" name="content" placeholder="답변을 입력해주세요"
				required></textarea>
			<input type="button" value="작성" onClick="answerCreate()">
		</form>

		<br>

		<!-- 답변 글 보기  -->
		<c:choose>
			<c:when test="${not empty answerList}">
				<table>
					<c:forEach var="answer" items="${answerList}">
						<tr>
							<td>작성자: ${answer.nickname}</td>
							<td>등록일: ${answer.createDate}</td>
							<c:if test="${sessionScope.userId == answer.userId}">
								<td id ="button1"><div id="ansContent">
								<input type="button"
									
									onclick="answerContent()" value="수정"> <a
									href="<c:url value='/answer/delete'><c:param name='aId' value='${answer.aId}'/></c:url>"
									onclick="ansRemoveAlert()">삭제</a>
									</div>
									<div id="ansForm">
										<input type="button" onClick="ansForm()" value="완료">
										<input type="button" onClick="" value="취소">
									</div>
									</td>
							</c:if>
						</tr>
						<tr>
							<td colspan="3">
							<div id="answer">
								<p /> ${answer.answer}
							</div>
							<div id="content_form">
								<form name="form2" method="GET" action="<c:url value='/answer/update'/>">
									<input type="hidden" name="userId" value="${answer.userId}">
									<input type="hidden" name="qId" value="${ques.qId}">
									<input type="hidden" name="aId" value="${answer.aId}">
									<textarea id="updateAns" name="answer">${answer.answer}</textarea>
								</form>
							</div>
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<br>
				<p>댓글이 없습니다</p>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>