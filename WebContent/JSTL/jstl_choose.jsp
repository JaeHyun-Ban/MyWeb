<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- choose는 else if의 용도로 사용된다 -->
	<c:choose>
		<%-- 이곳에 c:when 작성 --%>
		<c:when test="${param.name eq '홍길동' }">
			<h4>홍길동 입니다</h4>
		</c:when>
		<c:when test="${param.name eq '이순신' }">
			<h4>이순신 입니다</h4>
		</c:when>
		<%-- otherwise - else의 용도 --%>
		<c:otherwise>
			<h4>홍길동, 이순신이 아닙니다</h4>
		</c:otherwise>
	
	</c:choose>
	
	<!-- 20세 이상  성인, 20미만 미성년자 -->
	<c:choose>
		<c:when test="${param.age >= 20 }">
			<h3>성인</h3>
		</c:when>
		<c:otherwise>
			<h3>미성년자</h3>
		</c:otherwise>
	</c:choose>
	
	<% if(Integer.parseInt(request.getParameter("age")) >= 20) {%>
		<h3>성인</h3>
	<% } else {%>
		<h3>미성년자</h3>
	<% } %>
</body>
</html>














































