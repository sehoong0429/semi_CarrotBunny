<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.member.model.vo.Member"%>
<%
Member result = (Member) request.getAttribute("result");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복확인</title>
<style>
div#checkId-container {
	text-align: center;
	padding-top: 50px;
}

span#duplicated {
	color: red;
	font-weight: bolder;
}
</style>

<script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>

</head>
<body>
	<div id="checkId-container">
		<%
		if (result == null) {
		%>
		[<span><%=request.getParameter("userId")%></span>]는 사용가능합니다. <br>
		<br>
		<button type="button" onclick="fn_close();">닫기</button>
		<%
		} else {
		%>
		[<span id="duplicated"><%=request.getParameter("userId")%></span>]는
		사용중입니다. <br>
		<br>
		<!-- 아이디 재입력창 구성 -->
		<form action="<%=request.getContextPath()%>/checkDuplicateId"
			method="post">
			<input type="text" name="userId" id="userId_"> <input
				type="submit" value="중복검사">
		</form>
		<%
		}
		%>
	</div>
	
<script>
		
		$(function(){
			
			var replaceChar = /[~!@\#$%^&*\()\-=+_'\;<>\/.\`:\"\\,\[\]?|{}(\s*)]/gi;
			var replaceNotFullKorean = /[ㄱ-ㅎㅏ-ㅣ가-힣]/gi;
			
			//아이디 특수문자, 한글, 공백 제거
			$("#userId_").on("focusout", function() { 
				var x = $(this).val();
					if (x.length > 0) { 
						if (x.match(replaceChar) || x.match(replaceNotFullKorean)) { 
							x = x.replace(replaceChar, "").replace(replaceNotFullKorean, "").replace(/ /g,""); 
							} 
						$(this).val(x);
						} 
					}).on("keyup", function() { 
						$(this).val($(this).val().replace(replaceChar, "")); 
						});
		});
	
	
		const fn_close=()=>{
			//1. 현재값을 부모창의 userId_에 대입
			const userId='<%=request.getParameter("userId")%>';
			opener.memberEnrollFrm.userId.value=userId;
			opener.memberEnrollFrm.password.focus();
			//윈도우창 닫기
			close();
		}
</script>



</body>
</html>