<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List,com.board.model.vo.Board"%>
<%
List<Board> list = (List<Board>) request.getAttribute("list");
%>

<%@ include file="views/common/header.jsp"%>
<style>

.maincontainer {
	text-align: center;
	margin: 200px;
}

.mainproduct {
	width: 100%;
	height: 500px;
	margin-bottom: 50px;
	background: #f1f2f3;
}

.mainproduct>h1 {
	padding-top: 40px;
	text-align: center;
	margin-bottom: 10px;
}

p {
	text-align: center;
	font-size: 13px;
	font-weight: lighter;
	margin: 5px 0 10px 0;
}

.products {
	display: flex;
}

.products>li {
	flex: 1;
	background: white;
	margin-top: 49px;
	height: 400px;
	margin-right: 20px;
	padding-left: 0px;
}

.products>li:not(:last-child) {
	margin-right: 20px;
}

/* 메인 이미지 슬라이드  */
.main {
	font-family: Arial;
	height: 72%;
	width: 70%;
	display: block;
	margin: 0 auto;
	background: #f1f2f3;
}

.slick-slider {
	margin-bottom: 30px;
	height: 90%;
}

.ex {
	text-align: center;
	display: table;
	/*  border:1px solid #cecece; */
	width: 200%;
	height: 100%;
}

.imgclass {

	display: table-cell;
	vertical-align: middle;
	position: relative;
}

.new {
	max-width: 400px;
	max-height: 300px;
	margin-left : 60px; 
}

.action {
	display: block;
	margin: 100px auto;
	width: 100%;
	text-align: center;
}

.action a {
	display: inline-block;
	padding: 5px 10px;
	background: #f30;
	color: #fff;
	text-decoration: none;
}

.action a:hover {
	background: #000;
}

.slick-next:before, .slick-prev:before {
	color: black;
}

.slick-track {
	height: 100%
}

.slick-list {
	height: 100%;
	overflow: hidden;
	margin: 0;
	padding: 0;
}

.adbox {
	width: 100%;
	height: 500%;
	margin-top: 3%;
	margin-bottom: 30px;
	background: #f1f2f3;
}

.adbox>h1 {
	padding-top: 20px;
	text-align: center;
	font-size: 1.5em;
	margin-block-start: 0.83em;
	margin-block-end: 0.83em;
	margin-bottom: 10px;
}

.ad {
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: center;
	vertical-align: middle;
}

.ad td {
	padding-right: 13px;
	padding-left: 13px
}

.ad p {font-sie
	
}

.adimgbox {
	width: 380px;
	height: 220px;
	overflow: hidden;
	margin: 0 auto;
}

.ad td p {
	margin-bottom: 10px;
}

.adimg {
	width: 100%;
	height: 100%;
	object-fit: cover;
}

/* .img new {
	width: 640px;
	width: 728px;
} */

/* 공사중 */
h3 {
	background: #fff;
	color: #ff9800;
	font-size: 36px;
	line-height: 210px;
	margin: 10px;
	padding: 2%;
	position: relative;
	text-align: center;
	height: 150%;
}
</style>
<div class="wrapper">
	<section id="content" style="min-height: 400px;">
		<div class="mainproduct">
			<h1>당근당근바니바니 상점</h1>
			<p>당근당근바니바니 판매상품을 확인해주세요.</p>
			<div class="main">
				<div class="slider slider-nav">
					<%
			if (list.isEmpty()) {
			%>
					<p>조회된 상품이 없습니다.</p>
					<%} else {
				for (Board b : list) {
					if (b.getBoardIsDelete() == 0) {
						%>
					<div class="ex">
						<div class="imgclass">
							<a
								href="<%=request.getContextPath()%>/board/boardView?searchType=&keyword=&no=<%=b.getBoardNumber()%>">
								<img class="new"
								src="<%=request.getContextPath()%>/upload/board/<%=b.getBoardReFilePath()%>" />
							</a>
						</div>
					</div>
					<%}				}
				} %>
				</div>
			</div>
		</div>
	</section>
	<%if (loginMember == null) {%>
	<input type="hidden" id="loginCheck"></input>
	<%} else {%>
	<input type="hidden" id="loginCheck" value="1">
	<%}%>
	<div class="adbox">
		<h1>광고</h1>
		<p>당근당근바니바니에 광고를 걸어보세요.</p>
		<div class="ad">
			<table>
				<tr>
					<td>
						<div class="adimgbox">
							<a href="https://www.aesop.com/kr/"><img class="adimg"
								src="<%=request.getContextPath()%>/images/asope.jpg" /></a>
						</div>
						<p>AESOP</p>
					</td>
					<td>
						<div class="adimgbox">
							<a href="https://ohou.se/"><img class="adimg"
								src="<%=request.getContextPath()%>/images/ohHouse.jpg" /></a>
						</div>
						<p>오늘의 집</p>
					</td>
					<td>
						<div class="adimgbox">
							<a href="https://www.chanel.com/ko_KR/"><img class="adimg"
								src="<%=request.getContextPath()%>/images/chanel.jpg" /></a>
						</div>
						<p>CHANEL</p>
					</td>
				</tr>
			</table>
		</div>
	</div>

</div>

<script>

	$(".ex").click(function() { 
		var bNum = document.getElementById("bNum").value;
		var log = document.getElementById("loginCheck").value;
		
		if(!log){
			alert("로그인 후 확인 가능합니다.");
			location.href="<%=request.getContextPath()%>/views/login/loginPage.jsp"; 
		}else {
			location.href="<%=request.getContextPath()%>/board/boardView?cPage=<%=request.getAttribute("cPage")%>&no=" + bNum; 
		}
	} );
	$('.slider-for').slick({
	   slidesToShow: 1,
	   slidesToScroll: 1,
	   arrows: false,
	   fade: true,
	   asNavFor: '.slider-nav'
	 });
	 $('.slider-nav').slick({
	   slidesToShow: 3,
	   slidesToScroll: 1,
	   asNavFor: '.slider-for',
	   dots: true,
	   focusOnSelect: true
	 });

	 $('a[data-slide]').click(function(e) {
	   e.preventDefault();
	   var slideno = $(this).data('slide');
	   $('.slider-nav').slick('slickGoTo', slideno - 1);
	 });
</script>



<%@ include file="views/common/footer.jsp"%>