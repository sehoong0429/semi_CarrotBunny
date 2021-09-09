<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>
<%@ page import="com.member.model.vo.Member"%>
<%
	/*out.print((int)request.getAttribute("user"));*/
%>
<style>
.wrapper {
	height: auto;
	min-height: 70%;
	padding-bottom: 40px;
	margin-top:50px;
}

section#notice-container {
	width: 600px;
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
	padding: 5px 0 5px 10px;
	text-align: left;
}

.bwb {
	width: 70px;
	margin-right: 10px;
	height: 38px;
	border-radius: 10px;
	background-color: #ff9800;
	border: none;
	color: white;
	font-family: 'RIDIBatang';
	"
}
</style>
<div class="wrapper">
	<div id="notice-container">
		<form
			action="<%=request.getContextPath() %>/board/boardWriteEnd?user=<%=request.getAttribute("user")%>"
			method="post" enctype="multipart/form-data">
			<table id="tbl-notice">
				<tr>
					<th>첨부파일</th>
					<td><input class="bfb" type="file" name="boardFilepath1">

					</td>
				</tr>
				<tr>
					<th>상품명</th>
					<td><input type="text" name="boardTitle" id="noticeTitle"
						required></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="boardWriter" id="noticeWrite"
						value="<%=loginMember.getUserId()%>" readonly></td>
				</tr>
				<tr>
					<th>상품가격</th>
					<td><input type="text" name="boardPrice" id="noticePrice"
						required>원</td>
				</tr>
				<tr>
					<th>가격협의 가능</th>
					<td><input type="checkbox" name="boardIsNego" value="가능"
						id="isNego"><label for="isNego">가능</label></td>
				</tr>
				<tr>
					<th>수량</th>
					<td><input type="text" name="boardAmount" id="noticeAmount"
						required>개</td>
				</tr>
				<tr>
					<th>내 용</th>
					<td><textarea rows="5" cols="50" name="boardContent"></textarea></td>
				</tr>
				<tr>
					<th colspan="2"><input class="bwb" type="submit" value="등록하기"
						onclick=""></th>
				</tr>
			</table>
		</form>
	</div>
</div>



<%@ include file="../common/footer.jsp"%>