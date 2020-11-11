<%@page import="com.myweb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	UserVO vo = new UserVO();
	vo.setId("aaa123");
	vo.setName("이순신");
	vo.setEmail("aaa@google.com");
	
	request.setAttribute("vo", vo);//리퀘스트에 강제저장


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- getter가 존재해야 사용할 수 있다 -->
	아이디:${requestScope.vo.id }<br/>
	이름:${requestScope.vo.name }<br/>
	이메일:${requestScope.vo.email }<br/>
	
	<hr/>
	<!-- requestScope는 생략하고 많이 사용한다 -->
	아이디:${vo.id }<br/>
	이름:${vo.name }<br/>
	이메일:${vo.email }<br/>
	
	
	
</body>
</html>


















































