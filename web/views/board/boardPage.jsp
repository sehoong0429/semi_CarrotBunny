<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List,com.board.model.vo.Board"%>
<%
List<Board> list = (List<Board>) request.getAttribute("list");
String searchType = request.getParameter("searchType") == null ? "" : request.getParameter("searchType");
String keyword = request.getParameter("keyword") == null ? "" : request.getParameter("keyword");
/*out.print(request.getAttribute("sold"));*/
/* out.print(searchType+" : "+keyword); */
%>

<%@ include file="../common/header.jsp"%>




<style>




.wrapper {
	height: auto;
	min-height: 70%;
	
	padding-bottom: 40px;
}

section#notice-container {
	/* width: 600px; */
	margin: 0 auto;
	text-align: center;
}

section#notice-container h2 {
	margin: 10px 0;
}

*, *:before, *:after {
}

h1, h2, h3, h4, ul, ol, li, figure, figcaption, blockquote, dl, dd {
	margin: 0;
}

/* 푸터 괴롭히는놈 */
.new {
	max-width: 100%;
	/* display: block; */
}

a {
	color: inherit;
	font-weight: bold;
	
}

button {
	border: 1px solid;
	background-color: transparent;
}

body {
	font-family: 'RIDIBatang', sans-serif;
}

.link-button {
	display: flex;
	align-items: center;
	background-color: #000;
	border-radius: 0.375rem;
	padding: 0.5em 1.25em;
	color: #fff; i { font-size : 1.25rem;
	margin-left: 0.5rem;
}

}
.logo {
	font-size: 1.25rem;
	font-weight: 700;
}

main {
	padding-top: 0px;
	padding-bottom: 6rem;
}

.grid {
	display: grid;
	grid-template-columns: repeat(3, 1fr);
	gap: 2rem;
}

.grid-column {
	display: flex;
	flex-direction: column; & > * + * {
	margin-top: 4rem;
}

}
.product {
	text-decoration: none;
	font-weight: 400;
}

a.product {
	border-radius: 0.25rem 0.25rem 0 0;
	overflow: hidden;
	max-width: 400px;
	max-height: 600px;
}

.product-action {
	color: #000;
	width: 2.5rem;
	height: 2.5rem;
	border-radius: .25rem;
	font-size: 1.25rem;
	border: none;
	display: flex;
	align-items: center;
	justify-content: center;
	cursor: pointer;
	transition: .15s ease;
	&:
	hover
	{
	background-color
	:
	#ebebeb;
}

}
.product-info {
	display: flex;
	flex-direction: column;
}

.product-title {
	font-size: 1.125rem;
	line-height: 1.25;
}

.product-price {
	margin-top: .25rem;
}

.credits {
	display: flex;
	flex-direction: column;
	justify-content: center;
	text-align: center;
	margin-top: 10rem;
	color: #777;
	font-size: .875rem;
	a
	{
	display
	:
	block;
}

}
@media all and (max-width: 600px) {
	.grid {
		display: grid;
		grid-template-columns: repeat(1, 1fr);
		gap: 1.5rem;
	}
	.grid-column { & > * + * {
		margin-top: 1.5rem;
	}
	
	.*{
	font-family: "RIDIBatang", sans-serif;}
}

}
.writebutton input {
	-moz-transition: all 0.3s ease-in-out;
	-o-transition: all 0.3s ease-in-out;
	-webkit-transition: all 0.3s ease-in-out;
	transition: all 0.3s ease-in-out;
	font-size: 13px;
	line-height: 80%;
	font-family: "montserratbold", sans-serif;
	-moz-border-radius: 26px;
	-webkit-border-radius: 26px;
	border-radius: 26px;
	-moz-box-shadow: rgba(17, 17, 18, 0.1) 0 2px 4px, rgba(19, 20, 20, 0.07)
		0 1px 1px;
	-webkit-box-shadow: rgba(17, 17, 18, 0.1) 0 2px 4px,
		rgba(19, 20, 20, 0.07) 0 1px 1px;
	box-shadow: rgba(17, 17, 18, 0.1) 0 2px 4px, rgba(19, 20, 20, 0.07) 0
		1px 1px;
	background-color: #ff9800;
	color: #fff;
	cursor: pointer;
	display: inline-block;
	padding: 16px 26px;
	border: 0;
	outline: 0;
}

.search-userId button {
	width: 60px;
    height: 38px;
    border-radius: 10px;
    background-color: #ff9800;
    border: none;
    color: white; 
    
   
}

.search-userId input{
    height: 38px;
    border-radius: 10px;
	border : none;
	margin-left:50px;
}


select {
    height: 38px;
    width: 80px;
    border: none;
    margin-right: 10px;
	font-size: 13px;
}

