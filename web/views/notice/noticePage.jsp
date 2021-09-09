<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.notice.model.vo.Notice"%>
<%
	List<Notice> list =(List<Notice>)request.getAttribute("list");
%>
<%@ include file="../common/header.jsp"%>

<style>
#wrap {
	width: 100%;
	min-height: 600px;
	/* position: relative; */
}

.notice .noticeList {
	width: 81%;
	height: 80%;
	margin: auto;
	min-height: 600px;
}

.noticetable{
	margin: auto;
    width: 80%;
}


.noticetitle {
	text-align: center;
	height: 50px;
	font-size: 22px;
	font-weight: bolder;
}

.noticetitle p {
	text-align: center;
	font-size: 12px;
	font-weight: lighter;
	margin: 25px 0 0 0;
}

.noticetb {
	width: 100%;
	margin-top: 90px;
	line-height: 50px;
	text-align: center;
	font-size: 15px;
	border: 1px solid lightgray;
	background-color: white;
	border-radius: 10px;
}

.noticetb th, td {
	border-bottom: 1px solid lightgray;
}

.noticewrite {
	display : flex;
	margin :auto;
	margin-top: 4%;
	width: 92px;
	height: 40px;
	border-radius: 10px;
	background-color: #ff9800;
	border: none;
	color: white;
	font-family: 'RIDIBatang';
}

.nwrite {
	width : 100%;
}


</style>
<div id="wrap">

	<div class="notice">
		<!--공지사항 제목-->
		<div class="noticetitle">
			공지사항
			<p>당근당근바니바니를 이용하기 전 꼭 읽어주세요!</p>
		</div>

		<!-- 공지사항 리스트 -->
		<div class="noticeList">
			<div class="noticetable">
				<table class="noticetb">
					<tr>
						<th>제목</th>
						<th>작성일</th>
					</tr>
					<%if(list.isEmpty()){ %>
					<tr>
						<td colspan="2">조회된 공지사항이 없습니다.</td>
					</tr>
					<%}else{ 
				for(Notice n : list){%>
					<tr>
						<td><a style="text-decoration: none; color:black;"
							href="<%=request.getContextPath()%>/notice/noticeView?noticeNo=<%=n.getNoticeNo()%>"><%=n.getNoticeTitle() %></a>
						</td>
						<td><%=n.getNoticeDate() %></td>
					</tr>
					<%}
			}%>
				</table>
			</div>
			<%if(loginMember!=null&&loginMember.getUserId().equals("admin")){ %>
			<div class="nwrite">
				<input class="noticewrite" type="button" value="공지사항 등록"
					onclick="location.assign('<%=request.getContextPath()%>/noticewrite')">
			</div>
			<%} %>
		</div>
	</div>
</div>


<%@ include file="../common/footer.jsp"%>