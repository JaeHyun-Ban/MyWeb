<%@page import="com.myweb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
	//기존 세션값 얻어오는 방식
	UserVO vo = (UserVO)session.getAttribute("vo");
	String auth = (String)session.getAttribute("auth");
	*/


%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- 
	세션도 sessionScope을 생략해서 할 수 있지만, 구분이 힘드므로 꼭 작성해주자
	-->
	auth:${sessionScope.auth }<br/>
	아이디:${sessionScope.vo.id }<br/>
	이름:${sessionScope.vo.name }<br/>
	<!-- 스크립트릿 없이 값을 꺼내올 수 있게된다 -->
	
	
</body>
</html>



















































