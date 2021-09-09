<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.qna.model.vo.Qna"%>
<%
Qna q = (Qna) request.getAttribute("qna");
%>
<%@ include file="../common/header.jsp"%>

<style>
.wrapper {
	height: auto;
	min-height: 65%;
	padding-bottom: 60px;
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

textarea {
	width: 88%;
	height: auto;
}

.noticetitle {
	text-align: center;
	height: 50px;
	font-size: 22px;
	font-weight: bolder;
}

.qwb{
	margin-right:10px; 
	height: 38px; 
	border-radius: 10px; 
	background-color: #ff9800; 
	border: none; 
	color: white; 
	font-family: 'RIDIBatang';"
}

.qwform {

}

.qnaTitle {
	width: 80%;
	min-height: 30px; 
	border-radius: 10px; 
	border-width: 0.5px;
}

.qnaContent {
	width: 80%;
	min-height: 200px; 
	border-radius: 10px; 
	border-width: 0.5px;
}

	
</style>
<div class="wrapper">
	<div id="notice-container">
		<div class="noticetitle">
			<p>1:1 문의 등록</p>
		</div>
		<div class="qwform">
			<form name="qnaWrite"
				action="<%=request.getContextPath()%>/qna/qnaWriteEnd" method="post">
				<table id="tbl-notice">
					<tr>
						<th>문의 제목</th>
						<td><input name="qnaTitle" id="qnaTitle" class="qnaTitle" placeholder="문의 제목을 작성해주세요." required></input>
					</tr>
					<tr>
						<th>문의 내용</th>
						<td><input name="qnaContent" id="qnaContent" placeholder="문의 할 내용을 작성해주세요." class="qnaContent" required></input></td>
					</tr>
					<tr>
						<th colspan="2"><input class="qwb" type="submit" value="문의 등록하기"></th>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>

<%@ include file="../common/footer.jsp"%>