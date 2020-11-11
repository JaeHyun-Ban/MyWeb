<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<!-- 
section은 html에서 본문을 의미하는 태그로 
div로 나누어서 사용하면 된다 -->
<section>
	<div align="center">
		<h2>회원가입 연습</h2>
		<hr/>
		<form action="join_ok.jsp" method="post" name="regForm">
			<table >
				<tr>
					<td>아이디:</td>
					<td><input type="text" name="id" placeholder="4글자 이상">
				</tr>
				<tr>
					<td>비밀번호:</td>
					<td><input type="password" name="pw" placeholder="특수문자 1개 포함">
				</tr>
				<tr>
					<td>비밀번호확인:</td>
					<td><input type="password" name="pwCheck">
				</tr>
				<tr>
					<td>이름:</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>이메일:</td>
					<td><input type="email" name="email">
				</tr>
				<tr>
					<td>주소:</td>
					<td><input type="text" name="address"></td>
				</tr>
			</table>
			<!-- table안에는 tr과 td만 존재해야 한다 -->
		</form>
		<!-- class에는 header에서 참조하는 bootstrap을 이용해 생성한 디자인이다, 짱신기하네 -->
		<!-- onclick안에는 자바스크립트코드를 작성한다 -->
		<input type="button" value="가입" class="btn btn-default" onclick="check()">
		<input type="button" value="로그인" class="btn btn-primary" onclick="location.href='login.jsp'">
	</div>

</section>

<!-- onclick안에 자바스크립트 직접 작성 x, 따로 작성 후 대입 -->
<script type="text/javascript">
	function check() { //가입버튼 이벤트
		//alert("가입 버튼 눌림!");
		//form태그는 유일하게 document.form이름.이름... 접근이 가능하다.
		console.log(document.regForm.id);//>누를때마다 폼태그의 id 정보를 가져온다
		console.log(document.regForm.id.name);
		console.log(document.regForm.id.value);
		
		//value는 String값이다
		if(document.regForm.id.value.length < 4){ //value가 공백이라면
			//>아이디 길이가 4자리 미만이라면 경고창
			alert("공백입니다!");//경고창 띄워주기
			return; //함수 종료
		} else if(document.regForm.pw.value < 4 ){
			alert("비밀번호는 4자리 이상입니다");
			return;
		} else if(document.regForm.pw.value != document.regForm.pwCheck.value){
			alert("비밀번호를 다시 확인해주세요");
			return;
			
		} else if(document.regForm.name.value == '') {
			alert("이름은 필수 입니다");
			return;
		} else {
			//submit()은 자바스크립트의 서브밋기능
			document.regForm.submit();
		}
		
		
		
		
	}
</script>


<%@ include file="../include/footer.jsp" %>
























































