<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List,com.board.model.vo.Board"%>
<%
List<Board> list = (List<Board>) request.getAttribute("list");
List<Board> allUserList = (List<Board>) request.getAttribute("allUserList");

int selling = 0;
int sold = 0;
for (Board b : allUserList) {
	if (b.getBoardIsSell() == 1) {
		selling += 1;
	} else {
		sold += 1;
	}
}
%>

<%@ include file="../common/header.jsp"%>
<style>
.wrapper {
	height: 100%;
	min-height: 80%;
	padding-bottom: 0;
}

section#notice-container {
	width: 600px;
	margin: 0 auto;
	margin-bottom: 10px;
	text-align: center;
	height: 400px;
}

section#notice-container h2 {
	margin: 10px 0;
}

table#tbl-notice {
	width: 100%;
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse;
}

table#tbl-notice th, table#tbl-notice td {
	border: 1px solid;
	padding: 5px 0;
	text-align: center;
}

table#tbl-notice {
	width: 100%;
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse;
	clear: both;
}

/* 멤버에서 가져옴*/
#tbl-member {
	width: 100%;
	min-height: 300px;
	/* position: relative; */
}

.members .memberlist {
	width: 81%;
	height: 80%;
	margin: auto;
	
}

.membertitle {
	text-align: center;
	height: 50px;
	font-size: 22px;
	font-weight: bolder;
}

.membertitle p {
	text-align: center;
	font-size: 12px;
	font-weight: lighter;
	margin: 5px 0 0 0;
	padding: 0;
}

.membertb {
	width: 100%;
	margin-top: 20px;
	line-height: 60px;
	text-align: center;
	font-size: 15px;
	border: 1px solid lightgray;
	/*border-collapse: collapse; */
	background-color: white;
	border-radius: 10px;
}

div#search-container {
	text-align: center;
}

.searchType {
	height: 38px;
	width: 80px;
	border: none;
	margin-right: 10px;
	font-size: 13px;
	margin-left: 10px;
}

.searchbtn {
	width: 60px;
	height: 38px;
	border-radius: 10px;
	background-color: #ff9800;
	border: none;
	color: white;
}

.searchinputid {
	height: 38px;
	border-radius: 10px;
	border: none;
	margin-left: 50px;
}

div#search-userId {
	display: inline-block;
}

div#search-userName {
	display: none;
}

div#numPerpage-container {
	float: right;
}

form#numperPageFrm {
	display: inline;
}

div#pageBar>* {
	
	margin-right: 10px;
	text-decoration: none;
	color:black;
}
 
.writebutton {
	width: 90px;
	height: 38px;
	border-radius: 20px;
	background-color: #ff9800;
	border: none;
	color: white;
	font-family: 'RIDIBatang';
}

.search-container {
	align-items: center;
}

.tbl-member a {
	text-decoration: none;
	color: #ff8c00;
	
}

.buttonbox {
	display:block;
	text-align: right;
    margin-right: 10%;
    margin-bottom: 10px;
}



</style>
<div class="wrapper">

	<section id="tbl-member" class="tbl-member">
		<div class="members">
			<div class="membertitle">나의 판매상품</div>
			<div id="search-container" class="search-container">
				<div><p>
					판매중
					<%=selling %>
					/ 판매완료
					<%=sold %></p></div>
				<div class="buttonbox"><input class="writebutton" type="button" value="글쓰기" id="btn-add"
					onclick="fn_noticeWrite();"></div>
			</div>
			<!-- 회원 리스트 -->
			<div class="memberlist">
				<table class="membertb">
					<thead>
						<tr>
							<th>판매여부</th>
							<th>상품명</th>
							<th>수량</th>
							<th>상품가격</th>
						</tr>
					</thead>
					<tbody>
						<%
						if (list.isEmpty()) {
						%>
						<tr>
							<td colspan="5">조회된 등록상품이 없습니다.</td>
						</tr>
						<%
						} else {
						%>
						<%
						for (Board b : list) {
							if (b.getBoardIsDelete() == 0) {
						%>
						<tr>
							<%if (b.getBoardIsSell() == 1) { %>
							<td>판매중</td>
							<%} else { %>
							<td>판매완료</td>
							<%} %>
							<td ><a 
								href="<%=request.getContextPath()%>/board/boardView?user=<%=request.getAttribute("user")%>&cPage=<%=request.getAttribute("cPage")%>&no=<%=b.getBoardNumber()%>">
									<%=b.getBoardTitle()%>
							</a></td>
							<td><%=b.getBoardAmount() %></td>
							<td><%=b.getBoardPrice() %></td>
						</tr>
						<%
						}
						}
						}%>
					</tbody>
				</table>

				<div id="pageBar" class="pageBar" align="center" style="text-align: center; letter-spacing: 10px; font-size: 16px; margin-top:40px; color:black;">
					<%=request.getAttribute("pageBar")%>
				</div>
			</div>
		</div>
	</section>
</div>
<script>
		const fn_noticeWrite=()=>{
			location.assign("<%=request.getContextPath()%>/board/boardWrite?user=1");		
		}
</script>

<%@ include file="../common/footer.jsp"%>