<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.member.model.vo.Member,com.common.listener.LoginCheckListener"%>
<%
//서버에서 전송된 request의 loginMember를 가져오자
//Member loginMember=(Member)request.getAttribute("loginMember"); 
//	-> 데이터 유지가 안됨
//HttpSession객체에 저장된 loginMember가져오기
Member loginMember = (Member) session.getAttribute("loginMember");
Cookie[] cookies = request.getCookies();
String saveId = null;
if (cookies != null) {
	for (Cookie c : cookies) {
		if (c.getName().equals("saveId")) {
	saveId = c.getValue();
	break;
		}
	}
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>바니바니당근당근</title>

<link rel=" shortcut icon"
	href="<%=request.getContextPath()%>/images/favicon.ico">
<link rel="icon" href="<%=request.getContextPath()%>/images/favicon.ico">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.9/slick.min.css" rel="stylesheet" type="text/css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.9/slick-theme.min.css" rel="stylesheet" type="text/css">

<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.9/slick.min.js"></script>



<style>
html, body {
	font-family: 'RIDIBatang';
	margin: 0;
	padding: 0;
	height: 100%;
	width : 100%;

}

.logo {
	width: 160px;
	height: 160px;
	margin-left: 30.3%;
}

.navbar_logo {
	display: block;
	margin: 0px auto;
}

.header {
	display: flex; /*한줄에 나란히 */
	justify-content: space-between; /*중간중간 spacing 같은 중심축에서 넣는다.*/
	align-items: center;
	margin-top: 0px;
}

.loginicon {
	width: 30px;
	height: 30px;
}

.menubar {
	width: 60px;
	height: 30px;
}

.menubar ul {
	width: 100%;
}

.carrot {
	/* margin: auto; */
}

.market{
	margin-left:10px;
	width:80px;
	height:90px;
}

.menubar li{
	
}


</style>
</head>
<body>
	<div class="header">
	
		<div class="boardPage">
			<a href="<%=request.getContextPath()%>/board/boardPage">
			<img class="market"
				src="<%=request.getContextPath()%>/images/page.png";></a>
		</div>
		<div class="carrot">
			<a href="<%=request.getContextPath()%>/index.jsp">
			<img class="logo"
				src="<%=request.getContextPath()%>/images/carrotlogo.png";></a>
		</div>
		<div class="nav">
			<%
			if (loginMember == null) {
			%>
			<a href="<%=request.getContextPath()%>/loginPage" class="loginfrm"
				style="font-size: 15px; text-decoration: none; color: #646464;">login</a>
			<a href="<%=request.getContextPath()%>/loginPage"><img
				class="loginicon"
				src="<%=request.getContextPath()%>/images/loginicon.png"></img></a>
			<%
			} else {
			%>
			<p style="font-size: 15px; text-decoration: none; color: #646464; margin-bottom : 5px; margin-right: 10px;"><%=loginMember.getUserName()%>님 앙뇽!
			</p>
			<a href="<%=request.getContextPath()%>/logout" class="loginfrm"
				style="font-size: 15px; text-decoration: none; color: #646464;">logout</a>
			<a href="<%=request.getContextPath()%>/myinfo"><img
				class="loginicon"
				src="<%=request.getContextPath()%>/images/loginicon.png"></img></a>
			<%
			}
			%>
			<!-- 메뉴 바 -->
			<img class="btn"
				src="<%=request.getContextPath()%>/images/menubar.png"></img>
		</div>
	</div>



	<!-- 메뉴영역 밖을 누르면 닫힘 -->
	<div onclick="history.back();" class="page_cover"></div>
	<!-- 메뉴영역 -->
	<div id="menu">
		<!-- 뒤로가기 버튼 -->
		<div onclick="history.back();">
			<img class="close"
				src="<%=request.getContextPath()%>/images/rabbiticon.png"></img>
		</div>
		<!-- 메뉴바 이동 -->
		<div class="menubar">
			<ul>
				<li><a href="<%=request.getContextPath()%>/noticePage">공지사항</a></li>
			</ul>
			<ul>
				<%
				if (loginMember == null) {
				%>
				<li><a
					href="<%=request.getContextPath()%>/views/login/loginPage.jsp">로그인
						/<br> 회원가입</a></li>
				<%
				} else {
				%>
				<li><a href="<%=request.getContextPath()%>/myinfo">나의 정보</a></li>
				<%
				}
				%>
			</ul>
			<ul>
				<li><a href="<%=request.getContextPath()%>/board/boardPage">상품목록
						/<br>조회</a></li>
			</ul>
			<ul>
				<li><a href="<%=request.getContextPath()%>/qna.do">1:1 문의</a></li>
			</ul>
		</div>
	</div>

	<script type="text/javascript">
		$(".btn").click(function() {
			$("#menu,.page_cover,html").addClass("open");
			window.location.hash = "#open";
		});
		window.onhashchange = function() {
			if (location.hash != "#open") {
				$("#menu,.page_cover,html").removeClass("open");
			}
		};
	</script>