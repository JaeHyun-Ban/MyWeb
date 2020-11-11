<%@page import="com.myweb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../include/header.jsp"%>

<%
	//이 페이지에 진입할 시, 비밀번호를 제외한 회원의 정보를 input태그에 처리합니다.
	request.setCharacterEncoding("utf-8");
	UserVO vo =  (UserVO)session.getAttribute("login");
	String id = vo.getId();
	String name = vo.getName();
	String email = vo.getEmail();
	String address = vo.getAddress();
%>


<section>
	<div align="center">
		<h2>회원정보 연습</h2>
		<hr/>
		<form action="update_ok.jsp" method="post" name="regForm">
			<table>
				<tr>
					<!-- id를 PK로 생성해서 수정이 불가능하다 -->
					<td>아이디:</td>
					<td><input type="text" name="id" value="<%=id %>" placeholder="4글자 이상" readonly></td>
					<!-- disable: 수정x 값이 안넘어감 || readonly: 수정x, 갑은 넘어감 -->
				</tr>
				<tr>
					<td>비밀번호:</td>
					<td><input type="password" name="pw"></td>
				</tr>
				<tr>
					<td>비밀번호확인:</td>
					<td><input type="password" name="pwCheck"></td>
				</tr>
				<tr>
					<td>이름:</td>
					<td><input type="text" name="name" value="<%=name %>"></td>
				</tr>
				<tr>
					<td>이메일:</td>
					<td><input type="email" name="email" value="<%=email %>"></td>
				</tr>
				<tr>
					<td>주소:</td>
					<td><input type="text" name="address" value="<%=address %>"></td>
				</tr>
			</table>
		</form>
		<br/>
		<input type="button" value="정보 수정" class="btn btn-default" onclick="check()">
		<input type="button" value="취소" class="btn btn-primary" onclick="history.go(-1)"><%-- history(인터넷사용기록).go(-1)[이동] --%>
	</div>

</section>

<%@ include file="../include/footer.jsp"%>

<%-- JavaScript 기능들 --%>
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














































