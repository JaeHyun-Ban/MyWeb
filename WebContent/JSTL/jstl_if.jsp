<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- JSTL을 사용하기 위해서는 선언이 필요하다, prefix(접두어) c로 사용하겠습니다 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 
c:out - 내용 출력
c:set - 변수선언
c:if - 조건처리
c:choose - 다중 조건 처리
c:forEach - 컬렉션이나 Map의 각 항목 처리
c:when - 조건에 맞을 때
c:otherwise - 맞는 조건이 없을 경우

 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 변수 선언 -->
	<c:set var="num1" value="1"/>
	
	<!-- c:set으로 생성한 변수는 el태그로 선언 가능하다? -->
	변수 출력: ${num1 }<br/>
	
	<hr/>
	<!-- 변수 출력 >> 굳이 쓸일은 없을꺼임 -->
	<c:out value="${num1 }"/>
	
	
	
	<hr/>
	<c:if test="true"><!-- test="조건" -->
		무조건 실행되는 문장<br/>
	</c:if>
	<!-- 위,아래 같은 용도로 사용됨 -->
	<% if(true) { %>
		무조건 실행되는 문장<br/>
	<% } %>
	
	<hr/>
	<!-- form태그를 받아보자 -->
	<c:if test="${param.name eq '홍길동' }">
		${param.name }입니다<br/>
		${param.age }세 입니다<br/>
	</c:if>
	
	<c:if test="${param.name == '이순신' }">
		${param.name }입니다<br/>
		${param.age }세 입니다.<br/>
	</c:if>
	
	<% if(request.getParameter("name").equals("홍길동")) { %>
		이름이 홍길동 입니다.<br/>
	<% } %>
	
	
	<hr/>
	<!-- age파라미터 값이 20이상이면 성인입니다, 20미만이면 미성년자입니다 -->
	<c:if test="${param.age >= 20 }"><!-- 자바에서 문자열을 숫자와 직접 비교하는것은 불가능하다 -->
		성인입니다<br/>
	</c:if>
	<c:if test="${param.age < 20 }">
		미성년자 입니다<br/>
	</c:if>
	
	<% if(Integer.parseInt(request.getParameter("age")) >= 20) {%>
		성인입니다<br/>
	<% } %>
	
</body>
</html>























































