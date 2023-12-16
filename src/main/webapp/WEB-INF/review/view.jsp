<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	Review review = (Review)request.getAttribute("review");
%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel=stylesheet href="<c:url value='/css/userMenu.css' />" type="text/css">
		<link rel=stylesheet href="<c:url value='/css/logo.css' />" type="text/css">
		<link rel=stylesheet href="<c:url value='/css/menu.css' />" type="text/css">
		<link rel=stylesheet href="<c:url value='/css/review/view.css' />" type="text/css">
		<title>상세보기</title>
        <script>
			function reviewRemove() {
				var answer = confirm("정말 삭제하시겠습니까?");

				if(answer){
					deleteReviewForm.submit();
				}
				return false;
			}
			
			function commentCreate() {
				if (form.rContent.value == "") {
					alert("댓글을 입력하세요.");
					form.rContent.focus();
					return false;
				}
				form.submit();
			}
			
			function commentRemove(formElement){
				var answer = confirm("정말 삭제하시겠습니까?");

				if(answer){
					formElement.submit();
				}
				return false;
			}
			
			function commentModify(commentId){
				var updateCommentForm = document.forms["updateCommentForm_" + commentId];
				if (updateCommentForm.rContent.value == "") {
					alert("댓글을 입력하세요.");
					updateCommentForm.rContent.focus();
					return false;
				}
				updateCommentForm.submit();
			}
			
			function commentModifyInput(commentId){
				 var commentUpdate = document.getElementById('commentUpdate_' + commentId);
				 var commentRead = document.getElementById('commentRead_' + commentId);
				 var commentDelete = document.getElementById('commentDelete_' + commentId);
				 var commentChange = document.getElementById('commentChange_' + commentId);
				 
				 var commentCancel = document.getElementById('commentCancel_' + commentId);
				 var commentMod = document.getElementById('commentMod_' + commentId);
				 
				 commentRead.style.display = 'none';
				 commentDelete.style.display = 'none';
				 commentChange.style.display = 'none';
				 
				 commentUpdate.style.display = 'table-cell';
				 commentCancel.style.display = 'table-cell';
				 commentMod.style.display = 'table-cell';
			}
			
			function commentModifyCancel(commentId){
				
				
				 var commentUpdate = document.getElementById('commentUpdate_' + commentId);
				 var commentRead = document.getElementById('commentRead_' + commentId);
				 var commentDelete = document.getElementById('commentDelete_' + commentId);
				 var commentChange = document.getElementById('commentChange_' + commentId);
				 
				 var commentCancel = document.getElementById('commentCancel_' + commentId);
				 var commentMod = document.getElementById('commentMod_' + commentId);
				 
				 commentRead.style.display = 'table-cell';
				 commentDelete.style.display = 'table-cell';
				 commentChange.style.display = 'table-cell';
				 
				 commentUpdate.style.display = 'none';
				 commentCancel.style.display = 'none';
				 commentMod.style.display = 'none';
				 
				 var updateForm = document.forms["updateCommentForm_" + commentId];
				    
				 updateForm.rContent.value = commentRead.innerHTML.trim();
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
    	<jsp:param name="selected" value="review" />
	</jsp:include>
	<main>
		<c:if test="${sessionScope.userId == review.userId or sessionScope.userId == 'admin'}">
            <div align="right">
			<form name="updateReviewForm" method="GET"
				action="<c:url value='/review/update' />">
    			<input type="hidden" name="reviewId" value="<%=review.getReviewId()%>">
    			<button type="submit">수정하기</button>
            </form>
            <form name="deleteReviewForm" method="GET"
				action="<c:url value='/review/delete' />">
    			<input type="hidden" name="reviewId" value="<%=review.getReviewId()%>">
                <input type="button" value="삭제하기"  onClick="reviewRemove()">
            </form>
            </div>
       </c:if> 
            <div>
                <table class="book_box">
                    <tbody>
                      <tr>
                        <td>작정자</td>
                        <td colspan="2"><%=review.getNickname()%></td>
                        <td>작성일</td>
                        <td><%=review.getCreateDate()%></td>
                      </tr>
                      <tr>
                        <td colspan="5"><%=review.getTitle()%></td>
                      </tr>
                      <tr>
                        <td class="book_pic" rowspan="6"><img class="book_cover" src="<c:url value='${review.cover_path}' />" /></td>
                        <td>저자</td>
                        <td colspan="3"><%=review.getAuthor()%></td>
                      </tr>
                      <tr>
                        <td>출판사</td>
                        <td colspan="3"><%=review.getPublisher()%></td>
                      </tr>
                      <tr>
                        <td>별점</td>
                        <td>
                        <c:choose> 
							<c:when test="${review.getStars() eq 0}">☆☆☆☆☆</c:when> 
							<c:when test="${review.getStars() eq 1}">★☆☆☆☆</c:when> 
							<c:when test="${review.getStars() eq 2}">★★☆☆☆</c:when> 
							<c:when test="${review.getStars() eq 3}">★★★☆☆</c:when> 
							<c:when test="${review.getStars() eq 4}">★★★★☆</c:when> 
							<c:otherwise>
							★★★★★
							</c:otherwise> 
						</c:choose> 
						</td>
                        <td>난이도</td>
                        <td>
                        <c:choose> 
							<c:when test="${review.getLevel() eq 1}">초급</c:when> 
							<c:when test="${review.getLevel() eq 2}">중급</c:when> 
							<c:otherwise>
							고급
							</c:otherwise> 
						</c:choose> 
                        </td>
                      </tr>
                      <tr>
                        <td>독서 목적</td>
                        <td><%=review.getPurpose()%></td>
                        <td>추천 대상</td>
                        <td><%=review.getRecommObj()%></td>
                      </tr>
                      <tr>
                        <td>키워드</td>
                        <td colspan="3"><%=review.getKeyword()%></td>
                      </tr>
                      <tr>
                        <td>한줄평</td>
                        <td colspan="3"><%=review.getOnelineReview()%></td>
                      </tr>
                      <tr>
                        <td colspan="5"></td>
                      </tr>
                      <tr>
                        <td colspan="5">상세 리뷰</td>
                      </tr>
                      <tr>
                        <td class="book_multiline_review" colspan="5"><%=review.getMultilineReview()%></td>
                      </tr>
                    </tbody>
                </table>
            </div>
            <div align="center">
	            <form name="form" method="POST"
					action="<c:url value='/review/comment/create'/>">
					<input type="hidden" name="reviewId" value="${review.reviewId}">
					<input type="hidden" name="userId" value="${userId}">
					<textarea id="comment_input" name="rContent" placeholder="답변을 입력해주세요"
						required></textarea>
					<input type="button" value="작성" onClick="commentCreate()">
				</form>
                
                <c:forEach var="comment" items="${commentList}"> 
	                <div>
	                    <table class="comment_box">
	                        <thead>
	                          <tr>
	                            <td class="comment_nick">${comment.nickname}</td>
	                            <td>${comment.createDate}</td>
	                            <td>
									<c:if test="${sessionScope.userId == comment.userId or sessionScope.userId == 'admin'}">
							            <form name="deleteCommentForm" method="GET"
											action="<c:url value='/review/comment/delete' />">
											<input type="hidden" name="reviewId" value="${review.reviewId}">
							    			<input type="hidden" name="commentId" value="${comment.commentId}">
							                <input type="button" value="삭제" id="commentDelete_${comment.commentId}" onClick="commentRemove(this.form)">
							            </form>
							                <input type="button" value="수정" id="commentChange_${comment.commentId}" onClick="commentModifyInput(${comment.commentId})">
							                <input type="button" value="완료" id="commentMod_${comment.commentId}" style="display: none;" onClick="commentModify(${comment.commentId})">
							                <input type="button" value="취소" id="commentCancel_${comment.commentId}" style="display: none;" onClick="commentModifyCancel(${comment.commentId})">	
						            </c:if>
	                            </td>
	                          </tr>
	                        </thead>
	                        <tbody>
	                          <tr>
 							    <form name="updateCommentForm_${comment.commentId}" method="GET"
									action="<c:url value='/review/comment/update' />">
									<input type="hidden" name="userId" value="${userId}">
									<input type="hidden" name="reviewId" value="${review.reviewId}">
							    	<input type="hidden" name="commentId" value="${comment.commentId}">
									<td class="comment_detail" colspan="3" id="commentUpdate_${comment.commentId}" style="display: none;">
											<textarea id="comment_input" name="rContent" required>${comment.rContent}</textarea>	
		                            </td>
								</form>
	                            <td class="comment_detail" colspan="3" id="commentRead_${comment.commentId}">
	                            	${comment.rContent}
	                            </td>
	                          </tr>
	                        </tbody>
	                    </table>
	                </div>
                </c:forEach>
            </div>
        </main>
</body>
</html>