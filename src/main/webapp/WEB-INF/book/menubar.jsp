<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
#top {
	margin: 10px 0 15px 30px;
	text-align: center;
}

#menu1 {
	height: 30px;
	text-align: center;
	padding-top: 10px;
	border: solid 1px #000000;
}

#menu1 li {
	display: inline;
}

.notSelectedPage {
	margin: 0 30px 0 30px;
}

.selectedPage {
	background-color: rgb(198, 218, 218);
	margin: 0 30px 0 30px;
	font-weight: bold;
}

.user_menu {
	display: flex;
	justify-content: flex-end;
}

.user_menu {
	margin: 15px;
	padding: 0;
}

.user_menu li {
	display: inline-block;
	margin-left: 20px;
}

.user_menu li a {
	text-decoration: none;
	color: black;
	font-weight: bold;
	font-size: 16px;
}
</style>
</head>
<body>
	<header>
		<div>
			<ul class="user_menu">
				<li><a href="mypage.html">����������</a></li>
				<li><a href="login.html">�α���</a></li>
				<li><a href="signup.html">ȸ������</a></li>
			</ul>
		</div>
		<div id="logo">
			<a>åit��</a>
		</div>
	</header>
	<nav>
		<div>
			<ul id="menu1">
				<li class="selectedPage"><a href="./search.jsp">���� �˻�</a></li>
				<li>|</li>
				<li class="notSelectedPage"><a>���� ��õ</a></li>
				<li>|</li>
				<li class="notSelectedPage"><a href="./bookReview.html">���� ����</a></li>
				<li>|</li>
				<li class="notSelectedPage"><a> Q&A &nbsp; &nbsp; &nbsp;
						&nbsp; &nbsp; &nbsp;</a></li>
			</ul>
		</div>
	</nav>

</body>
</html>