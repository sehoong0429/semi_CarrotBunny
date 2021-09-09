<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<style>
section#enroll-container {
	text-align: center;
	margin-bottom: 10%;
}

section#enroll-container input {
	margin: 3px;
}

section#enroll-container table {
	margin: 0 auto;
}

section#enroll-container table th {
	padding: 0 10px;
	text-align: right;
}

section#enroll-container table td {
	padding: 0 10px;
	text-align: left;
}

<!--이용약관 css -->* {
	margin: 0;
	padding: 0;
 	box-sizing: border-box
	border-radius:10px;
}

body {
	background-color: #f7f7f7;
}

ul>li {
	list-style: none
}

a {
	text-decoration: none;
}

.clearfix::after {
	content: "";
	display: block;
	clear: both;
	border-radius:20px;
}

#joinForm {
	width: 460px;
	margin: 0 auto;
	text-align: center;
}

ul.join_box {
	border: 1px solid #ddd;
	background-color: #fff;
}

.checkBox, .checkBox>ul {
	position: relative;
}

.checkBox>ul>li {
	float: left;
}

.checkBox>ul>li:first-child {
	width: 85%;
	padding: 15px;
	font-weight: 600;
	color: #888;
}

.checkBox>ul>li:nth-child(2) {
	position: absolute;
	top: 50%;
	right: 30px;
	margin-top: -12px;
}

.checkBox textarea {
	width: 96%;
	height: 90px;
	margin: 0 2%;
	background-color: #f7f7f7;
	color: #888;
	border: none;
}


.btn1{
	width: 68px; 
	height: 25px; 
	border-radius: 21px; 
	background-color: #ff9800; 
	border: none; 
	color: white; 
	font-family: 'RIDIBatang';
}

.btn2 {
	width: 70px; 
	margin-right:10px; 
	height: 38px; 
	border-radius: 10px; 
	background-color: #ff9800; 
	border: none; 
	color: white; 
	font-family: 'RIDIBatang';
}


</style>

<section id=enroll-container class=enroll-container>
	<h2>회원 가입 정보 입력</h2>
	<form name="memberEnrollFrm"
		action="<%=request.getContextPath()%>/memberenrollend.do"
		method="post">
		<table >
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" placeholder="4글자이상" name="userId" id="userId_"  minlength="4"  required> 
					<input class="btn1" type="button" value="중복검사" onclick="fn_id_duplicate();"></td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td><input type="password" name="password" id="password_" minlength="4" maxlength="12" required><br></td>
			</tr>
			<tr>
				<th>패스워드확인</th>
				<td><input type="password" id="password_2"><br></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="userName" id="userName" required><br>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="email" placeholder="abc@xyz.com" name="email"
					id="email" required><br></td>
			</tr>
			<tr>
				<th >휴대폰</th>
				<td><input  type="tel" placeholder="(-없이)01012345678"
					name="phone" id="phone" maxlength="11"required><br></td>
			</tr>
		</table>
		<div style="margin : 5% 15%;">
			<ul class="join_box" style="padding-inline-start : 1em; padding-inline-end : 1em;">
				<li class="checkBox check02">
					<ul class="clearfix">
						<li>이용약관 동의(필수)</li>
						<li class="checkBtn"><input type="checkbox" name="pointcheck" id="pointcheck" value="0" />
						</li>
					</ul > <textarea name="" id="" readonly="readonly" >여러분을 환영합니다.
바니바니당근당근 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 바니바니당근당근 서비스의 이용과 관련하여 바니바니당근당근 서비스를 제공하는 바니바니당근당근 주식회사(이하 ‘바니바니당근당근’)와 이를 이용하는 바니바니당근당근 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 바니바니당근당근 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
       </textarea>
				</li>
				<li class="checkBox check03">
					<ul class="clearfix">
						<li>개인정보 수집 및 이용에 대한 안내(필수)</li>
						<li class="checkBtn"><input type="checkbox" name="pointcheck2" id="pointcheck2" value="0" />
						</li>
					</ul> <textarea name="" id="" readonly="readonly">여러분을 환영합니다.
바니바니당근당근 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 바니바니당근당근 서비스의 이용과 관련하여 바니바니당근당근 서비스를 제공하는 바니바니당근당근 주식회사(이하 ‘바니바니당근당근’)와 이를 이용하는 바니바니당근당근 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 바니바니당근당근 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
       </textarea>
				</li>
			</ul>
		</div>
		<!-- fn_enroll_validate 함수가 submit을 하면 실행이 되도록 이벤트를 걸어줘야함. 그래야 사용이 가능. 
			로직에서 true가 반환되면 넘어가고 false가 되면 전송을 막음. -->
		<input class="btn2" type="submit" value="가입" onclick="return fn_enroll_validate();">
		<!-- <button type="button" id="signUp">가입</button> -->
		<input class="btn2" type="reset" value="취소">
	</form>
</section>
<form action="" name="checkDuplicateId">
	<input type="hidden" name="userId"> 
</form>
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

			//비밀번호와 비밀번호확인의 값이 일치하는지 check
			$("#password_2").blur((e)=>{
				const pwck=$(e.target).val();
				const pw=$("#password_").val();
				if(pwck!=pw){
					alert("패스워드가 일치하지 않습니다.");
					$("#password_").focus();
				}
			});
			
		});	
		
		const fn_enroll_validate=()=>{
			const userId=$("#userId_");
			const pwck=$("#password_");
			const pw=$("#password_2");	
			if(userId.val().length<4){
				alert("아이디는 최소 4자리 이상이여야 합니다.");
				userId.focus();
				return false;
			}
			if(pwck.val() != pw.val()){
				alert("패스워드가 일치하지 않습니다.");
				pw.focus();
				return false;
			}
			//여기에서 체크해 체크박스에 체크가 되었는지 안되었는지
			//안되어있으면 false를 반환 
			if($("input:checkbox[name=pointcheck]").is(":checked")==false){
				alert("약관동의 체크해주세요.");
				pointcheck.focus();
				return false;
			}
			if($("input:checkbox[name=pointcheck2]").is(":checked")==false){
				alert("약관동의 체크해주세요.");
				pointcheck2.focus();
				return false;
			}
		}
		
		const fn_id_duplicate=()=>{
			//1. 연결할 주소
			const url="<%=request.getContextPath()%>/checkDuplicateId";
			const title="checkDuplicateId";
			const status="left=500px,top=100px,width=300px,height=200px";
			
			open("",title,status);
			
			//form에 있는 input의 값을 채우고 윈도우(새창)에서 그 결과를 받는 로직으로 설정
			//form태그는 name속성을 설정하면 그 name속성으로 직접접근이 가능함
			console.log(checkDuplicateId);
			//form의 name속으로 필요한 데이터 세팅하기
			checkDuplicateId.userId.value=$("#userId_").val();
			checkDuplicateId.method="post";
			checkDuplicateId.action=url;
			//이 form태그가 제출(submit)되는 윈도우를 지정
			checkDuplicateId.target=title;
			
			//제출
			checkDuplicateId.submit();
		}

	</script>


<%@ include file="/views/common/footer.jsp"%>









