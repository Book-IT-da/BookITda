<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
			<div>
				<c:forEach var="review" items="${reviewList}"> 
	               <table class="bookreview_box">
	                   <tbody>
	                       <tr>
	                         <th colspan="2" align="right">
	                           <a href="<c:url value='/review/view'>
									   <c:param name='reviewId' value='${review.reviewId}'/>
							 		 </c:url>">
	                            	상세보기 </a>
	                         </th>
	                       </tr>
	                       <tr>
	                           <td>${review.getNickname()}</td>
	                           <td>${review.getCreateDate()}</td>
	                       </tr>
	                       <tr>
	                           <td colspan="2">
									<c:choose> 
										<c:when test="${review.stars eq 0}">☆☆☆☆☆</c:when> 
										<c:when test="${review.stars eq 1}">★☆☆☆☆</c:when> 
										<c:when test="${review.stars eq 2}">★★☆☆☆</c:when> 
										<c:when test="${review.stars eq 3}">★★★☆☆</c:when> 
										<c:when test="${review.stars eq 4}">★★★★☆</c:when> 
										<c:otherwise>
										★★★★★
										</c:otherwise> 
									</c:choose> 
								</td>
	                       </tr>
	                       <tr>
	                           <td colspan="2">
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
	                           <td colspan="2">${review.getOnelineReview()}</td>
	                       </tr>
	                   </tbody>
	               </table>
	  			</c:forEach>
           </div>