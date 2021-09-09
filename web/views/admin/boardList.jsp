<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.board.model.vo.Board"%>
<%
List<Board> list = (List<Board>) request.getAttribute("list");

String searchType = request.getParameter("searchType") == null ? "" : request.getParameter("searchType");
String keyword = request.getParameter("searchKeyword") == null ? "" : request.getParameter("searchKeyword");
/* out.print(searchType + " : " + keyword); */
%>
<%@ include file="/views/common/header.jsp"%>
<style>
.wrapper {
	height: auto;
	min-height: 70%;
	padding-bottom: 60px;
}

#tbl-board {
	width: 80%;
	min-height: 300px;
	margin-left: 10%;
	
	/* position: relative; */
}

.boardlist .aliveboardlist {
	width: 81%;
	height: 80%;
	margin: auto;
	
}

.boardtitle {
	text-align: center;
	height: 50px;
	font-size: 22px;
	font-weight: bolder;
}

.boardtitle p {
	text-align: center;
	font-size: 12px;
	font-weight: lighter;
	margin: 5px 0 0 0;
	padding: 0;
}

.boardtb {
	width: 100%;
	margin-top: 20px;
	line-height: 60px;
	text-align: center;
	font-size: 13px;
	border: 1px solid lightgray;
	/* border-collapse: collapse; */
	background-color: white;
	border-radius: 10px;
	text-decoration : none;
}

div#search-container {
	margin: 30px 0 20px 0;
	text-align: center;
	display: flex;
}

div#search-boardNameSell {
	display: inline-block;
}

div#search-boardNamesold {
	display: none;
}

div#numPerpage-container {
	float: left;
	margin-left: 50px;
}

form#numperPageFrm {
	display: inline;
}

div#pageBar>* {
	margin-right: 20px;
	color: black;
	text-decoration: none;
	color: black;
}

.searchboard {
	height: 38px;
	width: 80px;
	border: none;
	/* margin-right: 10px; */
	font-size: 13px;
	/* margin-left: 10px; */
	/* display: inline-block;  */
}

.searchbtn {
	width: 60px;
	height: 38px;
	border-radius: 10px;
	background-color: #ff9800;
	border: none;
	color: white;
	
}

.searchinput {
	height: 38px;
	border-radius: 10px;
	border: none;
	/* margin-left: 50px; */
}
</style>
<div class="wrapper">
	<section id="tbl-board">

		<div class="boardlist">
			<div class="boardtitle">
				게시글 조회
				<p>당근당근바니바니를 이용하는 회원들의 게시물 관리 해주세요!</p>
			</div>
		</div>


		<div id="search-container">
			<div style="margin: initial; margin-left: auto;">
				<select id="isSell" name="isSell" class="searchboard"
					onchange="if(this.value)location.href=(this.value);">
					<option value="<%=request.getContextPath()%>/boardListPage">판매여부</option>
					<option value="<%=request.getContextPath()%>/adminCheckSell">판매중</option>
					<option value="<%=request.getContextPath()%>/adminCheckSold">판매완료</option>

				</select>
			</div>
			<div id="search-boardName" style="margin-right: auto; margin-bottom:20px;">
				<form action="<%=request.getContextPath()%>/searchBoardList">
					<input type="hidden" name="searchType" value="B_TITLE"> <input
						type="text" name="searchKeyword" size="25"
						placeholder="검색할 상품이름을 입력하세요"
						value='<%=searchType.equals("boardTitle") ? keyword : ""%>'
						class="searchinput">
					<button type="submit" class="searchbtn">검색</button>
				</form>
			</div>
		</div>
		<div class="aliveboardlist">
			<table class="boardtb">
				<thead>
					<tr>
						<th>판매여부</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
					<%
					if (list.isEmpty()) {
					%>
					<tr>
						<td colspan="3" align="center">검색결과가 없습니다.</td>
					</tr>
					<%
					} else {
					%>
					<%
					for (Board b : list) {
					%>
					<tr>
						<%
						if (b.getBoardIsSell() == 1) {
						%>
						<td>판매중</td>
						<%
						} else {
						%>
						<td>판매완료</td>
						<%
						}
						%>
						<td ><a style="text-decoration : none; color: #d2691e;"
							href="<%=request.getContextPath()%>/board/boardView?cPage=<%=request.getAttribute("cPage")%>&no=<%=b.getBoardNumber()%>"><%=b.getBoardTitle()%></a></td>
						<td><%=b.getBoardWriter()%></td>
						<td><%=b.getBoardDate()%></td>
					</tr>
					<%
					}
					}
					%>
				</tbody>
			</table>
			<div id="pageBar" align="center" style="margin-top: 40px; margin-bottom:40px; color:black; ">
				<%=request.getAttribute("pageBar")%>
				<!-- memberlistservlet에서 보낸 pagebar를 받아서 사용. -->
			</div>
		</div>
	</section>
</div>
<script>
	$("#searchType").change(e=>{
		location.assign('<%=request.getContextPath()%>/searchBoardList?searchType='+$(e.target).val());
	})
	
	$(function(){   		
    		$("#searchType").change();
    })    	
</script>





<%@ include file="/views/common/footer.jsp"%>