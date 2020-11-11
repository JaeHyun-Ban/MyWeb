<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	//로그아웃 해주기
	session.removeAttribute("login");
	//혹은
	//session.invalidate();//이걸로 지워주기
	response.sendRedirect(request.getContextPath()); //home화면으로

%>