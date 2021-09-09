<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <style>
 	.footer_line {
 		position :relative;
 		left:0;
 		width:100%;
 		transform:translateY(-100%);
 		padding-bottom: 0px;
 		padding-top : 10px;
 		
 	}
 
 </style>

    <footer class="footer_line">
        <p>&emsp;@2021당근당근바니바니&nbsp;&nbsp;</p>
        <a href="<%=request.getContextPath()%>/noticePage" style="text-decoration:none; color : #646464;">공지</a>
        <ul class="footer_icon">  
            <a href="" ><img class="facebook" src="<%=request.getContextPath()%>/images/facebook.png"></a>&emsp;</img>
            <a href=""><img class="instagram" src="<%=request.getContextPath()%>/images/instagram.png"></a>&emsp;</img>
            <a href=""><img class="youtube" src="<%=request.getContextPath()%>/images/Youtube.png"></a></img>
        </ul>
        <p>만든이 : 홍서연&emsp;</p>
    </footer>

</body>
</html>