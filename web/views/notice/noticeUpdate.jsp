<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.notice.model.vo.Notice"%>
<%
Notice n = (Notice) request.getAttribute("notice");
%>
<%@ include file="../common/header.jsp"%>

<style>
.wrap {
	witth: 100%;
	height: 50%;
	min-height: 70%;
	padding-bottom: 60px;
}

.noticeupdate {
	width: 100%;
	height: 100%;
}

.noticetitle {
	text-align: center;
	height: 50px;
	font-size: 22px;
	font-weight: bolder;
	padding-bottom: 20px;
}

.noticetitle p {
	text-align: center;
	font-size: 12px;
	font-weight: lighter;
	margin: 5px 0 0 0;
	padding: 0;
}

section#notice-container {
	width: 600px;
	/* margin: 0 auto; */
	text-align: center;
}




table#tbl-notice {
	width: 500px;
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse;
	clear: both;
}

table#tbl-notice th {
	width: 125px;
	border: 1px solid;
	padding: 5px 0;
	text-align: center;
}

table#tbl-notice td {
	border: 1px solid;
	padding: 5px 0 5px 10px;
	text-align: left;
}


.nobtn {
	width: 80px;
	height: 40px;
	border-radius: 10px;
	background-color: #ff9800;
	border: none;
	color: white;
	
}

.nup {
}

.noticeupdate {
	display :flex;
}

.noticeupdate table {
	margin:auto;
}

.noticeupdate textarea {
	height: 320px;
	margin-left: 15px; 
	border-radius: 10px; 
	margin-top: 20px; 
	font-size: 14px; 
	resize: none;
    width: 100%;"
}


</style>

<div class="wrap">
	<div id="noticeupdate">
		<div class="noticetitle">
			공지사항 수정
			<p>공지사항을 수정해주세요!</p>
		</div>

		<div class="nup">
			<form action="<%=request.getContextPath()%>/notice/noticeUpdateEnd"
				method="post">

				<input type="hidden" name="noticeNo" value="<%=n.getNoticeNo()%>">


				<div class="noticeupdate">

					<table>
						<tr>
							<th style="padding-right: 15px; padding-top: 17px;">제 목</th>
							<td><input
								style="width: 500px; height: 30px; margin-left: 15px; border-radius: 10px; margin-top: 20px; font-size: 14px;"
								type="text" name="n_title" id="noticeTitle"
								value="<%=n.getNoticeTitle()%>" required></td>
						</tr>
						<tr>
							<th style="padding-right: 15px;">내 용</th>
							<td><textarea name="n_content"><%=n.getNoticeContent()%></textarea></td>
						</tr>
					</table>
				</div>
				<div style="padding-top: 20px; text-align: center;">
					<input class="nobtn" type="submit" value="등록하기" onclick="">
				</div>

			</form>
		</div>
	</div>

</div>



<%@ include file="../common/footer.jsp"%>