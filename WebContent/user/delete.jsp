<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>


<!-- 탈퇴 전 확인 비밀번호 받기 -->
<section>
	<div align="center">
		<h2>기존 비밀번호를 입력해주세요</h2>
		<form action="deleteForm.user" method="post">
			비밀번호: <input type="password" name="pw">
			<input type="submit" value="탈퇴" class="btn btn-danger" >
		</form>
		
		<span>${msg }</span><%-- 이렇게 표현해도 됨 --%>
	</div>
	
	<%-- <br/>
	<div align="center"><b>${msg }</b></div>
	<br/> --%>
	
</section>


<%@ include file="../include/footer.jsp" %>


<!-- 입력값이 0일 때 -->
<!-- <script type="text/javascript">

	function blank() {
		console.log(document.del.pw.value);
		var num = document.del.pw.value.length;
		
		if(num == 0) {//입력x
			alert("비밀번호를 입력하세요");
			return;//함수 종료
		} else {
			document.del.submit();
		}
	}

</script> -->





























































