<%@page import="com.myweb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//접근 금지 설정해주기
	if(session.getAttribute("user") == null) {
		response.sendRedirect("login.jsp");
	} 
	request.setCharacterEncoding("utf-8");
	UserVO vo = (UserVO)session.getAttribute("user");
	//꺼내놓고 사용해도 됨
	
	//>>>>EL코드들은 이제 줄이도록 하자
%>    

<%@ include file="../include/header.jsp" %>

<section>
	<div align="center">
		<%-- 화면에는 EL코드도없이 화면만 남기는 것이 제일 이상적 --%>
		<h2>${sessionScope.user.id }(${sessionScope.user.name })님이 로그인 중입니다</h2>
		
		<a href="update.user">[정보 수정]</a>
		<a href="delete.user">[회원 탈퇴]</a>
	
	</div>
</section>


<%@ include file="../include/footer.jsp" %>




























































