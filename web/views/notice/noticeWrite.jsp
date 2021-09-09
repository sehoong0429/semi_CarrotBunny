<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>

<style>
/*공지사항 기본 style*/
#wrap {
	width: 100%;
	padding-bottom: 100px;
	min-height: 70%;
}

.noticewrite {
	width: 100%;
	height: 80%;
	margin: auto;
	min-height: 400px;
}

.nwtitle {
	text-align: center;
	height: 50px;
	font-size: 22px;
	font-weight: bolder;
	padding-bottom: 20px;
}

.nwtitle p {
	text-align: center;
	font-size: 12px;
	font-weight: lighter;
	margin: 5px 0 0 0;
	padding: 0;
}


.nwwrite {
	width : 100%;	
}

.nform{
	width: 80%;
    margin: auto;
}

.writetb{
	width: 80%;
	margin: auto;

}

.writetb input{
	width: 500px; 
	height: 30px; 
	margin-left: 12px;	
	border-radius: 10px;
	margin-top:10px;
  
}

.writetb textarea{
	width: 500px; 
	height: 320px; 
	margin-left: 12px;
	border-radius: 10px;
	margin-top : 20px;
}


.boardbtn{
	margin-top:20px;
	width: 100px;
    height: 50px;
    border-radius: 10px;
    background-color: #ff9800;
    border: none;
    color: white; 
	font-family: 'RIDIBatang';

}

.nwt {
	margin : auto;
}

</style>

<script>
	
</script>
<div id="wrap">

	<div class="noticewrite">
		<!-- 공지사항 제일 윗 부분 -->
		<div class="nwtitle">
			공지사항 등록
			<p>회원에게 보여줄 공지를 등록해주세요!</p>
		</div>



		<div class="nwwrite">
			<form action="<%=request.getContextPath()%>/notice/noticeWriteEnd" class="nform"
				method="post">

				<div class="writetb">
					<table class="nwt">
						<tr>
							<th>제목</th>
							<td><input type="text" name="n_title"
								placeholder="제목을 입력해주세요"
								></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea type="text" name="n_content"
									placeholder="내용을 입력해주세요."></textarea>
							</td>
						</tr>
					</table>
				</div>
				<div
					style="text-align: center; padding-bottom: 40px; padding-top: 10px;">
					<input class="boardbtn" type="submit" value="등록" >
					<input class="boardbtn" type="button" value="목록" 
						onclick="location.assign('<%=request.getContextPath()%>/noticePage')">
				</div>
			</form>
		</div>

	</div>



</div>




<%@ include file="../common/footer.jsp"%>