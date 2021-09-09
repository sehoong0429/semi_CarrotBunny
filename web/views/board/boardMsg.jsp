<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.board.model.vo.Board"
%>
<%
	String msg=(String)request.getAttribute("msg"); //로그인서블릿에서 넣어준 데이터를 여기서 불러온다. 
	String script=(String)request.getAttribute("script");
	
	Board b = (Board)request.getAttribute("board");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시스템메세지</title>
</head>
<body>
	<script>
		alert('<%=msg%>');
		/* 변수는'' ""로 감싸기  */
		//페이지 닫아 주기 
		<%=script!=null?script:""%>
		<%if((int)request.getAttribute("user") == 1) { %>
			location.replace('<%=request.getContextPath()%>/board/boardPageUser?user=<%=request.getAttribute("user")%>&love=<%=request.getAttribute("love")%>&searchType=<%=request.getAttribute("searchType")%>&keyword=<%=request.getAttribute("keyword")%>&sold=<%=request.getAttribute("sold")%>&cPage=<%=request.getAttribute("cPage")%>&no=<%=request.getAttribute("no")%>');
		<%} else if ((int)request.getAttribute("love") == 1) {%>
			location.replace('<%=request.getContextPath()%>/love/lovePage?user=<%=request.getAttribute("user")%>&love=<%=request.getAttribute("love")%>&searchType=<%=request.getAttribute("searchType")%>&keyword=<%=request.getAttribute("keyword")%>&sold=<%=request.getAttribute("sold")%>&cPage=<%=request.getAttribute("cPage")%>&no=<%=request.getAttribute("no")%>');
		<%} else{ %>
		//페이지전환하는 로직구성
			location.replace('<%=request.getContextPath()%>/board/boardPage?user=<%=request.getAttribute("user")%>&love=<%=request.getAttribute("love")%>&searchType=<%=request.getAttribute("searchType")%>&keyword=<%=request.getAttribute("keyword")%>&sold=<%=request.getAttribute("sold")%>&cPage=<%=request.getAttribute("cPage")%>&no=<%=request.getAttribute("no")%>');
		<%} %>
	</script>
</body>
</html>