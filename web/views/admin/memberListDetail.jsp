<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%
Member m = (Member) request.getAttribute("member");
%>
<%@ include file="/views/common/header.jsp"%>
<style>

.wrapper {
	height : auto;
	min-height:40%;
	padding-bottom:30px;
}

#tbl-member {
	width: 100%;
	min-height: 200px;
	/* position: relative; */
}

.members .memberlist {
	width: 81%;
	height: 80%;
	margin: auto;
	min-height: 500px;
}

.membertitle {
	text-align: center;
	height: 50px;
	font-size: 22px;
	font-weight: bolder;
}

.membertb p {
	text-align: center;
	font-size: 12px;
	font-weight: lighter;
	margin: 5px 0 0 0;
	padding: 0;
}

.membertb {
	width: 50%;
	margin:auto;
	margin-top: 30px;
	line-height: 70px;
	text-align: center;
	font-size: 13px;
	margin-top:30px;
	border: 1px solid lightgray;
	border-radius:10px;
	background-color: white;
	border-radius: 5px;
}

.memberlistbtn{
	text-align:center;
	margin-top : 10px;

}

.memberlistbtn input {
	width: 130px; 
	height: 30px; 
	margin-top: 20px; 
	background-color: #ff9800; 
	color: white; 
	border: none; 
	border-radius: 10px;
	font-family: 'RIDIBatang';
}
</style>

<div class="wrapper">
<div id="tbl-member">

	<div class="members">
		<div class="membertitle">회원상세</div>

		<!-- 회원 리스트 -->
		<div class="memberlist">
			<table class="membertb">
				<p align="center">관리자는 비매너 회원, 부적절한 게시물을 올린 회원을 삭제 할 수 있습니다.</p>
				<!-- <thead> -->
				<tr>
					<th>회원번호</th>
					<td><%=m.getmemberNum()%></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><%=m.getUserName()%></td>
				</tr>
				<tr>
					<th>핸드폰번호</th>
					<td><%=m.getPhone()%></td>
				</tr>
				<tr>
					<th>가입일자</th>
					<td><%=m.getenrollDate()%></td>
				</tr>
			</table>
			<div class ="memberlistbtn">
				<input type="button" value="목록"
					style="width: 50px; height: 30px;"
					onclick="location.assign('<%=request.getContextPath()%>/memberList')"></input>
				<input type="button" value="삭제" style="width: 50px; height: 30px;"
					onclick="fn_delete_member();"> 
			</div>
		</div>
	</div>
</div>

</div>
	<script>
	
	const fn_delete_member=()=>{
		if(confirm("정말로 삭제하시겠습니까?")){
			location.replace("<%=request.getContextPath()%>/adminupdatemember?memberNum="+'<%=m.getmemberNum()%>');
		}
	}
	</script>




<%@ include file="/views/common/footer.jsp"%>