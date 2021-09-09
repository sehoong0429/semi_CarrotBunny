<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.member.model.vo.Member" %>
<%
	String script=(String)request.getAttribute("script");
	Member loginMember = (Member)session.getAttribute("loginMember");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시스템메세지</title>
</head>
<body>
	<script>
		alert("<%=loginMember.getUserName()%>님 마이페이지로 이동~!"); /* 변수는'' ""로 감싸기  */
		//페이지 닫아 주기 
		<%=script!=null?script:""%>
		//페이지전환하는 로직구성
		location.replace('<%=request.getContextPath()%><%=request.getAttribute("loc")%>');
	</script>
</body>
</html>