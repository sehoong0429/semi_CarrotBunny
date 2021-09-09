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
	min-height: 90%;
	padding-bottom: 60px;
}

section#notice-container {
	width: 600px;
	margin: 0 auto;
	display: block;
	
}

section#notice-container h2 {
	margin: 10px 0;
}

table#tbl-notice {
	margin:auto;
	width: 68%;
	margin-top: 20px;
	line-height: 40px;
	text-align: center;
	font-size: 13px;
	border: 1px lightgray;
	/* border-collapse: collapse; */
	background-color: white;
	border-radius: 10px;
	text-decoration: none;
	margin-top: 20px;
}

table#tbl-notice th {
	min-width: 70px;
	width: 125px;
	padding: 5px 0;
	text-align: center;
}

table#tbl-notice td {
	border: none;
	padding: 5px 0 15px 10px;
	text-align: left;
	border-radius: 10px;
}

textarea {
	width: 88%;
	height: auto;
	resize: none;
	min-height: 70px;
}

.noticetitle {
	text-align: center;
	height: 50px;
	font-size: 22px;
	font-weight: bolder;
}
</style>
<div class="wrapper">

	<%
	if (loginMember != null && loginMember.getUserId().equals("admin")) {
	%>
	<div id="notice-container">
		<div class="noticetitle">
			<p>1:1 문의 상세화면</p>
		</div>
		<div>
			<form name="qnaUpdate"
				action="<%=request.getContextPath()%>/qna/qnaUpdate" method="post">
				<div
					style=" width: 60%; padding-top:30px; margin:auto">
					<table id="tbl-notice">
						<tr>
							<th>제 목</th>
							<td><%=q.getQnaTitle()%></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td><%=q.getQnaWriter()%></td>
						</tr>
						<tr>
							<th>작성 날짜</th>
							<td><%=q.getQnaDate()%></td>
						</tr>
						<tr>
							<th>문의 내용</th>
							<td><p style="border: none;" readonly="readonly"><%=q.getQnaContent()%></p></td>
						</tr>
						<tr >
							<th >답변 내용</th>
							<%
							if (q.getQnaAnswer() == null) {
							%>
							<td ><input type="hidden" name="qnaNo"
								value="<%=q.getQnaNo()%>"> <input style="width:400px; height:100px; border-radius:10px; color: #ff9800;"
									placeholder="문의에 대한 답변이 아직 등록되지 않았습니다." name="answer"
									id="answer"></input><br> </td>
							<%
							} else {
							%>
							<td><input type="hidden" name="qnaNo"
								value="<%=q.getQnaNo()%>"> <textarea
									placeholder="<%=q.getQnaAnswer()%>" name="answer" id="answer"><%=q.getQnaAnswer()%></textarea><br>
								<input type="submit" value="수정하기"></td>
							<%
							}
							%>
						
					</table>
				</div>
				<div style="text-align: center; margin-top:10px;">
					<input style="width: 70px; margin-right:10px; height: 38px; border-radius: 10px; background-color: #ff9800; border: none; color: white; font-family: 'RIDIBatang';" type="submit" 
								value="답변 등록">
					<input
						style="width: 70px; height: 38px; margin-right:10px;  margin-top: 20px; border-radius: 10px; background-color: #ff9800; border: none; color: white; font-family: 'RIDIBatang';"
						type="button" value="문의 목록"
						onclick="location.assign('<%=request.getContextPath()%>/qna.do')">
					<input
						style="width: 70px; height: 38px; border-radius: 10px; background-color: #ff9800; border: none; color: white; font-family: 'RIDIBatang';"
						type="button" value="삭제하기" onclick="fn_delete_qna();">
				</div>
			</form>
		</div>
	</div>
	<%
} else {
%>
	<div id="notice-container">
		<div class="noticetitle">
			<p>1:1 문의 상세화면</p>
		</div>
		<div style=" width: 60%; padding-top:30px; margin:auto">
		<table id="tbl-notice" >
			<tr>
				<th>제 목</th>
				<td><%=q.getQnaTitle()%></td>
			</tr>
			<tr>
				<th>작성 날짜</th>
				<td><%=q.getQnaDate()%></td>
			</tr>
			<tr>
				<th>문의 내용</th>
				<td><textarea readonly="readonly"><%=q.getQnaContent()%></textarea></td>
			</tr>
			<tr>
				<%
				if (q.getQnaAnswer() == null) {
				%>
				<th colspan="2"><p style="color: #ff9800;">
						문의에 대한 답변이 아직 등록되지 않았습니다.
						<p></th>
				<%
				} else {
				%>
				<th>답변 내용</th>
				<td><textarea readonly="readonly"><%=q.getQnaAnswer()%></textarea>
				</td>
				<%
				}
				%>
			</tr>
		</table></div>
		<div style="text-align: center;">
			<input
				style="width: 70px; height: 38px; border-radius: 10px; background-color: #ff9800; border: none; color: white; font-family: 'RIDIBatang';"
				type="button" value="문의 목록"
				onclick="location.assign('<%=request.getContextPath()%>/qna.do')">
		</div>
	</div>
	<%
}
%>

</div>
<script>

const fn_delete_qna=()=>{
	if(confirm("정말로 삭제하시겠습니까?")){
		//삭제 로직 진행
		location.replace("<%=request.getContextPath()%>/qna/qnaDelete?qnaNo=<%=q.getQnaNo()%>");
	}
}

</script>





						<%@ include file="../common/footer.jsp"%>