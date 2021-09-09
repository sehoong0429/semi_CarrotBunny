<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.board.model.vo.Board"%>


<% 
	Board b=(Board)request.getAttribute("board");
	
	Member m = (Member)session.getAttribute("loginMember");
	/*out.print(b.getBoardNumber());*/
%>

<%@ include file="../common/header.jsp"%>

<style>
.wrapper {
	height: auto;
	min-height: 70%;
	padding-bottom: 60px;
	margin-top: 50px;
}

section#notice-container {
	width: 800px;
	margin: 0 auto;
	text-align: center;
}

section#notice-container h2 {
	margin: 10px 0;
}

table#tbl-notice {
	width: 500px;
	margin: 0 auto;
	clear: both;
}

table#tbl-notice th {
	width: 125px;
	padding: 5px 0;
	text-align: center;
}

table#tbl-notice td {
	padding: 5px 0 15px 10px;
	text-align: left;
}
</style>
<div class="wrapper">
	<div id="notice-container">
		<form
			action="<%=request.getContextPath() %>/board/boardUpdateEnd?user=<%=request.getAttribute("user")%>&love=<%=request.getAttribute("love")%>&searchType=<%=request.getAttribute("searchType")%>&keyword=<%=request.getAttribute("keyword")%>&sold=<%=request.getAttribute("sold")%>&cPage=<%=request.getAttribute("cPage")%>&no=<%=b.getBoardNumber()%>"
			method="post" enctype="multipart/form-data">
			<table id="tbl-notice">
				<tr>
					<th>첨부파일</th>
					<td><input type="file" name="up_file"> <%if(b.getBoardFilePath() != null){ %>
						<input type="hidden" name="boardFilepath1"
						value="<%=b.getBoardFilePath()%>"> <span id="fileName"><%=b.getBoardReFilePath() %></span>
						<%}  %></td>
				</tr>
				<tr>
					<th>상품명</th>
					<td><input type="text" name="boardTitle" id="noticeTitle"
						value="<%=b.getBoardTitle() %>" required></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="boardWriter" id="noticeWrite"
						value="<%=loginMember.getUserId()%>" readonly></td>
				</tr>
				<tr>
					<th>상품가격</th>
					<td><input type="text" name="boardPrice" id="noticePrice"
						value="<%=b.getBoardPrice() %>" required>원</td>
				</tr>
				<tr>
					<th>가격협의</th>
					<td>
						<%if (b.getBoardIsNego() == 1) { %> <input type="checkbox"
						name="boardIsNego" value="가능" id="isNego" checked><label
						for="isNego">가능</label> <%} else { %> <input type="checkbox"
						name="boardIsNego" value="가능" id="isNego"><label
						for="isNego">가능</label> <%} %>
					</td>
				</tr>
				<tr>
					<th>수량</th>
					<td><input type="text" name="boardAmount" id="noticeAmount"
						value="<%=b.getBoardAmount() %>" required>개</td>
				</tr>
				<tr>
					<th>내 용</th>
					<td><textarea style="resize:none; height:200px;"rows="5" cols="50" name="boardContent"><%=b.getBoardContent() %></textarea></td>
				</tr>
				<tr>
					<th colspan="2"><input
						style="width: 60px; height: 38px; border-radius: 10px; background-color: #ff9800; border: none; color: white;"
						type="submit" value="수정하기" onclick=""></th>
				</tr>
			</table>
		</form>
	</div>
</div>
<script>
   	$(function(){
   		$("input[name=up_file]").change(e => {
   			if($(e.target).val()==""){
   				$("#fname").show();
   			}else{
   				$("#fname").hide();
   			}
   		});
   	});
</script>


<%@ include file="../common/footer.jsp"%>