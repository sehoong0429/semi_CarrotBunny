<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg=(String)request.getAttribute("msg"); //로그인서블릿에서 넣어준 데이터를 여기서 불러온다. 
	String script=(String)request.getAttribute("script");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시스템메세지</title>
</head>
<body>
	<script>
		
		/* 변수는'' ""로 감싸기  */
		//페이지 닫아 주기 
		<%=script!=null?script:""%>
		//페이지전환하는 로직구성
		location.replace('<%=request.getContextPath()%>/board/boardView?cPage=<%=request.getAttribute("cPage")%>&no=<%=request.getAttribute("no")%>');
	</script>
</body>
</html>