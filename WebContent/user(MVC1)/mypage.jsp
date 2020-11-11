<%@page import="com.myweb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//접근 금지 설정해주기
	if(session.getAttribute("login") == null) {
		response.sendRedirect("login.jsp");
	} 
	request.setCharacterEncoding("utf-8");
	UserVO vo = (UserVO)session.getAttribute("login");
	//꺼내놓고 사용해도 됨
%>    

<%@ include file="../include/header.jsp" %>

<section>
	<div align="center">
		<h2><%=vo.getId() %>(<%=vo.getName() %>)님이 로그인 중입니다</h2>
		
		<a href="update.jsp">[정보 수정]</a>
		<a href="delete.jsp">[회원 탈퇴]</a>
	
	</div>
</section>


<%@ include file="../include/footer.jsp" %>



























































