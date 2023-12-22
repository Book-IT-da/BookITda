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
<link rel=stylesheet href="<c:url value='/css/question/view.css' />"
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
	
	function ansUpdate(aId) {
		var updateAnswerForm = document.forms["updateForm_" + aId];
		if (updateAnswerForm.aContent.value == "") {
			alert("답변을 입력하세요.");
			updateAnswerForm.aContent.focus();
			return false;
		}
		updateAnswerForm.submit();
	}
	
	function quesremoveAlert() {
		var msg = confirm("정말 삭제하시겠습니까?");
		
		if (msg) {
			return true;
		}
		else {
			return false;			
		}
	}
	
	function removeAlert(formName) {
		var msg = confirm("정말 삭제하시겠습니까?");
		
		if (msg) {
			formName.submit();
		}
		return false;
	}
	
	function updateAnswer(aId) {
		var answerUpdate = document.getElementById('answerUpdate_' + aId);
		var answerContent = document.getElementById('answerContent_' + aId);
		var answerDelete = document.getElementById('answerDelete_' + aId);
		var answerChange = document.getElementById('answerChange_' + aId);
		
		var answerCancel = document.getElementById('answerCancel_' + aId);
		var answerComplete = document.getElementById('answerComplete_' + aId);
		
		answerContent.style.display = 'none';
		answerDelete.style.display = 'none';
		answerChange.style.display = 'none';
		
		answerUpdate.style.display = 'block';
		answerCancel.style.display = 'block';
		answerComplete.style.display = 'block';
	} 
	
	function answerUpdateCancel(aId) {
		var answerUpdate = document.getElementById('answerUpdate_' + aId);
		var answerContent = document.getElementById('answerContent_' + aId);
		var answerDelete = document.getElementById('answerDelete_' + aId);
		var answerChange = document.getElementById('answerChange_' + aId);
		
		var answerCancel = document.getElementById('answerCancel_' + aId);
		var answerComplete = document.getElementById('answerComplete_' + aId);
		
		answerContent.style.display = 'block';
		answerDelete.style.display = 'block';
		answerChange.style.display = 'block';
		
		answerUpdate.style.display = 'none';
		answerCancel.style.display = 'none';
		answerComplete.style.display = 'none';
		
		var updateForm = document.forms["updateForm_" + aId];
		
		updateForm.aContent.value = answerContent.innerHTML.trim();
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
				<td class="bottom_line" colspan="3">
					<p /> ${ques.qContent}
				</td>
			</tr>
		</table>
		<p />
		<c:if test="${sessionScope.userId == ques.userId or sessionScope.userId == 'admin'}">
			<a
				href="<c:url value='/question/update'><c:param name='qId' value='${ques.qId}'/></c:url>"
				class="button">수정</a>
			<a
				href="<c:url value='/question/delete'><c:param name='qId' value='${ques.qId}'/></c:url>"
				onclick="return quesremoveAlert();" class="button">삭제</a>
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
			<c:forEach var="answer" items="${answerList}">
				<table>
						<tr>
							<td>
								<c:if test="${ques.userId != answer.userId}">
								작성자: ${answer.nickname}</c:if>
								<c:if test="${ques.userId == answer.userId}">
								작성자: <span class="nickname">${answer.nickname}</span></c:if>
							</td>
							<td class="bottom_line">등록일: ${answer.createDate}</td>
							<c:if test="${sessionScope.userId == answer.userId or sessionScope.userId == 'admin'}">
								<td class="bottom_line" id ="button1">
										<input type="button" value="수정" id="answerChange_${answer.aId}" onClick="updateAnswer(${answer.aId})">
										<input type="button" value="완료" id="answerComplete_${answer.aId}" style="display: none;" onClick="ansUpdate(${answer.aId})">
										<input type="button" value="취소" id="answerCancel_${answer.aId}" style="display: none;" onClick="answerUpdateCancel(${answer.aId})" >	
										<form name="ansRemove" method="GET" action="<c:url value='/answer/delete' />">
											<input type="hidden" name="qId" value="${ques.qId}">
											<input type="hidden" name="aId" value="${answer.aId}">
											<input type="button" value="삭제" id="answerDelete_${answer.aId}" onClick="removeAlert(this.form)">
										</form>
								</td>
							</c:if>
						</tr>
						<tr>
							<td class="last_element" colspan="3" id="answerContent_${answer.aId}">
								${answer.answer}
							</td>
							<form name="updateForm_${answer.aId}" method="GET" action="<c:url value='/answer/update'/>">
								<input type="hidden" name="userId" value="${answer.userId}">
								<input type="hidden" name="qId" value="${ques.qId}">
								<input type="hidden" name="aId" value="${answer.aId}">
								<td class="last_element" colspan="3" id="answerUpdate_${answer.aId}" style="display: none;">								
									<textarea id="aContent" name="answer">${answer.answer}</textarea>
								</td>
							</form>
						</tr>
					
				</table>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<br>
				<p>댓글이 없습니다</p>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>