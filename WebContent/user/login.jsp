<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<!-- section = html의 본문 -->
<section>
	<div align="center">
		<h2>로그인 연습</h2>
		<hr/>
		<form action="loginForm.user" method="post">
			<input type="text" name="id" placeholder="아이디" required><br/>
			<input type="password" name="pw" placeholder="비밀번호" required><br/>
			<input type="submit" value="로그인" class="btn btn-default">
			<input type="button" value="취소" class="btn btn-primary" onclick="goindex()">
		</form>
		
		<br/>
		<%-- span = 블럭 x 줄이 안바꾸미, div = 블럭o --%>
		<span>${msg }</span>
		
	</div>
</section>

<script type="text/javascript">
	function goindex() {
		location.href="<%=request.getContextPath()%>/index.jsp";
	}
	
	
</script>

<%@ include file="../include/footer.jsp"%>