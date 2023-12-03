<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var = "selected" value = "${param.selected}"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
		<nav>
            <div>
                <ul id="menu1">
                    <li class="${selected eq 'random' ? 'selectedPage' : 'notSelectedPage'}"><a href="${contextPath}/book/randomBooks">HOME</a></li>
                    <li>|</li>
                    <li class="${selected eq 'search' ? 'selectedPage' : 'notSelectedPage'}"><a href="${contextPath}/book/allList">도서 검색</a></li>
                    <li>|</li>
                    <li class="${selected eq 'recomm' ? 'selectedPage' : 'notSelectedPage'}"><a href="${contextPath}/recommBook/view">도서 추천</a></li>
                    <li>|</li>
                    <li class="${selected eq 'review' ? 'selectedPage' : 'notSelectedPage'}"><a href="${contextPath}/review/list">도서 리뷰</a></li>
                    <li>|</li>
                    <li class="${selected eq 'qna' ? 'selectedPage' : 'notSelectedPage'}"><a href="${contextPath}/question/list">&nbsp;&nbsp; Q&A &nbsp;&nbsp;</a></li>
                	<li>|</li>
                	<li class="notSelectedPage">
          				<a>&nbsp;&nbsp; 관리 &nbsp;&nbsp;</a>
						<ul class="sub_menu">
							<li><a href="">회원 관리</a></li><br/>
							<li><a href="">책 관리</a></li><br/>
							<li><a href="">카테고리 관리</a></li><br/>
						</ul>
			        </li>
                </ul>
            </div>    
        </nav>
         