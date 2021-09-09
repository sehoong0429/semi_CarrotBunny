<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List,com.board.model.vo.Board"%>
<%
List<Board> list = (List<Board>) request.getAttribute("list");

%>

<%@ include file="../common/header.jsp"%>
<style>
section#notice-container {
	width: 600px;
	margin: 0 auto;
	text-align: center;
}

section#notice-container h2 {
	margin: 10px 0;
}

table#tbl-notice th, table#tbl-notice td {
	padding: 5px 0;
	text-align: center;
}

input#btn-add {
	float: right;
	margin: 0 0 15px;
}

table#tbl-notice {
	width: 100%;
	margin-top: 20px;
	line-height: 60px;
	text-align: center;
	font-size: 15px;
	border: 1px solid lightgray;
	/*border-collapse: collapse; */
	background-color: white;
	border-radius: 10px;
	margin-bottom: 30px;
}

p#heart {
	margin: 0 auto;
	font-size: 58px;
}

div#pageBar>* {
	margin-right: 10px; text-decoration : none;
	color: black;
	text-decoration: none;
}
</style>

<div class="wrapper">
	<section id="notice-container">
		<div>
			<h2 style="margin-bottom: 20px;">나의 관심목록</h2>
			<p id="heart">🧡</p>
		</div>
		<div>
			<table id="tbl-notice">
				<tr>
					<th>판매여부</th>
					<th>상품명</th>
				</tr>
				<%
				if (list.isEmpty()) {
				%>
				<tr>
					<td colspan="5">조회된 관심목록이 없습니다.</td>
				</tr>
				<%
				} else {
				for (Board b : list) {
					if (b.getBoardIsDelete() == 0) {
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
					<td><a style="text-decoration: none; color: #FF8C00;"
						href="<%=request.getContextPath()%>/board/boardView?love=<%=request.getAttribute("love")%>&cPage=<%=request.getAttribute("cPage")%>&no=<%=b.getBoardNumber()%>">
							<%=b.getBoardTitle()%>
					</a></td>
				</tr>
				<%
				}
				}
				}
				%>
			</table>
		</div>

		<div id="pageBar" class="pageBar">
			<%=request.getAttribute("pageBar")%>
		</div>
	</section>
</div>

<%@ include file="../common/footer.jsp"%>