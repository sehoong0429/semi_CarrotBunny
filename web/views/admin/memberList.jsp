<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%
List<Member> list = (List<Member>) request.getAttribute("list");

String searchType = request.getParameter("searchType") == null ? "" : request.getParameter("searchType");
String keyword = request.getParameter("searchKeyword") == null ? "" : request.getParameter("searchKeyword");
/* out.print(searchType + " : " + keyword); */
%>
<%@ include file="/views/common/header.jsp"%>
<style>

.wrapper {
	height : auto;
	min-height:70%;
	padding-bottom:60px;
}

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
	margin: 30px 0 20px 0;
	padding: 3px;
	text-align: center;
}

.searchType{
    height: 38px;
    width: 80px;
    border: none;
    margin-right: 10px;
    font-size: 13px;
	margin-left:10px;
}

.searchbtn{
 	width: 60px;
    height: 38px;
    border-radius: 10px;
    background-color: #ff9800;
    border: none;
    color: white; 



}


.searchinputid{
    height: 38px;
    border-radius: 10px;
	border : none;
	margin-left:50px;


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
	margin-right: 20px;
	text-decoration : none;
	color: black;
}

</style>

<div class="wrapper">
	<section id="tbl-member">
	
		<div class="members">
			<div class="membertitle">
				회원조회
				<p>당근당근바니바니를 이용하는 회원들을 관리 해주세요!</p>
			</div>
			<div id="search-container">
				<select id="searchType" class="searchType">
					<option value="userId"
						<%=searchType.equals("userId") ? "selected" : ""%>>아이디</option>
					<option value="userName"
						<%=searchType.equals("userName") ? "selected" : ""%>>회원이름</option>
				</select>
				<div id="search-userId">
					<form action="<%=request.getContextPath()%>/admin/searchMember">
						<input type="hidden" name="searchType" value="member_id"> <input
							type="text" name="searchKeyword" size="30"
							placeholder="검색할 아이디를 입력하세요" class="searchinputid"
							value='<%=searchType.equals("UserId") ? keyword : ""%>'>
						<button type="submit" class="searchbtn">검색</button>
					</form>
				</div>
				<div id="search-userName">
					<form action="<%=request.getContextPath()%>/admin/searchMember">
						<input type="hidden" name="searchType" value="mem_name"> <input
							type="text" name="searchKeyword" size="30"
							placeholder="검색할 이름을 입력하세요" class="searchinputid"
							value='<%=searchType.equals("UserName") ? keyword : ""%>'>
						<button type="submit" class="searchbtn">검색</button>
					</form>
				</div>
			</div>
	
			<!-- 회원 리스트 -->
			<div class="memberlist" >
				<table class="membertb" >
					<thead>
						<tr>
							<th>회원번호</th>
							<th>아이디</th>
							<th>이름</th>
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
						for (Member m : list) {
						%>
						<tr>
							<td><%=m.getmemberNum()%></td>
							<td><a style="text-decoration : none; color: #d2691e;"
								href="<%=request.getContextPath()%>/memberlistDetail?memberNum=<%=m.getmemberNum()%>"><%=m.getUserId()%>
							</a></td>
							<td><%=m.getUserName()%></td>
						</tr>
						<%
						}
						}
						%>
					</tbody>
				</table>
	
				<div class= "pageBar" id="pageBar" align="center" style="margin-top: 40px; margin-bottom:40px; ">
					<%=request.getAttribute("pageBar")%>
				</div>
			</div>
		</div>
	</section>
</div>
<script>
    	$("#searchType").change(e => {
    		const userId=$("#search-userId");
    		const userName=$("#search-userName");
    		const value=$(e.target).val();//userId OR userName 
    		
    		userId.css("display","none");
    		userName.css("display","none");
    		
    		$("#search-"+value).css("display","inline-block");
    		
    	});
    	$(function(){   		
    		$("#searchType").change();
    	}) 

	</script>




<%@ include file="/views/common/footer.jsp"%>