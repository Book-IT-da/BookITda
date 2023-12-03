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
	function questionRemove() {
		return confirm("정말 삭제하시겠습니까?");
	}

	function answerCreate() {
		if (form.content.value == "") {
			alert("답변을 입력하세요.");
			form.content.focus();
			return false;
		}
		form.submit();
	}
</script>
<style>
table {
	margin-top: 30px;
	width: 1000px;
}

td {
	height: 50px;
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

.button {
	margin-top: 30px;
	width: 100px;
	height: 50px;
	border: none;
	border-radius: 6px;
	background-color: skyblue;
	font-size: 25px;
	color: white;
}

.ansBtn {
	border-left: none;
	border-bottom: none;
}

#title {
	text-align: center;
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
	<center>
		<!-- 질문 글 -->
		<table>
			<tr>
				<td colspan="3">질문내용</td>
			</tr>
			<tr>
				<td id="title" colspan="3">제목: ${ques.title}</td>
			</tr>
			<tr>
				<td>사용자: ${ques.nickname}</td>
				<td>등록일: ${ques.createDate}</td>
				<td>조회수: ${ques.views}</td>
			</tr>
			<tr>
				<td colspan="3">글 내용<br> ${ques.qContent}
				</td>
			</tr>
		</table>
		<a
			href="<c:url value='/question/update'><c:param name='qId' value='${ques.qId}'/></c:url>">수정</a>
		<a
			href="<c:url value='/question/delete'><c:param name='qId' value='${ques.qId}'/></c:url>"
			onclick="return questionRemove();">삭제</a> <a
			href="<c:url value='/question/list'/>" class="button">목록</a>

		<!-- 답변 글 작성 -->
		<form name="form" method="POST"
			action="<c:url value='/answer/create'/>">
			<table>
				<input type="hidden" name="qId" value="${ques.qId}">
				<tr>
					<td><textarea name="content" required></textarea> <input
						type="button" value="작성" onClick="answerCreate()"></td>
				</tr>
			</table>
		</form>

		<!-- 답변 글 보기  -->
		<c:choose>
			<c:when test="${not empty answerList}">
				<table>
					<c:forEach var="answer" items="${answerList}">
						<tr>
							<td></td>
						<tr>
						<tr>
							<td colspan="2">답변내용</td>
							<td class="ansBtn"><a
								href="<c:url value=''><c:param name='qId' value='${ques.qId}'/></c:url>"
								onclick="">수정</a></td>
							<td class="ansBtn"><a
								href="<c:url value=''><c:param name='qId' value='${ques.qId}'/></c:url>"
								onclick="">삭제</a></td>
						</tr>
						<tr>
							<td>닉네임: ${answer.nickname}</td>
							<td>등록일: ${answer.createDate}</td>
						</tr>
						<tr>
							<td colspan="2">글 내용<br>${answer.answer}</td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<p>댓글이 없습니다</p>
			</c:otherwise>
		</c:choose>
	</center>
</body>
</html>