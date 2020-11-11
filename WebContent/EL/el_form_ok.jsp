<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	/*
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String pw = request.getParameter("pw"); 
	*/



%>
      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- param.태그이름 으로 한번에 받아서 사용한다 -->
	<!-- request로 보내진 값을 바로 받아서 사용할 수 있ㄷ -->
	<!-- 하지만 MVC2에서는 param이 사용될 일이 없다고 한다 -->
	이름:${param.name }<br/>
	아이디:${param.id }<br/>
	비밀번호:${param.pw }<br/>
	
</body>
</html>


















































