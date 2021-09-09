<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ page import="com.member.model.vo.Member"%>
<!--멤버 가져오기-->

<style>
.wrapper {
	height: auto;
	min-height: 68%;
	padding-bottom: 56px;
}

/* myinfo */
.myinfo-container {
	display: flex;
	justify-content: center;
	/* 	align-items: center; */
	min-height: 500px;
}

.myicon {
	display: center;
	height: 100px;
	width: 100px;
	margin-bottom: 0px;
	font-size: 12px;
}

.myinfo {
	text-align: center;
	height: 50px;
	font-size: 22px;
	font-weight: bolder;
}

.myinfotitle p {
	text-align: center;
	font-size: 12px;
	font-weight: lighter;
	margin: 15px 0 10px 0;
	padding: 0;
}

.btn1 {
	width: 90px;
	height: 40px;
	border-radius: 10px;
	background-color: #ff9800;
	border: none;
	color: white;
	margin-top: 10%;
	margin-right:10px;
}

.btn2 {
	width: 100px;
	height: 40px;
	border-radius: 10px;
	background-color: #ff9800;
	border: none;
	color: white;
	margin-top: 10%;
	margin-right:10px;
}

.btn3 {
	width: 90px;
	height: 40px;
	border-radius: 10px;
	background-color: #ff9800;
	border: none;
	color: white;
	margin-top: 15px;

}

.memberFrm input {
	border-radius: 10px;
}

.memberFrm th {
	border-radius: 10px;
	font-size: 15px;
}
</style>
<div class="wrapper">
	<div class="myinfo-container">
		<div class="myinfo"style="margin-top:20px;">
			<!-- 메인 코멘트 영역 -->
			<div class="myinfotitle" style="margin-bottom: 20%;">
				나의 정보
			
				<p>*표시만 수정이 가능합니다</p>
			</div>

			<form id="memberFrm" class="memberFrm" 
				onsubmit="return fn_update_validate();" method="post">
				<table >
					<tr>
						<th>아이디</th>
						<td><input type="text" name="userId" id="userId_"
							placeholder="<%=loginMember.getUserId()%>"
							style="height: 58px; font-size: 15px; border: none; margin-left: 50px;"
							value="<%=loginMember.getUserId()%>" readonly></td>
					</tr>
					<tr>
						<th>이름 *</th>
						<td><input type="text" name="userName" id="userName"
							placeholder="<%=loginMember.getUserName()%>"
							style="height: 58px; font-size: 15px; border: none; margin-left: 50px;"
							value="<%=loginMember.getUserName()%>" required><br>
						</td>
					</tr>
					<tr>
						<th>이메일 *</th>
						<td><input
							style="height: 58px; font-size: 15px; border: none; margin-left: 50px;"
							type="email" placeholder="<%=loginMember.getEmail()%>"
							name="email" id="email" value="<%=loginMember.getEmail()%>"><br>
						</td>
					</tr>
					<tr>
						<th>휴대폰 *</th>
						<td><input type="tel"
							placeholder="<%=loginMember.getPhone()%>" name="phone" id="phone"
							style="height: 58px; font-size: 15px; border: none; margin-left: 50px;"
							maxlength="11" value="<%=loginMember.getPhone()%>"><br></td>
					</tr>
				</table>
				<input class="btn1" type="button" value="정보수정"
					onclick="fn_update_member();" />
				<button class="btn2" type="button" onclick="fn_password_update();">비밀번호변경</button>
				 <input class="btn3" type="button" value="회원탈퇴"
					onclick="fn_delete_member();" />
			</form>

		</div>
	</div>
</div>
<script>
		const fn_password_update=()=>{
			const url="<%=request.getContextPath()%>/updatePassword?userId=<%=loginMember.getUserId()%>";
			const status="left=500px,top=200px,width=400px,height=210px";
			open(url,"_blank",status);
		}
	
	
	
		const fn_delete_member=()=>{
			if(confirm("정말로 탈퇴하시겠습니까?")){
				//탈퇴로직 진행
				location.replace("<%=request.getContextPath()%>/deleteMember?userId=<%=loginMember.getUserId()%>");
				//location.replace("/06_HelloMVC/deleteMember?userId="+'user04')
			}
		}
	
	
	
		const fn_update_member=()=>{
			const frm=$("#memberFrm");
			console.log(frm);
			//form태그에 action속성이 설정되어있지 않아 설정하고 submit함수 호출하면 됨.
			//동적으로 form으로 전송되는 servlet을 변경할 수 있다. -> 동적요청
			frm.attr("action","<%=request.getContextPath()%>/memberupdateend.do");
			frm.submit();
		}
		const fn_update_validate=()=>{
			
			
			
			return true;
		}
		$("#password_2").blur((e)=>{
			const pw=$("#password_").val();
			const pwck=$(e.target).val();
			if(pw!=pwck){
				alert("비밀번호가 일치하지 않습니다.");
				$("#password_").focus();
			}
		});	
		
	</script>







<%@ include file="../common/footer.jsp"%>