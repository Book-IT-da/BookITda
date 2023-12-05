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
		<title>상세보기</title>
		<style>     
            li a {
                color: black;
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
            .book_box th{
                border-color:black;
                border-style:solid;
                border-width:1px;
                padding:10px 5px;
            }
            .book_pic{
                width: 20%;
            }
            .book_cover{
				width: 200px;
            }
            .book_multiline_review{
                height: 100px;
            }
            .comment_box  {
                border-collapse:collapse;
                border-spacing:0;
                margin: auto;
                width: 50%;
                height: 80%;
                margin-top: 30px;
                margin-bottom: 30px;
            }
            .comment_box td{
                border-color:black;
                border-style:solid;
                border-width:1px;
                padding:10px 5px;
            }
            .comment_box th{
                border-color:black;
                border-style:solid;
                border-width:1px;
                padding:10px 5px;
            }
            .comment_detail{
                height: 100px;
            }
            .comment_nick{
            	width: 500px;
            }
            td form { 
            	float: right; 
            }
        </style>
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
			
			function commentRemove(){
				var answer = confirm("정말 삭제하시겠습니까?");

				if(answer){
					deleteCommentForm.submit();
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
    	<jsp:param name="selected" value="review" />
	</jsp:include>
	<main>
		<c:if test="${sessionScope.userId == review.userId}">
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
                        <td class="book_pic" rowspan="6"><%-- <img class="book_cover" src="<c:url value='/images/bookCover/${review.cover_path}' />" /> --%></td>
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
									<c:if test="${sessionScope.userId == comment.userId}">
							            <form name="deleteCommentForm" method="GET"
											action="<c:url value='/review/comment/delete' />">
											<input type="hidden" name="reviewId" value="${review.reviewId}">
							    			<input type="hidden" name="commentId" value="${comment.commentId}">
							                <input type="button" value="삭제"  onClick="commentRemove()">
							            </form>
							            <form name="updateCommentForm" method="GET"
											action="<c:url value='/comment/update' />">
							    			<input type="hidden" name="commentId" value="">
							                <input type="button" value="수정"  onClick="commentModify()">
							            </form>
						            </c:if>
	                            </td>
	                          </tr>
	                        </thead>
	                        <tbody>
	                          <tr>
	                            <td class="comment_detail" colspan="3">${comment.rContent}</td>
	                          </tr>
	                        </tbody>
	                    </table>
	                </div>
                </c:forEach>
            </div>
        </main>
</body>
</html>