<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<ul class="user_menu">
		<li><a href="<c:url value='/user/mypage'/>">마이페이지</a></li>
		<c:if test="${sessionScope.userId==null}">
			<li><a href="<c:url value='/user/login/form'/>">로그인</a></li>
		</c:if>
		<c:if test="${sessionScope.userId!=null}">
			<li><a href="<c:url value='/user/logout'/>">로그아웃</a></li>
		</c:if>
		<li><a href="<c:url value='/user/register'/>">회원가입</a></li>
	</ul>
</div>