.search-boardName button {
	width: 60px;
    height: 38px;
    border-radius: 10px;
    background-color: #ff9800;
    border: none;
    color: white; 
    
   
}


.search-boardName input{
    height: 38px;
    border-radius: 10px;
	border : none;
	margin-left:50px;
}


</style>







<div class="wrapper">





	<section id="notice-container">
		<h2>상품 게시판</h2>
		<div id="search-container">
			<select id="searchType">
				<option value="userId"
					<%=searchType.equals("userId") ? "selected" : ""%>>아이디</option>
				<option value="boardName"
					<%=searchType.equals("boardName") ? "selected" : ""%>>상품이름</option>
			</select>
			<div id="search-userId" class="search-userId">
				<form action="<%=request.getContextPath()%>/board/boardSearch">
					<input type="hidden" name="searchType" value="B_WRITER"> <input
						type="text" name="keyword" size="25"
						placeholder="검색할 아이디를 입력하세요"
						value='<%=searchType.equals("userId") ? keyword : ""%>'>
					<button type="submit">검색</button>
					<%if((int)request.getAttribute("sold")==1) { %>
						<input name="soldCheck" type="checkbox" value="판매 완료" checked><label for="sold">판매 완료 포함</label>
					<%} else { %>
						<input name="soldCheck" type="checkbox" value="판매 완료"><label for="sold">판매 완료 포함</label>
					<%} %>
				</form>
			</div>
			<div id="search-boardName" class="search-boardName">
				<form action="<%=request.getContextPath()%>/board/boardSearch">
					<input type="hidden" name="searchType" value="B_TITLE"> <input
						type="text" name="keyword" size="25"
						placeholder="검색할 상품이름을 입력하세요"
						value='<%=searchType.equals("boardTitle") ? keyword : ""%>'>
					<button type="submit">검색</button>
					<%if((int)request.getAttribute("sold")==1) { %>
						<input name="soldCheck" type="checkbox" value="판매 완료" checked><label for="sold">판매 완료 포함</label>
					<%} else { %>
						<input name="soldCheck" type="checkbox" value="판매 완료"><label for="sold">판매 완료 포함</label>
					<%} %>
				</form>
			</div>
		</div>
		<div class="writebutton"
			style="text-align: right; margin-right: 60px; margin-bottom: 10px;">
			<input type="button" value="글쓰기" id="btn-add"
				onclick="fn_noticeWrite();"
				style="margin-bottom: 50px; margin-top: 10px; margin-right: 20px;">
		</div>

	</section>




	<main>
		<div class="responsive-container">
			<div class="grid">

				<%
			if (list.isEmpty()) {
			%>
					<div style="text-align:center; font-size:50px;">
					<p > 찾는 상품 없~다! </p>
					</div>
				<%} else {
				
				int i = 0;
				
				for (Board b : list) {
					if (b.getBoardIsDelete() == 0) {
						%>
				<div class="grid-column" style="align: center;">
					<a class="product"
						href="<%=request.getContextPath()%>/board/boardView?searchType=<%=searchType %>&keyword=<%=keyword %>&sold=<%=request.getAttribute("sold") %>&cPage=<%=request.getAttribute("cPage")%>&no=<%=b.getBoardNumber()%>">
						<div class="product-image" style="text-align: center;">
							<img class="new"
								src="<%=request.getContextPath()%>/upload/board/<%=b.getBoardReFilePath()%>" />
						</div>
						<div class="product-content">
							<div class="product-info" style="text-align: center;">
								<h2 class="product-title"><%=b.getBoardTitle()%></h2>
								<p class="product-price"><%=b.getBoardPrice() %>
									원
								</p>
								<input type="hidden"></input> <input type="hidden"></input> <input
									type="hidden"></input> <input type="hidden"></input>
							</div>
							<button class="product-action">
								<i class="material-icons-outlined"></i>
							</button>
						</div>
					</a>
				</div>
				<%i = 0;
				}
				}
				} %>

			</div>
		</div>
		<div id="pageBar"
			style="text-align: center; letter-spacing: 10px; font-size: 19px; margin-top:10px;">
			<%=request.getAttribute("pageBar")%>
		</div>
	</main>


</div>

<script>
		const fn_noticeWrite=()=>{
			location.assign("<%=request.getContextPath()%>/board/boardWrite");			
		}
		$("#searchType").change(e => {
    		const userId=$("#search-userId");
    		const boardName=$("#search-boardName");
    		const value=$(e.target).val();//userId OR userName OR gender
    		
    		userId.css("display","none");
    		boardName.css("display","none");
    		
    		$("#search-"+value).css("display","inline-block");
    		
    	});
    	$(function(){   		
    		$("#searchType").change();
    	})
    	
    	
    	
    	
 
	
    	
    	
    	
    	
</script>

<%@ include file="../common/footer.jsp"%>